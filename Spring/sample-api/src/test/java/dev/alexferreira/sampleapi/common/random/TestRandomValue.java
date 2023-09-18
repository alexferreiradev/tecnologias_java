package dev.alexferreira.sampleapi.common.random;

import org.apache.commons.math.random.RandomDataImpl;

public class TestRandomValue {

   public static String generateCpf() {
      return String.valueOf(new RandomDataImpl().nextLong(1000000000, 99999999999L));
   }
}
