import org.junit.jupiter.api.*;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;


class PdfExporterTest {

   @Mock
   ProdutoExporter produtoCreator;

   @InjectMocks
   PdfExporter pdfExporter;

   @BeforeEach void setUp() {
      MockitoAnnotations.openMocks(this);
   }

   @AfterEach void tearDown() {
      Mockito.verifyNoMoreInteractions(produtoCreator);
   }

   @Test void deveRetornarPDFValid_QuandoChamarExportComParametroValido() {
      String produto = "valid produto";
      String pdfValido = "pdf valido";
      Mockito.when(produtoCreator.exportProduto(produto)).thenReturn(pdfValido);

      String export = pdfExporter.createPDF(produto);

      Assertions.assertEquals(pdfValido, export);

      Mockito.verify(produtoCreator).exportProduto(Mockito.eq(produto));
   }

   @Test void deveRetornarPDFInvalido_whenProdutoExporterReturnInvalidTexto() {
      String produto = "valid produto";
      String invalidProdutoExportado = "invalid produto";
      Mockito.when(produtoCreator.exportProduto(produto)).thenReturn(invalidProdutoExportado);

      String export = pdfExporter.createPDF(produto);

      Assertions.assertEquals(invalidProdutoExportado, export);
   }

   @Test void deveRetornarErro_whenProdutoExporterLancarErro() {
      String produto = "valid produto";
      Mockito.when(produtoCreator.exportProduto(produto)).thenThrow(new IllegalArgumentException("invalid product"));

      IllegalArgumentException exception =
         assertThrows(IllegalArgumentException.class, () -> pdfExporter.createPDF(produto));

      Assertions.assertEquals("invalid product", exception.getMessage());
   }

   @Test void deveRetornarErro_quandoProdutoSejaVazio() {
      String produto = "";

      IllegalArgumentException exception =
         assertThrows(IllegalArgumentException.class, () -> pdfExporter.createPDF(produto));

      Assertions.assertEquals("produto vazio invalido", exception.getMessage());
   }
}
