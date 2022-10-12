package dev.alexferreira.sampleapi.modules.product.api;

import dev.alexferreira.sampleapi.common.*;
import org.junit.jupiter.api.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.test.context.*;
import org.springframework.test.context.support.*;

@ContextConfiguration(classes = NewBean.class, loader = AnnotationConfigContextLoader.class)
@ActiveProfiles("dev")
class ProductApiIT2 extends IntegrationTest {

   @Autowired Logger logger;

   @Test public void test() {
      System.out.println();
      logger.info("teste");
   }

   @Test public void test2() {
         System.out.println();
      logger.info("teste2");
   }

}
