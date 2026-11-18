public class CategoriaImoveis implements CategoriaDeLote {

    @Override
    public String getNome() {
        return "Imóveis";
    }

    @Override
    public RegraDeComissao regraDeComissaoPadrao() {
        return new ComissaoDeImoveis();
    }
}