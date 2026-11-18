/**
 * Categoria de um lote. Cada categoria sabe qual é a sua regra de comissão inicial,
 * o que dispensa qualquer if/switch sobre a categoria no cálculo (RNF02).
 */
public interface CategoriaDeLote {

    String getNome();

    RegraDeComissao regraDeComissaoPadrao();
}
