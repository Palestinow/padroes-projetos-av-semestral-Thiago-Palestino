import java.math.BigDecimal;

/** Certificado de autenticidade: R$ 300,00 fixos. */
public class CertificadoDeAutenticidade extends ServicoAdicional {

    private static final BigDecimal CUSTO_FIXO = new BigDecimal("300.00");

    public CertificadoDeAutenticidade(Fatura fatura) {
        super(fatura);
    }

    @Override
    protected String getDescricaoDoServico() {
        return "Certificado de autenticidade";
    }

    @Override
    protected BigDecimal calcularCusto() {
        return CUSTO_FIXO;
    }
}