import java.math.BigDecimal;

/** Acerto com o vendedor de um lote vendido: valor de venda, comissão cobrada e valor líquido a repassar. */
public class AcertoDoVendedor {

    private final String nomeDoLote;
    private final String descricaoDaRegra;
    private final BigDecimal valorDeVenda;
    private final BigDecimal comissaoCobrada;

    public AcertoDoVendedor(Lote lote) {
        this.nomeDoLote = lote.getNome();
        this.descricaoDaRegra = lote.getRegraDeComissao().getDescricao();
        this.valorDeVenda = lote.getValorDeVenda();
        this.comissaoCobrada = lote.calcularComissao();
    }

    public String getNomeDoLote() {
        return nomeDoLote;
    }

    public String getDescricaoDaRegra() {
        return descricaoDaRegra;
    }

    public BigDecimal getValorDeVenda() {
        return valorDeVenda;
    }

    public BigDecimal getComissaoCobrada() {
        return comissaoCobrada;
    }

    public BigDecimal getValorLiquido() {
        return valorDeVenda.subtract(comissaoCobrada);
    }
}