package dev.alexferreira.sampleapi.adapter.rest;

import dev.alexferreira.sampleapi.adapter.rest.request.CreateInquilinoRequest;
import dev.alexferreira.sampleapi.common.fixture.RequestFixtures;
import dev.alexferreira.sampleapi.common.test.BaseIT;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

class InquilinoResourceIT extends BaseIT {

   private final CreateInquilinoRequest request = RequestFixtures.createInquilinoRequest();

   private String baseUrl = "http://localhost:";

   @BeforeEach
   void setUp() {
      baseUrl += webServerAppCtxt.getWebServer().getPort();
   }

   @Test
   void createInquilino() {
      RestTemplate restTemplate = new RestTemplate();
      MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
      header.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
      HttpEntity<CreateInquilinoRequest> request = new HttpEntity<>(this.request, header);

      ResponseEntity<Void> response = restTemplate.postForEntity(baseUrl + "/inquilinos", request, Void.class);
      Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED);
   }
}
