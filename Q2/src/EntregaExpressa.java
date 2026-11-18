import java.math.BigDecimal;

/** Entrega expressa: R$ 450,00 fixos. */
public class EntregaExpressa extends ServicoAdicional {

    private static final BigDecimal CUSTO_FIXO = new BigDecimal("450.00");

    public EntregaExpressa(Fatura fatura) {
        super(fatura);
    }

    @Override
    protected String getDescricaoDoServico() {
        return "Entrega expressa";
    }

    @Override
    protected BigDecimal calcularCusto() {
        return CUSTO_FIXO;
    }
}