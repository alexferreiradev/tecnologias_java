package dev.alexferreira.sampleapi.modules.product.api;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ExceptionHandlingController {

   @ExceptionHandler(Throwable.class)
   public ResponseEntity error(Throwable throwable) {
      return ResponseEntity.status(500).build();
   }

}
