package dev.alexferreira.sampleapi.domain.tenant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Tenant {

   @Id
   private UUID id = UUID.randomUUID();
   @Column(nullable = false)
   private String name;
   @Column
   private String flatNumber;
   @Column
   private String tower;
   @Column(unique = true, updatable = false, nullable = false)
   private String document;
   @Column
   private String imagePath;
   @Column(nullable = false, updatable = false)
   private Instant createdAt = Instant.now();

   public UUID getId() {
      return id;
   }

   public void setId(UUID id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getFlatNumber() {
      return flatNumber;
   }

   public void setFlatNumber(String flatNumber) {
      this.flatNumber = flatNumber;
   }

   public String getTower() {
      return tower;
   }

   public void setTower(String tower) {
      this.tower = tower;
   }

   public String getDocument() {
      return document;
   }

   public void setDocument(String document) {
      this.document = document;
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
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Tenant tenant = (Tenant) o;
      return Objects.equals(id, tenant.id) && Objects.equals(name, tenant.name) && Objects.equals(flatNumber, tenant.flatNumber) && Objects.equals(tower, tenant.tower) && Objects.equals(document, tenant.document) && Objects.equals(imagePath, tenant.imagePath) && Objects.equals(createdAt, tenant.createdAt);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, name, flatNumber, tower, document, imagePath, createdAt);
   }

   @Override
   public String toString() {
      return "Tenant{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", flatNumber='" + flatNumber + '\'' +
              ", tower='" + tower + '\'' +
              ", document='" + document + '\'' +
              ", imagePath='" + imagePath + '\'' +
              ", createdAt=" + createdAt +
              '}';
   }
}
