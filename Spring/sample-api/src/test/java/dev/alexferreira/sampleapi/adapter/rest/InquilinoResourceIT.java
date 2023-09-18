package dev.alexferreira.sampleapi.adapter.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.alexferreira.sampleapi.adapter.rest.request.CreateAuthorizationRequest;
import dev.alexferreira.sampleapi.adapter.rest.request.CreateInquilinoRequest;
import dev.alexferreira.sampleapi.common.exception.response.ErrorEntitityResponse;
import dev.alexferreira.sampleapi.common.fixture.RequestFixtures;
import dev.alexferreira.sampleapi.common.test.BaseIT;
import dev.alexferreira.sampleapi.domain.inquilino.exception.InquilinoExistenteException;
import dev.alexferreira.sampleapi.usecase.CreateInquilino;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class InquilinoResourceIT extends BaseIT {

   private final CreateAuthorizationRequest request = RequestFixtures.createAuthorizationRequest();

   @Autowired MockMvc mockMvc;
   @Autowired ObjectMapper objectMapper;

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
