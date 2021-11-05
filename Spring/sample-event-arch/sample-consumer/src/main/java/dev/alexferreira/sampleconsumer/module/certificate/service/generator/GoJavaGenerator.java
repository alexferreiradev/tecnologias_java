package dev.alexferreira.sampleconsumer.module.certificate.service.generator;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import dev.alexferreira.sampleconsumer.core.helper.DateHelper;
import dev.alexferreira.sampleconsumer.core.helper.FileHelper;
import dev.alexferreira.sampleconsumer.core.helper.StreamHelper;
import dev.alexferreira.sampleconsumer.core.util.ParticipantUtil;
import dev.alexferreira.sampleconsumer.module.certificate.command.CertificatorGeneratorCommand;
import dev.alexferreira.sampleconsumer.module.certificate.model.Certificate;
import dev.alexferreira.sampleconsumer.module.certificate.model.Participant;
import dev.alexferreira.sampleconsumer.module.certificate.service.generator.token.TokenGenerator;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class GoJavaGenerator implements CertificateGenerator {

    private final Logger logger;
    private final TokenGenerator tokenGenerator;

    @Autowired
    public GoJavaGenerator(TokenGenerator tokenGenerator) {
        this.logger = LoggerFactory.getLogger(GoJavaGenerator.class);
        this.tokenGenerator = tokenGenerator;
    }

    @Override
    public List<Certificate> generateCertificates(CertificatorGeneratorCommand command) {
        logger.info("Iniciando a geração de certificados de {} participantes", command.getParticipantList().size());

        List<Certificate> certificateList = crieListaCertificadoOrThrow(command);
        logger.info("{} certificados gerados", certificateList.size());

        return certificateList;
    }

    private List<Certificate> crieListaCertificadoOrThrow(CertificatorGeneratorCommand command) {
        List<Certificate> certificateList = new ArrayList<>();
        for (Participant participant : command.getParticipantList()) {
            logger.debug("Gerando certificado para participante: {}", participant);
            Certificate certificate = crieCertificadoParticipanteOrThrow(command, participant);
            certificateList.add(certificate);
        }

        return certificateList;
    }

    @Override
    public Certificate generateCertificate(Participant participant, CertificatorGeneratorCommand command) {
        return crieCertificadoParticipanteOrThrow(command, participant);
    }

    private Certificate crieCertificadoParticipanteOrThrow(CertificatorGeneratorCommand command, Participant participant) {
        try {
            Certificate certificate = new Certificate();
            certificate.setParticipant(participant);

            certificate.setFileName(createCertName(participant));
            certificate.setFileExtension("pdf");
//            certificate.setUuid(tokenGenerator.generateToken(certificate));
            certificate.setUuid(UUID.randomUUID().toString());
            certificate.setFileContent(buildPdfFileContent(certificate, command));

            return certificate;
        } catch (Exception e) {
            logger.error("Erro ao gerar certificado para participante: {}", ParticipantUtil.createIdentifier(participant), e);
            throw new RuntimeException("Erro ao gerar certificados, consulte o suporte", e);
        }
    }

    private byte[] buildPdfFileContent(Certificate certificate, CertificatorGeneratorCommand command) {
        FileOutputStream fileOutputStream = null;
        Document document = null;

        try {
            logger.debug("Criando arquivo PDF para participante: {}", certificate.getParticipant());

            File certificateTemp = File.createTempFile("certificateTemp_", ".pdf");
            fileOutputStream = new FileOutputStream(certificateTemp);

            document = new Document(PageSize.A4.rotate());
            PdfWriter writer = PdfWriter.getInstance(document, fileOutputStream);
            document.open();

            ColumnText ct = new ColumnText(writer.getDirectContent());
            ct.setSimpleColumn(75, 200, 775, 480);

            Font paragraphFont = createFontToCertText();
            Paragraph paragraph = createParagraphToCertText(certificate, paragraphFont);
            ct.addElement(paragraph);
            ct.go();

            addBackgroundImage(writer.getDirectContentUnder(), command);

            document.close();
            certificateTemp.deleteOnExit();

            return FileUtils.readFileToByteArray(certificateTemp);
        } catch (IOException | DocumentException | URISyntaxException e) {
            logger.error(e.getLocalizedMessage(), e);
            String msgErro = String.format("Erro ao tentar gerar PDF: %s", certificate.getFileName());
            throw new RuntimeException(msgErro, e);
        } finally {
            if (document != null) {
                document.close();
            }
            StreamHelper.closeSafeOutput(fileOutputStream);
        }
    }

    private void addBackgroundImage(PdfContentByte background, CertificatorGeneratorCommand command) throws IOException, URISyntaxException, DocumentException {
        logger.debug("Adicionando imagem de background");

        String backgroundFileName = command.getBackgroundFileName();

        URL backgroundUrl = getClass().getResource("/img/gojava_certificado-2-0-0.png").toURI().toURL();
        if (FileHelper.isValidFile(backgroundFileName)) {
            backgroundUrl = new File(backgroundFileName).toURI().toURL();
        }

        Image image = Image.getInstance(backgroundUrl);
        image.setAbsolutePosition(0, 0);
        image.scaleAbsolute(PageSize.A4.rotate());
        background.saveState();
        PdfGState pdfGState = new PdfGState();
        pdfGState.setFillOpacity(0.8f);
        background.setGState(pdfGState);
        background.addImage(image);
        background.restoreState();
    }

    private Paragraph createParagraphToCertText(Certificate certificate, Font paragraphFont) {
        Paragraph paragraph = new Paragraph();
        paragraph.setLeading(0f, 1.1f);
        paragraph.setAlignment(Paragraph.ALIGN_JUSTIFIED);
        paragraph.setFont(paragraphFont);
        paragraph.add(generateCertificateText(certificate));
        return paragraph;
    }

    private Font createFontToCertText() {
        Font paragraphFont = new Font();
        paragraphFont.setSize(24);
        paragraphFont.setColor(BaseColor.WHITE);
        paragraphFont.setFamily(BaseFont.TIMES_ROMAN);
        paragraphFont.setStyle(Font.NORMAL);
        return paragraphFont;
    }

    private String generateCertificateText(Certificate certificate) {
        logger.debug("Gerando textos para certificado do participante: {}", certificate.getParticipant());

        String finalText = "Certificamos que NOME_PARTICIPANTE com TIPO_IDENTIFICACAO IDENTIFICACAO_PARTICIPANTE participou do evento NOME_EVENTO durante HORAS_EVENTO horas no "
                + "dia DATA_EVENTO promovido pelo Gojava - Grupo de usuários Java de Goiás. O evento foi sobre ASSUNTO_EVENTO. Valide seu certificado em gojava.dev com o token "
                + "de validação: VALIDATOR_UUID";
        Participant participant = certificate.getParticipant();
        finalText = finalText.replaceAll("NOME_PARTICIPANTE", ParticipantUtil.participantCompleteName(participant).toUpperCase());
        if (participant.getCpf() != null) {
            finalText = finalText.replaceAll("TIPO_IDENTIFICACAO", "CPF");
            finalText = finalText.replaceAll("IDENTIFICACAO_PARTICIPANTE", participant.getCpf());
        } else {
            finalText = finalText.replaceAll("TIPO_IDENTIFICACAO", "RG");
            finalText = finalText.replaceAll("IDENTIFICACAO_PARTICIPANTE", participant.getRg());
        }
        finalText = finalText.replaceAll("NOME_EVENTO", participant.getEvent().getName());
        finalText = finalText.replaceAll("HORAS_EVENTO", participant.getHour());

        Date dateStarted = participant.getEvent().getDateStarted();
        Date dateEnded = participant.getEvent().getDateEnded();
        if (dateEnded != null && !dateEnded.equals(dateStarted)) {
            String dateStartedString = DateHelper.format(dateStarted, DateFormat.SHORT);
            String dateEndedString = DateHelper.format(dateEnded, DateFormat.SHORT);
            finalText = finalText.replaceAll("DATA_EVENTO", String.format("%s - %s", dateStartedString, dateEndedString));
        } else {
            finalText = finalText.replaceAll("DATA_EVENTO", DateHelper.format(dateStarted, DateFormat.SHORT));
        }

        finalText = finalText.replaceAll("ASSUNTO_EVENTO", participant.getEvent().getTalkerTopics());
        finalText = finalText.replaceAll("VALIDATOR_UUID", certificate.getUuid());

        return finalText;
    }

    private String createCertName(Participant participant) {
        logger.debug("Criando nome do certificado do participante: {}", participant);
        String identy = participant.getCpf();
        if (identy == null || identy.isEmpty()) {
            identy = participant.getRg();
        }

        return "certificado_" + identy;
    }
}
