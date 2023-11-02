package dev.alexferreira.sampleapi.adapter.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.alexferreira.sampleapi.adapter.rest.request.CreateTenantRequest;
import dev.alexferreira.sampleapi.common.fixture.RequestFixtures;
import dev.alexferreira.sampleapi.common.test.BaseIT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TenantResourceIT extends BaseIT {

	private final CreateTenantRequest request = RequestFixtures.createTenantRequest();

	@Autowired
	ObjectMapper objectMapper;

	RestTemplate restTemplate = new RestTemplate();
	private URI baseUrl;
	private HttpEntity<String> entity;

	@BeforeEach
	void setUp() throws JsonProcessingException {
		baseUrl = URI.create("http://localhost:" + webServerAppCtxt.getWebServer().getPort() + "/tenants");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		String requestText = objectMapper.writeValueAsString(request);
		entity = new HttpEntity<>(requestText, headers);
	}

	@Test
	void shouldCreateUser_whenRequestIsValid() throws Exception {
		ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, entity, String.class);

		assertEquals(201, response.getStatusCode());
	}

	@Test
	void shouldReturnErrorResponse_whenUseCaseThrowDomainException() throws Exception {
//      InquilinoExistenteException exception = new InquilinoExistenteException();
//      Mockito.doThrow(exception).when(createInquilino).execute(Mockito.any());
//
//      mockMvc.perform(post("/inquilinos").contentType(MediaType.APPLICATION_JSON)
//            .content(objectMapper.writeValueAsString(request))).andDo(MockMvcResultHandlers.print())
//         .andExpect(status().is2xxSuccessful())
//         .andExpect(jsonPath("$.message").value(exception.code.message()))
//         .andExpect(jsonPath("$.code").value(exception.code.code()))
//      ;
	}

	@Test
	void shouldReturnErrorResponse_whenUseCaseThrowGenericException() throws Exception {
//      RuntimeException exception = new RuntimeException();
//      ErrorEntitityResponse errorEntitityResponse = new ErrorEntitityResponse();
//
//      Mockito.doThrow(exception).when(createInquilino).execute(Mockito.any());
//
//      mockMvc.perform(post("/inquilinos").contentType(MediaType.APPLICATION_JSON)
//            .content(objectMapper.writeValueAsString(request))).andDo(MockMvcResultHandlers.print())
//         .andExpect(status().is2xxSuccessful())
//         .andExpect(jsonPath("$.message").value(errorEntitityResponse.message))
//         .andExpect(jsonPath("$.code").value(errorEntitityResponse.code))
//      ;
	}
}
