package dev.alexferreira.sampleapi.common.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({MockitoExtension.class})
public abstract class BaseUnitTests {

   @BeforeEach
   void setUp() {
      Mockito.validateMockitoUsage();
   }
}
