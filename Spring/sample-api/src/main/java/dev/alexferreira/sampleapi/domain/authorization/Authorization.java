package dev.alexferreira.sampleapi.domain.authorization;

import dev.alexferreira.sampleapi.domain.user.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Document
public class Authorization {

   @Id
   public String id;

   @Field
   public User userAuthorized;
   @Field
   public String tipoPorta;
   @Field
   public String descPorta;

   @Field
   public Instant createdAt;
}
