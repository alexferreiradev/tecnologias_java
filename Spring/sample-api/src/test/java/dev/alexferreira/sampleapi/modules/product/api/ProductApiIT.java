package dev.alexferreira.sampleapi.modules.product.api;

import com.fasterxml.jackson.databind.*;
import dev.alexferreira.sampleapi.modules.product.dto.*;
import dev.alexferreira.sampleapi.modules.product.service.*;
import dev.alexferreira.sampleapi.test.common.*;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;
import org.springframework.test.web.servlet.result.*;
import org.springframework.test.web.servlet.setup.*;
import org.springframework.web.context.*;

import static org.junit.jupiter.api.Assertions.*;

class ProductApiIT extends IntegrationTest {

   @MockBean ProductService productService;
   @Autowired WebApplicationContext webApplicationContext;
   ObjectMapper objectMapper = new ObjectMapper();
   MockMvc mockMvc;
   ProdutoDTO dto = new ProdutoDTO();

   @BeforeEach
   void setUp() {
      mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
      dto.id = 1L;
      dto.name = "test";
   }

   @Test
   void registerProduct() throws Exception {
      Mockito.doThrow(new IllegalArgumentException()).when(productService).saveProduct(Mockito.isA(ProdutoDTO.class));

      String content = objectMapper.writeValueAsString(dto);
      MvcResult mvcResult = mockMvc
         .perform(MockMvcRequestBuilders.post("/products").contentType(MediaType.APPLICATION_JSON).content(content))
         .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().is5xxServerError()).andReturn();

      assertNotNull(mvcResult);
      Mockito.verify(productService).saveProduct(Mockito.eq(dto));
   }
}
