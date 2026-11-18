import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Decorator: envolve uma Fatura e acrescenta a ela um serviço (descrição e custo).
 * Os serviços concretos só precisam informar descrição e custo; o encadeamento é feito aqui.
 */
public abstract class ServicoAdicional implements Fatura {

    private final Fatura faturaEnvolvida;

    protected ServicoAdicional(Fatura faturaEnvolvida) {
        this.faturaEnvolvida = faturaEnvolvida;
    }

    protected abstract String getDescricaoDoServico();

    protected abstract BigDecimal calcularCusto();

    @Override
    public String getDescricaoDoLote() {
        return faturaEnvolvida.getDescricaoDoLote();
    }

    @Override
    public String getNomeDoComprador() {
        return faturaEnvolvida.getNomeDoComprador();
    }

    @Override
    public BigDecimal getValorDeVenda() {
        return faturaEnvolvida.getValorDeVenda();
    }

    @Override
    public List<ServicoContratado> getServicosContratados() {
        List<ServicoContratado> servicos = new ArrayList<>(faturaEnvolvida.getServicosContratados());
        servicos.add(new ServicoContratado(getDescricaoDoServico(), calcularCusto()));
        return Collections.unmodifiableList(servicos);
    }

    @Override
    public BigDecimal getTotalAPagar() {
        return faturaEnvolvida.getTotalAPagar().add(calcularCusto());
    }
}