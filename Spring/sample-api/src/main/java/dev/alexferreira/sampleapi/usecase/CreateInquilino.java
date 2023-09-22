package dev.alexferreira.sampleapi.usecase;

import dev.alexferreira.sampleapi.domain.inquilino.ImagemInquilinoStorage;
import dev.alexferreira.sampleapi.domain.inquilino.Inquilino;
import dev.alexferreira.sampleapi.domain.inquilino.InquilinoProducer;
import dev.alexferreira.sampleapi.domain.inquilino.InquilinoRepository;
import dev.alexferreira.sampleapi.domain.inquilino.exception.InquilinoExistenteException;
import dev.alexferreira.sampleapi.usecase.input.CreateInquilinoInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CreateInquilino {

   private final InquilinoRepository inquilinoRepository;
   private final ImagemInquilinoStorage imagemInquilinoStorage;
   private final InquilinoProducer inquilinoCreatedProducer;
   private final String createdTopicName;

   @Autowired
   public CreateInquilino(
      InquilinoRepository inquilinoRepository, ImagemInquilinoStorage imagemInquilinoStorage,
      InquilinoProducer inquilinoCreatedProducer,
      @Value("${spring.kafka.producer.properties.topics.inquilino}") String createdTopicName
   ) {
      this.inquilinoRepository = inquilinoRepository;
      this.imagemInquilinoStorage = imagemInquilinoStorage;
      this.inquilinoCreatedProducer = inquilinoCreatedProducer;
      this.createdTopicName = createdTopicName;
   }

   @Transactional
   public void execute(CreateInquilinoInput input) {
      inquilinoRepository.findByDocumento(input.documento).ifPresent(inquilino -> {
         throw new InquilinoExistenteException();
      });

      Inquilino inquilino = new Inquilino();
      inquilino.setNome(input.nome);
      inquilino.setBloco(input.bloco);
      inquilino.setApartamento(input.apartamento);
      inquilino.setDocumento(input.documento);

      if(input.imagem != null) {
         inquilino.setImagePath(imagemInquilinoStorage.save(inquilino, input.imagem));
      }

      inquilinoRepository.save(inquilino);

      inquilinoCreatedProducer.send(inquilino, createdTopicName);
   }
}
