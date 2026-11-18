import java.math.BigDecimal;
import java.util.List;

/** ConcreteComponent: fatura de um lote vendido, sem nenhum serviço adicional. */
public class FaturaDoLote implements Fatura {

    private final String descricaoDoLote;
    private final String nomeDoComprador;
    private final BigDecimal valorDeVenda;

    public FaturaDoLote(Lote lote) {
        this.descricaoDoLote = lote.getNome() + " (" + lote.getCategoria().getNome() + ")";
        this.nomeDoComprador = lote.getComprador().getNome();
        this.valorDeVenda = lote.getValorDeVenda();
    }

    @Override
    public String getDescricaoDoLote() {
        return descricaoDoLote;
    }

    @Override
    public String getNomeDoComprador() {
        return nomeDoComprador;
    }

    @Override
    public BigDecimal getValorDeVenda() {
        return valorDeVenda;
    }

    @Override
    public List<ServicoContratado> getServicosContratados() {
        return List.of();
    }

    @Override
    public BigDecimal getTotalAPagar() {
        return valorDeVenda;
    }
}