package dev.alexferreira.sampleconsumer.module.certificate.service.generator.token;

import dev.alexferreira.sampleconsumer.core.helper.DateHelper;
import dev.alexferreira.sampleconsumer.core.helper.UUIDHelper;
import dev.alexferreira.sampleconsumer.module.certificate.model.Certificate;
import dev.alexferreira.sampleconsumer.module.certificate.model.Event;
import dev.alexferreira.sampleconsumer.module.certificate.model.Participant;
import org.springframework.stereotype.Service;

import java.text.DateFormat;

@Service
public class TokenGenerator_SHA256 implements TokenGenerator {

    /**
     * Cuidado ao alterar este método. Deve ser refletido no validador de token.
     *
     * @param certificate model
     * @return uuid como token de validacao
     */
    @Override
    public String generateToken(Certificate certificate) {
        String uuidText = createUUIDText(certificate.getParticipant());

        return UUIDHelper.generateUUID_SHA256(uuidText);
    }

    /**
     * Não altere a ordem nem os dados sem alterar toda estrutura de validação.
     * Todos dados serão utilizados para gerar outro UUID, se alterar esta regra, os dados
     * do validador devem ser regerados, senão irão ficar inválidos, o que é impossível, pois
     * os certificados ficam com os participantes.
     *
     * <p>
     * Portanto, nunca altere este método.
     *
     * @param participant participante
     * @return texto que será utilizado para gerar uuid com sha256
     */
    private String createUUIDText(Participant participant) {
        String cpf = participant.getCpf();
        String participantIdentifier = cpf != null ? cpf : participant.getRg();
        Event event = participant.getEvent();
        String eventName = event.getName();
        String eventStartedDate = DateHelper.format(event.getDateStarted(), DateFormat.SHORT);
        String eventExecutor = event.getExecutor();

        return String.format("%s - %s - %s - %s", participantIdentifier, eventName, eventStartedDate, eventExecutor);
    }
}
