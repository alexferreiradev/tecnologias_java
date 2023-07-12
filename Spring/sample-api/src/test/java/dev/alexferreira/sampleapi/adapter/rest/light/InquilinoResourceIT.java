package dev.alexferreira.sampleapi.adapter.rest.light;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.alexferreira.sampleapi.adapter.rest.InquilinoResource;
import dev.alexferreira.sampleapi.adapter.rest.request.CreateInquilinoRequest;
import dev.alexferreira.sampleapi.common.fixture.RequestFixtures;
import dev.alexferreira.sampleapi.common.test.light.BaseRest;
import dev.alexferreira.sampleapi.usecase.CreateInquilino;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {InquilinoResource.class})
class InquilinoResourceIT extends BaseRest {

   private final CreateInquilinoRequest request = RequestFixtures.createInquilinoRequest();

   @Autowired MockMvc mockMvc;
   @Autowired ObjectMapper objectMapper;

   @MockBean CreateInquilino createInquilino;

   @Test
   void createInquilino() throws Exception {
      Mockito.doNothing().when(createInquilino).execute(request.toInput());

      mockMvc.perform(post("/inquilinos").contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request))).andDo(MockMvcResultHandlers.print())
         .andExpect(status().is2xxSuccessful());
   }
}
