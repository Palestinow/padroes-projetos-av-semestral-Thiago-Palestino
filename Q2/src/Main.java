import java.math.BigDecimal;

/**
 * Cenário completo do RF11: dois lotes de categorias distintas, três participantes, um painel e um auditor;
 * lances aceitos e recusados, saída de um participante do acompanhamento, troca de regra de comissão,
 * encerramento de um lote vendido e de um não vendido, e fatura com serviços adicionais (um deles repetido).
 */
public class Main {

    public static void main(String[] args) {
        Participante ana = new Participante("Ana");
        Participante bruno = new Participante("Bruno");
        Participante carla = new Participante("Carla");
        PainelDoLeiloeiro painel = new PainelDoLeiloeiro();
        AuditorDeLances auditor = new AuditorDeLances();

        Lote quadro = new Lote("Quadro Entardecer em Paraty (óleo sobre tela)", new CategoriaArte(),
                dinheiro("10000.00"), dinheiro("14000.00"));
        Lote notebook = new Lote("Notebook ultrafino X15", new CategoriaEletronicos(),
                dinheiro("3000.00"), dinheiro("5000.00"));

        titulo("1. ABERTURA E ACOMPANHAMENTO DOS LOTES");
        for (Lote lote : new Lote[] {quadro, notebook}) {
            lote.acompanhar(painel);
            lote.acompanhar(auditor);
        }
        ana.acompanhar(quadro);
        bruno.acompanhar(quadro);
        carla.acompanhar(quadro);
        ana.acompanhar(notebook);
        carla.acompanhar(notebook);
        System.out.println("Painel e auditor acompanham os dois lotes.");
        System.out.println("Quadro: Ana, Bruno e Carla acompanham. Notebook: Ana e Carla acompanham.");
        descreverLote(quadro);
        descreverLote(notebook);

        titulo("2. LANCES NO QUADRO (quem dá o lance não é notificado)");
        darLance(bruno, quadro, "10500.00");
        darLance(ana, quadro, "10800.00");
        darLance(ana, quadro, "11025.00");

        titulo("3. CARLA DEIXA DE ACOMPANHAR O QUADRO (não recebe os próximos lances)");
        carla.deixarDeAcompanhar(quadro);
        System.out.println("Carla deixou de acompanhar o lote '" + quadro.getNome() + "'.");
        darLance(bruno, quadro, "12000.00");
        darLance(ana, quadro, "13000.00");
        darLance(bruno, quadro, "14000.00");

        titulo("4. LANCES NO NOTEBOOK");
        darLance(carla, notebook, "3150.00");
        darLance(ana, notebook, "3500.00");
        darLance(carla, notebook, "3600.00");

        titulo("5. CAMPANHA: TROCA DA REGRA DE COMISSÃO DO QUADRO (sem recriar o lote)");
        System.out.println("Regra vigente: " + quadro.getRegraDeComissao().getDescricao());
        quadro.trocarRegraDeComissao(new ComissaoDeEletronicos());
        System.out.println("Regra após a campanha: " + quadro.getRegraDeComissao().getDescricao());
        System.out.println();

        titulo("6. ENCERRAMENTO DOS LOTES");
        encerrar(quadro);
        encerrar(notebook);
        darLance(ana, quadro, "15000.00");

        titulo("7. ACERTO COM O VENDEDOR DO QUADRO");
        new ImpressoraDeAcerto().imprimir(new AcertoDoVendedor(quadro));

        titulo("8. FATURA DO COMPRADOR");
        ImpressoraDeFatura impressoraDeFatura = new ImpressoraDeFatura();

        Fatura faturaSemServicos = new FaturaDoLote(quadro);
        System.out.println("(a) Mesma fatura, sem nenhum serviço adicional:");
        impressoraDeFatura.imprimir(faturaSemServicos);

        Fatura faturaComServicos = new FaturaDoLote(quadro);
        faturaComServicos = new EmbalagemEspecial(faturaComServicos);
        faturaComServicos = new SeguroDeTransporte(faturaComServicos);
        faturaComServicos = new EmbalagemEspecial(faturaComServicos);
        faturaComServicos = new CertificadoDeAutenticidade(faturaComServicos);
        faturaComServicos = new EntregaExpressa(faturaComServicos);
        System.out.println("(b) Com serviços (embalagem especial contratada duas vezes):");
        impressoraDeFatura.imprimir(faturaComServicos);

        titulo("9. REGISTRO DO AUDITOR (todos os lances aceitos)");
        auditor.getRegistros().forEach(System.out::println);
    }

    private static BigDecimal dinheiro(String valor) {
        return new BigDecimal(valor);
    }

    private static void titulo(String texto) {
        System.out.println("=== " + texto + " ===");
    }

    private static void descreverLote(Lote lote) {
        System.out.println("Lote '" + lote.getNome() + "' [" + lote.getCategoria().getNome() + "] | preço inicial: "
                + FormatadorMonetario.formatar(lote.getPrecoInicial()) + " | reserva: "
                + FormatadorMonetario.formatar(lote.getPrecoDeReserva()) + " | comissão: "
                + lote.getRegraDeComissao().getDescricao());
    }

    private static void darLance(Participante participante, Lote lote, String valor) {
        System.out.println(participante.getNome() + " dá um lance de " + FormatadorMonetario.formatar(dinheiro(valor))
                + " no lote '" + lote.getNome() + "':");
        ResultadoDoLance resultado = participante.darLance(lote, dinheiro(valor));
        System.out.println("   -> " + (resultado.foiAceito() ? "ACEITO: " : "RECUSADO: ") + resultado.getMensagem());
        System.out.println();
    }

    private static void encerrar(Lote lote) {
        SituacaoDoLote situacao = lote.encerrar();
        System.out.println("Lote '" + lote.getNome() + "' encerrado: " + situacao.getDescricao().toUpperCase()
                + " | maior lance: " + FormatadorMonetario.formatar(lote.getLanceAtual())
                + " | reserva: " + FormatadorMonetario.formatar(lote.getPrecoDeReserva()));
        if (situacao == SituacaoDoLote.VENDIDO) {
            System.out.println("Comprador: " + lote.getComprador().getNome());
        }
        System.out.println();
    }
}