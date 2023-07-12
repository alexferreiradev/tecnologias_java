package dev.alexferreira.sampleapi.domain.inquilino;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InquilinoRepository extends JpaRepository<Inquilino, UUID> {

   Optional<Inquilino> findByDocumento(String documento);
}
