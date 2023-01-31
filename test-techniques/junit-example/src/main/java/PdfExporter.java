public class PdfExporter implements Exporter {

   private final ProdutoExporter produtoExporter;

   public PdfExporter(ProdutoExporter produtoExporter) {
      this.produtoExporter = produtoExporter;
   }

   @Override public String createPDF(String text) {
      if (text.isBlank()) {
         throw new IllegalArgumentException("produto vazio invalido");
      }

      return produtoExporter.exportProduto(text);
   }
}
