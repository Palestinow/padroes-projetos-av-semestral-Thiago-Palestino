/** Orquestra um provisionamento: monta o ambiente, registra a auditoria e imprime o resultado. */
package Q1.src;

public class ProvisionadorDeAmbiente {

    private final MontadorDeAmbiente montador;

    public ProvisionadorDeAmbiente(MontadorDeAmbiente montador) {
        this.montador = montador;
    }

    public void provisionar(SolicitacaoDeAmbiente solicitacao) {
        HistoricoDeAuditoria historico = HistoricoDeAuditoria.obterInstancia();
        try {
            Ambiente ambiente = montador.montar(solicitacao);
            historico.registrarAceite(ambiente.getNome(), ambiente.getProvedor());
            System.out.println(montarResumo(ambiente));
        } catch (OrcamentoExcedidoException recusa) {
            historico.registrarRecusa(solicitacao.getNomeDoAmbiente(), solicitacao.getProvedor(), recusa.getMessage());
            System.out.println(montarAvisoDeRecusa(solicitacao, recusa));
        }
    }

    // O texto é montado por inteiro e impresso de uma só vez para que threads simultâneas não intercalem linhas.
    private String montarResumo(Ambiente ambiente) {
        return String.join(System.lineSeparator(),
                "=== Ambiente provisionado ===",
                "Ambiente: " + ambiente.getNome(),
                "Provedor: " + ambiente.getProvedor(),
                "Região: " + ambiente.getRegiao(),
                "Máquinas virtuais (" + ambiente.getMaquinasVirtuais().getQuantidade() + "): "
                        + ambiente.getMaquinasVirtuais().getIdentificador(),
                "Armazenamento (" + ambiente.getArmazenamento().getGigabytes() + " GB): "
                        + ambiente.getArmazenamento().getIdentificador(),
                "Fila de mensagens: " + ambiente.getFila().getIdentificador(),
                "Custo mensal estimado: " + FormatadorMonetario.formatar(ambiente.getCustoMensal()),
                "");
    }

    private String montarAvisoDeRecusa(SolicitacaoDeAmbiente solicitacao, OrcamentoExcedidoException recusa) {
        return String.join(System.lineSeparator(),
                "=== Provisionamento recusado ===",
                "Ambiente: " + solicitacao.getNomeDoAmbiente(),
                "Provedor: " + solicitacao.getProvedor(),
                "Motivo: " + recusa.getMessage(),
                "");
    }
}