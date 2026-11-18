import java.math.BigDecimal;
import java.math.RoundingMode;

/** Arte: 20% do valor de venda. */
public class ComissaoDeArte implements RegraDeComissao {

    private static final BigDecimal PERCENTUAL = new BigDecimal("0.20");

    @Override
    public BigDecimal calcular(BigDecimal valorDeVenda) {
        return valorDeVenda.multiply(PERCENTUAL).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String getDescricao() {
        return "Arte: 20% do valor de venda";
    }
}