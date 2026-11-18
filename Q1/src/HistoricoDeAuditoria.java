package Q1.src;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Singleton do histórico de auditoria. A data e hora do registro é obtida dentro da região
 * sincronizada, de modo que a ordem da lista seja exatamente a ordem em que os registros
 * foram efetivamente realizados, sem perda nem corrupção sob concorrência.
 */
public final class HistoricoDeAuditoria {

    private final Object trava = new Object();
    private final List<RegistroDeAuditoria> registros = new ArrayList<>();

    private HistoricoDeAuditoria() {
    }

    private static final class Instancia {
        private static final HistoricoDeAuditoria UNICA = new HistoricoDeAuditoria();
    }

    public static HistoricoDeAuditoria obterInstancia() {
        return Instancia.UNICA;
    }

    public void registrarAceite(String nomeDoAmbiente, String provedor) {
        registrar(nomeDoAmbiente, provedor, ResultadoDoProvisionamento.ACEITO, "");
    }

    public void registrarRecusa(String nomeDoAmbiente, String provedor, String motivo) {
        registrar(nomeDoAmbiente, provedor, ResultadoDoProvisionamento.RECUSADO, motivo);
    }

    private void registrar(String nomeDoAmbiente, String provedor,
                           ResultadoDoProvisionamento resultado, String motivo) {
        synchronized (trava) {
            registros.add(new RegistroDeAuditoria(LocalDateTime.now(), nomeDoAmbiente, provedor, resultado, motivo));
        }
    }

    /** Devolve uma cópia imutável na ordem em que os registros foram feitos. */
    public List<RegistroDeAuditoria> getRegistros() {
        synchronized (trava) {
            return List.copyOf(registros);
        }
    }
}