public class PdfExporter implements Exporter {

   private final ProdutoExporter produtoExporter;

   public PdfExporter(ProdutoExporterImpl produtoExporter) {
      this.produtoExporter = produtoExporter;
   }

   @Override public String export(String text) {
      if (text.isEmpty()) {
         throw new IllegalArgumentException("produto vazio invalido");
      }

      return produtoExporter.export(text);
   }
}
