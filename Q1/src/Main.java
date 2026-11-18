package Q1.src;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Demonstração do RF08: quatro provisionamentos (dois na Aws, um no Azure e um recusado por orçamento)
 * disparados ao mesmo tempo em threads distintas. A Main é, junto com a escolha da fábrica, o único
 * ponto que conhece as fábricas concretas (RNF03 e RNF06).
 */
public class Main {

    private static final Map<String, FabricaDeRecursos> FABRICAS_POR_PROVEDOR = new HashMap<>();

    static {
        // Para incluir um novo provedor (ex.: Gcp), basta acrescentar sua fábrica nesta lista.
        List<FabricaDeRecursos> fabricasDisponiveis = List.of(new FabricaAws(), new FabricaAzure());
        for (FabricaDeRecursos fabrica : fabricasDisponiveis) {
            FABRICAS_POR_PROVEDOR.put(fabrica.getNomeDoProvedor(), fabrica);
        }
    }

    private static FabricaDeRecursos escolherFabrica(String provedor) {
        FabricaDeRecursos fabrica = FABRICAS_POR_PROVEDOR.get(provedor);
        if (fabrica == null) {
            throw new IllegalArgumentException("Provedor não suportado: " + provedor);
        }
        return fabrica;
    }

    public static void main(String[] args) throws InterruptedException {
        List<SolicitacaoDeAmbiente> solicitacoes = List.of(
                new SolicitacaoDeAmbiente("loja-virtual-producao", "Aws", 5, 500),
                new SolicitacaoDeAmbiente("analise-de-vendas-homologacao", "Aws", 3, 2000),
                new SolicitacaoDeAmbiente("portal-de-clientes", "Azure", 4, 1000),
                new SolicitacaoDeAmbiente("datalake-corporativo", "Aws", 80, 10000));

        ConfiguracaoDoSistema configuracao = ConfiguracaoDoSistema.obterInstancia();
        System.out.println("Orçamento mensal máximo por ambiente: "
                + FormatadorMonetario.formatar(configuracao.getOrcamentoMensalMaximo()));
        System.out.println();

        CountDownLatch largada = new CountDownLatch(1);
        List<Thread> threads = solicitacoes.stream()
                .map(solicitacao -> criarThreadDeProvisionamento(solicitacao, largada))
                .toList();

        threads.forEach(Thread::start);
        largada.countDown();
        for (Thread thread : threads) {
            thread.join();
        }

        List<RegistroDeAuditoria> registros = HistoricoDeAuditoria.obterInstancia().getRegistros();
        System.out.println("=== Histórico de auditoria (" + registros.size() + " registros) ===");
        registros.forEach(System.out::println);
    }

    private static Thread criarThreadDeProvisionamento(SolicitacaoDeAmbiente solicitacao, CountDownLatch largada) {
        return new Thread(() -> {
            try {
                largada.await();
            } catch (InterruptedException interrupcao) {
                Thread.currentThread().interrupt();
                return;
            }
            FabricaDeRecursos fabrica = escolherFabrica(solicitacao.getProvedor());
            new ProvisionadorDeAmbiente(new MontadorDeAmbiente(fabrica)).provisionar(solicitacao);
        }, "provisionamento-" + solicitacao.getNomeDoAmbiente());
    }
}