import java.math.BigDecimal;

/** Embalagem especial: R$ 180,00 fixos. */
public class EmbalagemEspecial extends ServicoAdicional {

    private static final BigDecimal CUSTO_FIXO = new BigDecimal("180.00");

    public EmbalagemEspecial(Fatura fatura) {
        super(fatura);
    }

    @Override
    protected String getDescricaoDoServico() {
        return "Embalagem especial";
    }

    @Override
    protected BigDecimal calcularCusto() {
        return CUSTO_FIXO;
    }
}