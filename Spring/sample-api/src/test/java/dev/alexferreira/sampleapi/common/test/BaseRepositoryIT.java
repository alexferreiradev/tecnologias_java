package dev.alexferreira.sampleapi.common.test;

import dev.alexferreira.sampleapi.common.container.CustomSqlDataBaseContainer;
import dev.alexferreira.sampleapi.common.container.DynamicPropertyConfigurableContainer;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

@ActiveProfiles("dev")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BaseRepositoryIT {

   private static final DynamicPropertyConfigurableContainer sqlContainer = new CustomSqlDataBaseContainer();

   @DynamicPropertySource
   static void datasourceProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
      sqlContainer.configure(dynamicPropertyRegistry);
   }
}
