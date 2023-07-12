package dev.alexferreira.sampleapi.adapter.rest;

import dev.alexferreira.sampleapi.adapter.rest.request.CreateInquilinoRequest;
import dev.alexferreira.sampleapi.usecase.CreateInquilino;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/inquilinos", consumes = "application/json", produces = "application/json")
public class InquilinoResource {

   private final CreateInquilino createInquilino;

   @Autowired
   public InquilinoResource(CreateInquilino createInquilino) {this.createInquilino = createInquilino;}

   @PostMapping
   @ResponseStatus(code = HttpStatus.CREATED)
   public void createInquilino(@RequestBody CreateInquilinoRequest request) {
      createInquilino.execute(request.toInput());
   }
}
