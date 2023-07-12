package dev.alexferreira.sampleapi.usecase;

import dev.alexferreira.sampleapi.domain.inquilino.ImagemInquilinoStorage;
import dev.alexferreira.sampleapi.domain.inquilino.Inquilino;
import dev.alexferreira.sampleapi.domain.inquilino.InquilinoCreatedProducer;
import dev.alexferreira.sampleapi.domain.inquilino.InquilinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateInquilino {

   private final InquilinoRepository inquilinoRepository;
   private final ImagemInquilinoStorage imagemInquilinoStorage;
   private final InquilinoCreatedProducer inquilinoCreatedProducer;

   @Autowired
   public CreateInquilino(
      InquilinoRepository inquilinoRepository,
      ImagemInquilinoStorage imagemInquilinoStorage,
      InquilinoCreatedProducer inquilinoCreatedProducer
   ) {
      this.inquilinoRepository = inquilinoRepository;
      this.imagemInquilinoStorage = imagemInquilinoStorage;
      this.inquilinoCreatedProducer = inquilinoCreatedProducer;
   }

   public void execute(CreateInquilinoInput input) {
      Optional<Inquilino> possibleInquilino = inquilinoRepository.findByDocumento(input.documento);

      if(possibleInquilino.isPresent()) {
         throw new RuntimeException("Inquilino já cadastrado");
      }

      Inquilino inquilino = new Inquilino();
      inquilino.setNome(input.nome);
      inquilino.setBloco(input.bloco);
      inquilino.setApartamento(input.apartamento);
      inquilino.setDocumento(input.documento);

      if(input.imagem != null) {
         inquilino.setImagePath(imagemInquilinoStorage.save(inquilino, input.imagem));
      }

      inquilinoRepository.save(inquilino);

      inquilinoCreatedProducer.send(inquilino);
   }
}
