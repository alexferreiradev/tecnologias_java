package dev.alexferreira.sampleapi.test.container;

import dev.alexferreira.sampleapi.test.spring.*;

import java.util.*;

public class PostgresContainer implements Initializer.Configurator {

   @Override
   public Map<String, String> createDynamicProperties() {
      System.out.println("Creating property");
      return Collections.emptyMap();
   }
}
