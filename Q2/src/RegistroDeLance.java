import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Linha do histórico do auditor: um lance aceito, com data e hora. */
public class RegistroDeLance {

    private static final DateTimeFormatter FORMATO_DA_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private final LocalDateTime dataHora;
    private final String nomeDoLote;
    private final String nomeDoLicitante;
    private final BigDecimal valor;

    public RegistroDeLance(LocalDateTime dataHora, String nomeDoLote, String nomeDoLicitante, BigDecimal valor) {
        this.dataHora = dataHora;
        this.nomeDoLote = nomeDoLote;
        this.nomeDoLicitante = nomeDoLicitante;
        this.valor = valor;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getNomeDoLote() {
        return nomeDoLote;
    }

    public String getNomeDoLicitante() {
        return nomeDoLicitante;
    }

    public BigDecimal getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return dataHora.format(FORMATO_DA_DATA) + " | lote '" + nomeDoLote + "' | " + nomeDoLicitante
                + " | " + FormatadorMonetario.formatar(valor);
    }
}