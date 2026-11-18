/** Interessado que exibe sempre o maior lance e o total de lances do lote, consultando o próprio lote. */
public class PainelDoLeiloeiro implements AcompanhanteDoLote {

    @Override
    public void aoAceitarLance(Lote lote, Lance lance) {
        System.out.println("   [Painel do leiloeiro] Lote '" + lote.getNome() + "' | maior lance: "
                + FormatadorMonetario.formatar(lote.getLanceAtual())
                + " | total de lances: " + lote.getQuantidadeDeLances());
    }
}