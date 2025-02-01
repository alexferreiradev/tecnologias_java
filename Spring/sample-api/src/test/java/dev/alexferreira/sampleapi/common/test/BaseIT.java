package dev.alexferreira.sampleapi.common.test;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ActiveProfiles("dev")
@ContextConfiguration(initializers = {SpringInitializer.class})
public abstract class BaseIT {

//   @Autowired protected ServletWebServerApplicationContext webServerAppCtxt;
}
