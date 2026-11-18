import java.math.BigDecimal;
import java.math.RoundingMode;

/** Imóveis: 3% do valor de venda mais R$ 1.500,00 fixos. */
public class ComissaoDeImoveis implements RegraDeComissao {

    private static final BigDecimal PERCENTUAL = new BigDecimal("0.03");
    private static final BigDecimal TAXA_FIXA = new BigDecimal("1500.00");

    @Override
    public BigDecimal calcular(BigDecimal valorDeVenda) {
        return valorDeVenda.multiply(PERCENTUAL).setScale(2, RoundingMode.HALF_UP).add(TAXA_FIXA);
    }

    @Override
    public String getDescricao() {
        return "Imóveis: 3% do valor de venda mais " + FormatadorMonetario.formatar(TAXA_FIXA) + " fixos";
    }
}