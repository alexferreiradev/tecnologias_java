package dev.alexferreira.sampleapi.test.common;

import dev.alexferreira.sampleapi.test.common.spring.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = Initializer.class)
public abstract class IntegrationTest {}
