package dev.alexferreira.sampleapi.modules.product.api;

import org.slf4j.*;
import org.springframework.context.annotation.*;

@Profile("dev")
@Configuration
public class NewBean {

   @Bean
   public Logger getLog() {
      return LoggerFactory.getLogger("teste1");
   }
}
