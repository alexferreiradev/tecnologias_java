package dev.alexferreira.sampleapi.domain.inquilino;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Inquilino {

   @Id private UUID id = UUID.randomUUID();
   @Column(nullable = false) private String nome;
   @Column private String apartamento;
   @Column private String bloco;
   @Column(unique = true, updatable = false, nullable = false) private String documento;
   @Column private String imagePath;
   @Column(nullable = false, updatable = false) private Instant createdAt = Instant.now();

   public UUID getId() {
      return id;
   }

   public void setId(UUID id) {
      this.id = id;
   }

   public String getNome() {
      return nome;
   }

   public void setNome(String nome) {
      this.nome = nome;
   }

   public String getApartamento() {
      return apartamento;
   }

   public void setApartamento(String apartamento) {
      this.apartamento = apartamento;
   }

   public String getBloco() {
      return bloco;
   }

   public void setBloco(String bloco) {
      this.bloco = bloco;
   }

   public String getDocumento() {
      return documento;
   }

   public void setDocumento(String documento) {
      this.documento = documento;
   }

   public String getImagePath() {
      return imagePath;
   }

   public void setImagePath(String imagePath) {
      this.imagePath = imagePath;
   }

   public Instant getCreatedAt() {
      return createdAt;
   }

   public void setCreatedAt(Instant createdAt) {
      this.createdAt = createdAt;
   }

   @Override
   public boolean equals(Object o) {
      if(this == o) return true;
      if(o == null || getClass() != o.getClass()) return false;
      Inquilino inquilino = (Inquilino) o;
      return Objects.equals(id, inquilino.id) && Objects.equals(nome, inquilino.nome) && Objects.equals(apartamento,
         inquilino.apartamento
      ) && Objects.equals(bloco, inquilino.bloco) && Objects.equals(documento, inquilino.documento) && Objects.equals(imagePath,
         inquilino.imagePath
      ) && Objects.equals(
         createdAt,
         inquilino.createdAt
      );
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, nome, apartamento, bloco, documento, imagePath, createdAt);
   }

   @Override
   public String toString() {
      return "Inquilino{" + "id=" + id + ", nome='" + nome + '\'' + ", apartamento='" + apartamento + '\'' + ", bloco='"
         + bloco + '\'' + ", documento='" + documento + '\'' + ", imagePath='" + imagePath + '\'' + ", createdAt="
         + createdAt + '}';
   }

}
