import java.math.BigDecimal;

/** Pessoa que acompanha lotes e dá lances. É Licitante (autor de lances) e Observer (recebe os lances dos outros). */
public class Participante implements Licitante, AcompanhanteDoLote {

    private final String nome;

    public Participante(String nome) {
        this.nome = nome;
    }

    @Override
    public String getNome() {
        return nome;
    }

    public void acompanhar(Lote lote) {
        lote.acompanhar(this);
    }

    public void deixarDeAcompanhar(Lote lote) {
        lote.deixarDeAcompanhar(this);
    }

    public ResultadoDoLance darLance(Lote lote, BigDecimal valor) {
        return lote.registrarLance(this, valor);
    }

    @Override
    public void aoAceitarLance(Lote lote, Lance lance) {
        System.out.println("   [Participante " + nome + "] Lote '" + lote.getNome() + "': novo lance de "
                + FormatadorMonetario.formatar(lance.getValor()));
    }
}
