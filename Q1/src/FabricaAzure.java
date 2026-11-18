package Q1.src;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

/** Fábrica concreta do provedor Azure. Os contadores são atômicos para suportar threads simultâneas. */
public class FabricaAzure implements FabricaDeRecursos {

    public static final String NOME_DO_PROVEDOR = "Azure";

    private static final String PREFIXO_MAQUINA = "vm-";
    private static final String PREFIXO_ARMAZENAMENTO = "blob://";
    private static final String PREFIXO_FILA = "sb-";

    private final TabelaDePrecos tabelaDePrecos = new TabelaDePrecos(
            new BigDecimal("0.12"), new BigDecimal("0.02"), new BigDecimal("0.50"));

    private final AtomicInteger sequenciaDasMaquinas = new AtomicInteger();
    private final AtomicInteger sequenciaDosArmazenamentos = new AtomicInteger();
    private final AtomicInteger sequenciaDasFilas = new AtomicInteger();

    @Override
    public String getNomeDoProvedor() {
        return NOME_DO_PROVEDOR;
    }

    @Override
    public TabelaDePrecos getTabelaDePrecos() {
        return tabelaDePrecos;
    }

    @Override
    public MaquinaVirtual criarMaquinasVirtuais(int quantidade) {
        String identificador = PREFIXO_MAQUINA + sequenciaDasMaquinas.incrementAndGet();
        return new MaquinaVirtualAzure(identificador, quantidade, tabelaDePrecos);
    }

    @Override
    public ArmazenamentoDeObjetos criarArmazenamento(int gigabytes) {
        String identificador = PREFIXO_ARMAZENAMENTO + sequenciaDosArmazenamentos.incrementAndGet();
        return new ArmazenamentoAzure(identificador, gigabytes, tabelaDePrecos);
    }

    @Override
    public FilaDeMensagens criarFila() {
        String identificador = PREFIXO_FILA + sequenciaDasFilas.incrementAndGet();
        return new FilaAzure(identificador, tabelaDePrecos);
    }
}