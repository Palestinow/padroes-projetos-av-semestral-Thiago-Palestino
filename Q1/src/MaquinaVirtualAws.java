package Q1.src;
import java.math.BigDecimal;

public class MaquinaVirtualAws implements MaquinaVirtual {

    private final String identificador;
    private final int quantidade;
    private final TabelaDePrecos tabelaDePrecos;

    public MaquinaVirtualAws(String identificador, int quantidade, TabelaDePrecos tabelaDePrecos) {
        this.identificador = identificador;
        this.quantidade = quantidade;
        this.tabelaDePrecos = tabelaDePrecos;
    }

    @Override
    public String getIdentificador() {
        return identificador;
    }

    @Override
    public String getProvedor() {
        return FabricaAws.NOME_DO_PROVEDOR;
    }

    @Override
    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public BigDecimal getCustoMensal() {
        return tabelaDePrecos.custoDasMaquinas(quantidade);
    }
}