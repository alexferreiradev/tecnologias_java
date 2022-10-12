package dev.alexferreira.sampleapi.common;

import org.springframework.boot.test.context.*;
import org.springframework.test.context.*;

@SpringBootTest
@ContextConfiguration(initializers = {
   ContainerInitializer.class
})
public class IntegrationTest {

}
