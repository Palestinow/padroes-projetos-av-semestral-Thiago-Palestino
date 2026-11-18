import java.math.BigDecimal;
import java.math.RoundingMode;

/** Eletrônicos: 12% do valor de venda, com mínimo de R$ 50,00. */
public class ComissaoDeEletronicos implements RegraDeComissao {

    private static final BigDecimal PERCENTUAL = new BigDecimal("0.12");
    private static final BigDecimal MINIMO = new BigDecimal("50.00");

    @Override
    public BigDecimal calcular(BigDecimal valorDeVenda) {
        return valorDeVenda.multiply(PERCENTUAL).setScale(2, RoundingMode.HALF_UP).max(MINIMO);
    }

    @Override
    public String getDescricao() {
        return "Eletrônicos: 12% do valor de venda, com mínimo de " + FormatadorMonetario.formatar(MINIMO);
    }
}