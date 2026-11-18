package Q1.src;
import java.math.BigDecimal;

/**
 * Cliente da fábrica abstrata. Recebe de fora a fábrica do provedor escolhido e só conhece
 * as interfaces dos recursos: não referencia nenhuma classe concreta de provedor (RNF02).
 */
public class MontadorDeAmbiente {

    private final FabricaDeRecursos fabrica;

    public MontadorDeAmbiente(FabricaDeRecursos fabrica) {
        this.fabrica = fabrica;
    }

    /** Verifica o orçamento antes de criar qualquer recurso; recusado, nada é criado. */
    public Ambiente montar(SolicitacaoDeAmbiente solicitacao) throws OrcamentoExcedidoException {
        ConfiguracaoDoSistema configuracao = ConfiguracaoDoSistema.obterInstancia();

        BigDecimal custoEstimado = fabrica.getTabelaDePrecos().custoMensal(
                solicitacao.getQuantidadeDeMaquinas(), solicitacao.getGigabytesDeArmazenamento());
        BigDecimal orcamentoMaximo = configuracao.getOrcamentoMensalMaximo();
        if (custoEstimado.compareTo(orcamentoMaximo) > 0) {
            throw new OrcamentoExcedidoException(custoEstimado, orcamentoMaximo);
        }

        MaquinaVirtual maquinasVirtuais = fabrica.criarMaquinasVirtuais(solicitacao.getQuantidadeDeMaquinas());
        ArmazenamentoDeObjetos armazenamento = fabrica.criarArmazenamento(solicitacao.getGigabytesDeArmazenamento());
        FilaDeMensagens fila = fabrica.criarFila();

        return new Ambiente(solicitacao.getNomeDoAmbiente(), configuracao.getRegiaoPadrao(),
                maquinasVirtuais, armazenamento, fila);
    }
}