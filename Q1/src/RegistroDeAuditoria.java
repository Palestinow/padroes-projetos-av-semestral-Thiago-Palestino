package Q1.src;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** Registro imutável de um provisionamento (aceito ou recusado). */
public final class RegistroDeAuditoria {

    private static final DateTimeFormatter FORMATO_DA_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS");

    private final LocalDateTime dataHora;
    private final String nomeDoAmbiente;
    private final String provedor;
    private final ResultadoDoProvisionamento resultado;
    private final String motivo;

    public RegistroDeAuditoria(LocalDateTime dataHora, String nomeDoAmbiente, String provedor,
                               ResultadoDoProvisionamento resultado, String motivo) {
        this.dataHora = dataHora;
        this.nomeDoAmbiente = nomeDoAmbiente;
        this.provedor = provedor;
        this.resultado = resultado;
        this.motivo = motivo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getNomeDoAmbiente() {
        return nomeDoAmbiente;
    }

    public String getProvedor() {
        return provedor;
    }

    public ResultadoDoProvisionamento getResultado() {
        return resultado;
    }

    public String getMotivo() {
        return motivo;
    }

    @Override
    public String toString() {
        String texto = dataHora.format(FORMATO_DA_DATA) + " | ambiente: " + nomeDoAmbiente
                + " | provedor: " + provedor + " | resultado: " + resultado;
        return motivo.isEmpty() ? texto : texto + " (" + motivo + ")";
    }
}