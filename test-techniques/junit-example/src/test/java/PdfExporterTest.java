import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.junit.jupiter.api.function.*;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;


class PdfExporterTest {

   @Mock
   ProdutoExporterImpl produtoExporter;

   @InjectMocks
   PdfExporter pdfExporter;

   @BeforeEach void setUp() {
      MockitoAnnotations.openMocks(this);
   }

   @AfterEach void tearDown() {
      Mockito.verifyNoMoreInteractions(produtoExporter);
   }

   @Test void deveRetornarPDFValid_whenCallExport() {
      String produto = "valid produto";
      String validProdutoExportado = "valid produto";
      Mockito.when(produtoExporter.export(produto)).thenReturn(validProdutoExportado);

      String export = pdfExporter.export(produto);

      Assertions.assertEquals(validProdutoExportado, export);

      Mockito.verify(produtoExporter).export(Mockito.eq(produto));
   }

   @Test void deveRetornarPDFInvalido_whenProdutoExporterReturnInvalidTexto() {
      String produto = "valid produto";
      String invalidProdutoExportado = "invalid produto";
      Mockito.when(produtoExporter.export(produto)).thenReturn(invalidProdutoExportado);

      String export = pdfExporter.export(produto);

      Assertions.assertEquals(invalidProdutoExportado, export);
   }

   @Test void deveRetornarErro_whenProdutoExporterLancarErro() {
      String produto = "valid produto";
      Mockito.when(produtoExporter.export(produto)).thenThrow(new IllegalArgumentException("invalid product"));

      IllegalArgumentException exception =
         assertThrows(IllegalArgumentException.class, () -> pdfExporter.export(produto));

      Assertions.assertEquals("invalid product", exception.getMessage());
   }

   @Test void deveRetornarErro_whenProdutoSejaVazio() {
      String produto = "";

      IllegalArgumentException exception =
         assertThrows(IllegalArgumentException.class, () -> pdfExporter.export(produto));

      Assertions.assertEquals("produto vazio invalido", exception.getMessage());
   }
}
