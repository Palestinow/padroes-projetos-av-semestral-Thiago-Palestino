public class CategoriaVeiculos implements CategoriaDeLote {

    @Override
    public String getNome() {
        return "Veículos";
    }

    @Override
    public RegraDeComissao regraDeComissaoPadrao() {
        return new ComissaoDeVeiculos();
    }
}