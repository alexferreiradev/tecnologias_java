package dev.alexferreira.sampleapi.adapter.rest.light;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.alexferreira.sampleapi.adapter.rest.AuthorizationResource;
import dev.alexferreira.sampleapi.adapter.rest.request.CreateAuthorizationRequest;
import dev.alexferreira.sampleapi.common.exception.response.ErrorEntitityResponse;
import dev.alexferreira.sampleapi.common.fixture.RequestFixtures;
import dev.alexferreira.sampleapi.common.test.light.BaseRest;
import dev.alexferreira.sampleapi.domain.inquilino.exception.InquilinoExistenteException;
import dev.alexferreira.sampleapi.usecase.CreateAuthorization;
import dev.alexferreira.sampleapi.usecase.input.CreateAuthorizationInput;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
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

@WebMvcTest(AuthorizationResource.class)
class AuthorizationResourceIT extends BaseRest {
   private final CreateAuthorizationRequest request = RequestFixtures.createAuthorizationRequest();

   @Autowired MockMvc mockMvc;
   @Autowired ObjectMapper objectMapper;

   @MockBean CreateAuthorization useCase;
   private final String baseUrl = "/authorizations";

   @Test
   void shouldReturn200_whenRequestIsValid() throws Exception {
      String authorizationId = "authorizationId";
      ArgumentCaptor<CreateAuthorizationInput> captor = ArgumentCaptor.forClass(CreateAuthorizationInput.class);
      Mockito.when(useCase.execute(captor.capture())).thenReturn(authorizationId);

      mockMvc.perform(post(baseUrl).contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request))).andDo(MockMvcResultHandlers.print())
         .andExpect(status().is2xxSuccessful())
         .andExpect(jsonPath("$.authorizationId").value(authorizationId))
      ;
   }

   @Test
   void shouldReturnErrorResponse_whenUseCaseThrowDomainException() throws Exception {
      InquilinoExistenteException exception = new InquilinoExistenteException();
      Mockito.doThrow(exception).when(useCase).execute(Mockito.any());

      mockMvc.perform(post(baseUrl).contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request))).andDo(MockMvcResultHandlers.print())
         .andExpect(status().is2xxSuccessful())
         .andExpect(jsonPath("$.message").value(exception.code.message()))
         .andExpect(jsonPath("$.code").value(exception.code.code()))
      ;
   }

   @Test
   void shouldReturnErrorResponse_whenUseCaseThrowGenericException() throws Exception {
      RuntimeException exception = new RuntimeException();
      ErrorEntitityResponse errorEntitityResponse = new ErrorEntitityResponse();

      Mockito.doThrow(exception).when(useCase).execute(Mockito.any());

      mockMvc.perform(post(baseUrl).contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request))).andDo(MockMvcResultHandlers.print())
         .andExpect(status().is2xxSuccessful())
         .andExpect(jsonPath("$.message").value(errorEntitityResponse.message))
         .andExpect(jsonPath("$.code").value(errorEntitityResponse.code))
      ;
   }
}
