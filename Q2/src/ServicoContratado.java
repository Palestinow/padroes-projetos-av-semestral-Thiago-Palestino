import java.math.BigDecimal;

/** Linha de serviço adicional na fatura: descrição e custo. */
public class ServicoContratado {

    private final String descricao;
    private final BigDecimal custo;

    public ServicoContratado(String descricao, BigDecimal custo) {
        this.descricao = descricao;
        this.custo = custo;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getCusto() {
        return custo;
    }
}