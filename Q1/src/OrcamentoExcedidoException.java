package Q1.src;
import java.math.BigDecimal;

/** Sinaliza uma solicitação cujo custo estimado ultrapassa o orçamento mensal máximo. */
public class OrcamentoExcedidoException extends Exception {

    private static final long serialVersionUID = 1L;

    private final BigDecimal custoEstimado;
    private final BigDecimal orcamentoMaximo;

    public OrcamentoExcedidoException(BigDecimal custoEstimado, BigDecimal orcamentoMaximo) {
        super("custo mensal estimado de " + FormatadorMonetario.formatar(custoEstimado)
                + " acima do orçamento máximo de " + FormatadorMonetario.formatar(orcamentoMaximo));
        this.custoEstimado = custoEstimado;
        this.orcamentoMaximo = orcamentoMaximo;
    }

    public BigDecimal getCustoEstimado() {
        return custoEstimado;
    }

    public BigDecimal getOrcamentoMaximo() {
        return orcamentoMaximo;
    }
}