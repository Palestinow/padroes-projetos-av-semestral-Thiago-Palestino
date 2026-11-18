public class CategoriaArte implements CategoriaDeLote {

    @Override
    public String getNome() {
        return "Arte";
    }

    @Override
    public RegraDeComissao regraDeComissaoPadrao() {
        return new ComissaoDeArte();
    }
}