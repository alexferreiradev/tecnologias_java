package dev.alexferreira.sampleapi.test.util;

import dev.alexferreira.sampleapi.test.spring.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.*;

@ActiveProfiles("test")
@SpringBootTest
@ContextConfiguration(initializers = Initializer.class)
public abstract class IntegrationTest {}
