package dev.alexferreira.sampleapi.domain.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Document
public class User {

   @Id
   public String id = UUID.randomUUID().toString();

   @Field
   @Indexed(unique = true)
   public String document;

}
