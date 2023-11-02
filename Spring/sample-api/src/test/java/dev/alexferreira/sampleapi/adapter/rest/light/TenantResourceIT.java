package dev.alexferreira.sampleapi.adapter.rest.light;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.alexferreira.sampleapi.adapter.rest.TenantResource;
import dev.alexferreira.sampleapi.adapter.rest.request.CreateTenantRequest;
import dev.alexferreira.sampleapi.common.exception.response.ErrorEntitityResponse;
import dev.alexferreira.sampleapi.common.fixture.RequestFixtures;
import dev.alexferreira.sampleapi.common.test.light.BaseRest;
import dev.alexferreira.sampleapi.domain.tenant.exception.TenantAlreadyExistsException;
import dev.alexferreira.sampleapi.usecase.CreateTenant;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {TenantResource.class})
class TenantResourceIT extends BaseRest {

	private final CreateTenantRequest request = RequestFixtures.createTenantRequest();

	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	CreateTenant createTenant;
	private final String baseUrl = "/tenants";

	@Test
	void createInquilino() throws Exception {
		Mockito.doNothing().when(createTenant).execute(request.toInput());

		mockMvc.perform(post(baseUrl).contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(request))).andDo(MockMvcResultHandlers.print())
				.andExpect(status().is2xxSuccessful());
	}

	@Test
	void shouldReturnErrorResponse_whenUseCaseThrowDomainException() throws Exception {
		TenantAlreadyExistsException exception = new TenantAlreadyExistsException();
		Mockito.doThrow(exception).when(createTenant).execute(Mockito.any());

		mockMvc.perform(post(baseUrl).contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(request))).andDo(MockMvcResultHandlers.print())
				.andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$.message").value(exception.code.message()))
				.andExpect(jsonPath("$.code").value(exception.code.code()))
		;
	}

   @Test
   void shouldReturnErrorResponse_whenUseCaseThrowGenericException() throws Exception {
      RuntimeException exception = new RuntimeException();
      ErrorEntitityResponse errorEntitityResponse = new ErrorEntitityResponse();

	   Mockito.doThrow(exception).when(createTenant).execute(Mockito.any());

	   mockMvc.perform(post(baseUrl).contentType(MediaType.APPLICATION_JSON)
					   .content(objectMapper.writeValueAsString(request))).andDo(MockMvcResultHandlers.print())
			   .andExpect(status().is5xxServerError())
         .andExpect(jsonPath("$.message").value(errorEntitityResponse.message))
         .andExpect(jsonPath("$.code").value(errorEntitityResponse.code))
      ;
   }
}
