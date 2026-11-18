package Q1.src;
import java.math.BigDecimal;

public class FilaAzure implements FilaDeMensagens {

    private final String identificador;
    private final TabelaDePrecos tabelaDePrecos;

    public FilaAzure(String identificador, TabelaDePrecos tabelaDePrecos) {
        this.identificador = identificador;
        this.tabelaDePrecos = tabelaDePrecos;
    }

    @Override
    public String getIdentificador() {
        return identificador;
    }

    @Override
    public String getProvedor() {
        return FabricaAzure.NOME_DO_PROVEDOR;
    }

    @Override
    public BigDecimal getCustoMensal() {
        return tabelaDePrecos.custoDaFila();
    }
}