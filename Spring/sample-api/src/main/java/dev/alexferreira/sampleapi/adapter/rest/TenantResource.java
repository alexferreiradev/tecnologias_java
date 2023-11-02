package dev.alexferreira.sampleapi.adapter.rest;

import dev.alexferreira.sampleapi.adapter.rest.request.CreateTenantRequest;
import dev.alexferreira.sampleapi.usecase.CreateTenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/tenant", consumes = "application/json", produces = "application/json")
public class TenantResource {

   private final CreateTenant createTenant;

   @Autowired
   public TenantResource(CreateTenant createTenant) {
      this.createTenant = createTenant;
   }

   @PostMapping
   @ResponseStatus(code = HttpStatus.CREATED)
   public void createTenant(@RequestBody CreateTenantRequest request) {
      createTenant.execute(request.toInput());
   }
}
