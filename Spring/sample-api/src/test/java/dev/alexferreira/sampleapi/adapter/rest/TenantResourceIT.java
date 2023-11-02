package dev.alexferreira.sampleapi.adapter.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.alexferreira.sampleapi.adapter.rest.request.CreateAuthorizationRequest;
import dev.alexferreira.sampleapi.common.fixture.RequestFixtures;
import dev.alexferreira.sampleapi.common.test.BaseIT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

class TenantResourceIT extends BaseIT {

	private final CreateAuthorizationRequest request = RequestFixtures.createAuthorizationRequest();

	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper objectMapper;

	@Test
	void shouldCreateUser_whenRequestIsValid() throws Exception {
//      Mockito.doNothing().when(createInquilino).execute(request.toInput());
//
//      mockMvc.perform(post("/inquilinos").contentType(MediaType.APPLICATION_JSON)
//            .content(objectMapper.writeValueAsString(request))).andDo(MockMvcResultHandlers.print())
//         .andExpect(status().is2xxSuccessful());
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
