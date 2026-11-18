package Q1.src;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

/** Fábrica concreta do provedor Aws. Os contadores são atômicos para suportar threads simultâneas. */
public class FabricaAws implements FabricaDeRecursos {

    public static final String NOME_DO_PROVEDOR = "Aws";

    private static final String PREFIXO_MAQUINA = "i-";
    private static final String PREFIXO_ARMAZENAMENTO = "s3://";
    private static final String PREFIXO_FILA = "sqs-";

    private final TabelaDePrecos tabelaDePrecos = new TabelaDePrecos(
            new BigDecimal("0.10"), new BigDecimal("0.023"), new BigDecimal("0.40"));

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
        return new MaquinaVirtualAws(identificador, quantidade, tabelaDePrecos);
    }

    @Override
    public ArmazenamentoDeObjetos criarArmazenamento(int gigabytes) {
        String identificador = PREFIXO_ARMAZENAMENTO + sequenciaDosArmazenamentos.incrementAndGet();
        return new ArmazenamentoAws(identificador, gigabytes, tabelaDePrecos);
    }

    @Override
    public FilaDeMensagens criarFila() {
        String identificador = PREFIXO_FILA + sequenciaDasFilas.incrementAndGet();
        return new FilaAws(identificador, tabelaDePrecos);
    }
}