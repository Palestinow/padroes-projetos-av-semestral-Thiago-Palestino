import java.math.BigDecimal;
import java.math.RoundingMode;

/** Seguro de transporte: 1,5% do valor de venda do lote (não do total acumulado dos serviços). */
public class SeguroDeTransporte extends ServicoAdicional {

    private static final BigDecimal PERCENTUAL = new BigDecimal("0.015");

    public SeguroDeTransporte(Fatura fatura) {
        super(fatura);
    }

    @Override
    protected String getDescricaoDoServico() {
        return "Seguro de transporte (1,5% do valor de venda)";
    }

    @Override
    protected BigDecimal calcularCusto() {
        return getValorDeVenda().multiply(PERCENTUAL).setScale(2, RoundingMode.HALF_UP);
    }
}