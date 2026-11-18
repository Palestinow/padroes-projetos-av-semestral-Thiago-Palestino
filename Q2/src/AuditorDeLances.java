import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Interessado que registra todos os lances aceitos, com data e hora. */
public class AuditorDeLances implements AcompanhanteDoLote {

    private final List<RegistroDeLance> registros = new ArrayList<>();

    @Override
    public void aoAceitarLance(Lote lote, Lance lance) {
        RegistroDeLance registro = new RegistroDeLance(
                LocalDateTime.now(), lote.getNome(), lance.getLicitante().getNome(), lance.getValor());
        registros.add(registro);
        System.out.println("   [Auditor] lance registrado (" + registros.size() + " no total)");
    }

    public List<RegistroDeLance> getRegistros() {
        return Collections.unmodifiableList(registros);
    }
}