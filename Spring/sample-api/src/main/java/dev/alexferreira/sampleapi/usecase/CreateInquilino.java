package dev.alexferreira.sampleapi.usecase;

import dev.alexferreira.sampleapi.domain.inquilino.Inquilino;
import dev.alexferreira.sampleapi.domain.inquilino.InquilinoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateInquilino {

   private final InquilinoRepository inquilinoRepository;

   public CreateInquilino(InquilinoRepository inquilinoRepository) {
      this.inquilinoRepository = inquilinoRepository;
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
      inquilinoRepository.save(inquilino);
   }
}
