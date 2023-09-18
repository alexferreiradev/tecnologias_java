package dev.alexferreira.sampleapi.common.test;

import dev.alexferreira.sampleapi.common.container.CustomNoSqlContainer;
import dev.alexferreira.sampleapi.common.container.CustomDataBaseContainer;
import dev.alexferreira.sampleapi.common.container.DynamicPropertyConfigurableContainer;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

@ActiveProfiles("db")
@DataMongoTest(excludeAutoConfiguration = {
   EmbeddedMongoAutoConfiguration.class,
})
@AutoConfigureDataJpa
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BaseRepositoryIT {

   private static final DynamicPropertyConfigurableContainer noSqlContainer = new CustomNoSqlContainer();
   private static final DynamicPropertyConfigurableContainer sqlContainer = new CustomDataBaseContainer();

   @DynamicPropertySource
   static void datasourceProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
      noSqlContainer.configure(dynamicPropertyRegistry);
      sqlContainer.configure(dynamicPropertyRegistry);
   }
}
