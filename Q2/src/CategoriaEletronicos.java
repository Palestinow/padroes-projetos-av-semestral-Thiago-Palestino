public class CategoriaEletronicos implements CategoriaDeLote {

    @Override
    public String getNome() {
        return "Eletrônicos";
    }

    @Override
    public RegraDeComissao regraDeComissaoPadrao() {
        return new ComissaoDeEletronicos();
    }
}