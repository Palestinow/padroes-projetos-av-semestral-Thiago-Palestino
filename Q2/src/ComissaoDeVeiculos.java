import java.math.BigDecimal;
import java.math.RoundingMode;

/** Veículos: 8% do valor de venda, limitada a R$ 5.000,00. */
public class ComissaoDeVeiculos implements RegraDeComissao {

    private static final BigDecimal PERCENTUAL = new BigDecimal("0.08");
    private static final BigDecimal TETO = new BigDecimal("5000.00");

    @Override
    public BigDecimal calcular(BigDecimal valorDeVenda) {
        return valorDeVenda.multiply(PERCENTUAL).setScale(2, RoundingMode.HALF_UP).min(TETO);
    }

    @Override
    public String getDescricao() {
        return "Veículos: 8% do valor de venda, limitada a " + FormatadorMonetario.formatar(TETO);
    }
}