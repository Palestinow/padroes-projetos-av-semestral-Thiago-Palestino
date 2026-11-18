/**
 * Observer: qualquer interessado em acompanhar um lote. O Lote só conhece esta interface,
 * nunca as classes concretas de participantes, painel ou auditor.
 */
public interface AcompanhanteDoLote {

    void aoAceitarLance(Lote lote, Lance lance);
}