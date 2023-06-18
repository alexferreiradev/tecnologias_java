package dev.alexferreira.sampleapi.test.container;

import dev.alexferreira.sampleapi.test.spring.*;
import org.testcontainers.containers.*;
import org.testcontainers.utility.*;

import java.util.*;

public class MongoCustomContainer extends MongoDBContainer implements Initializer.Configurator {

   private final String databaseName = "sample-api";
   ;

   public MongoCustomContainer() {
      super(DockerImageName.parse("mongo"));

      withEnv("MONGO_INITDB_DATABASE", databaseName);

      start();
   }

   @Override
   public Map<String, String> createDynamicProperties() {
      return Map.of("spring.data.mongodb.uri", getReplicaSetUrl(databaseName));
   }
}
