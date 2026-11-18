package Q1.src;
import java.math.BigDecimal;

public class ArmazenamentoAws implements ArmazenamentoDeObjetos {

    private final String identificador;
    private final int gigabytes;
    private final TabelaDePrecos tabelaDePrecos;

    public ArmazenamentoAws(String identificador, int gigabytes, TabelaDePrecos tabelaDePrecos) {
        this.identificador = identificador;
        this.gigabytes = gigabytes;
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
    public int getGigabytes() {
        return gigabytes;
    }

    @Override
    public BigDecimal getCustoMensal() {
        return tabelaDePrecos.custoDoArmazenamento(gigabytes);
    }
}