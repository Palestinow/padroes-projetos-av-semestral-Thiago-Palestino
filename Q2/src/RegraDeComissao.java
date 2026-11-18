import java.math.BigDecimal;

/** Strategy: regra de cálculo da comissão cobrada do vendedor sobre o valor de venda. */
public interface RegraDeComissao {

    BigDecimal calcular(BigDecimal valorDeVenda);

    String getDescricao();
}