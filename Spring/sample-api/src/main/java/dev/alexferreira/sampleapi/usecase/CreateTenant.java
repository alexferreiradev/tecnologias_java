package dev.alexferreira.sampleapi.usecase;

import dev.alexferreira.sampleapi.domain.tenant.ImagemTenantStorage;
import dev.alexferreira.sampleapi.domain.tenant.Tenant;
import dev.alexferreira.sampleapi.domain.tenant.TenantRepository;
import dev.alexferreira.sampleapi.domain.tenant.exception.TenantAlreadyExistsException;
import dev.alexferreira.sampleapi.usecase.input.CreateTenantInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CreateTenant {

   private final TenantRepository tenantRepository;
   private final ImagemTenantStorage imagemTenantStorage;

   @Autowired
   public CreateTenant(
           TenantRepository tenantRepository,
           ImagemTenantStorage imagemTenantStorage,
           @Value("\\${spring.kafka.producer.properties.topics.tenant}")
           String topicName
   ) {
      this.tenantRepository = tenantRepository;
      this.imagemTenantStorage = imagemTenantStorage;
   }

   @Transactional
   public void execute(CreateTenantInput input) {
      tenantRepository.findByDocument(input.document).ifPresent(inquilino -> {
         throw new TenantAlreadyExistsException();
      });

      Tenant tenant = new Tenant();
      tenant.setName(input.name);
      tenant.setTower(input.tower);
      tenant.setFlatNumber(input.flatNumber);
      tenant.setDocument(input.document);

      if (input.image != null) {
         tenant.setImagePath(imagemTenantStorage.save(tenant, input.image));
      }

      tenantRepository.save(tenant);
   }
}
