/** Imprime a fatura do comprador (RF09), sem saber quais serviços ela contém nem quantos são. */
public class ImpressoraDeFatura {

    public void imprimir(Fatura fatura) {
        System.out.println("===== FATURA DO COMPRADOR =====");
        System.out.println("Lote: " + fatura.getDescricaoDoLote());
        System.out.println("Comprador: " + fatura.getNomeDoComprador());
        System.out.println("Valor de venda: " + FormatadorMonetario.formatar(fatura.getValorDeVenda()));
        System.out.println("Serviços adicionais contratados: " + fatura.getServicosContratados().size());
        for (ServicoContratado servico : fatura.getServicosContratados()) {
            System.out.println("  - " + servico.getDescricao() + ": " + FormatadorMonetario.formatar(servico.getCusto()));
        }
        System.out.println("Total a pagar: " + FormatadorMonetario.formatar(fatura.getTotalAPagar()));
        System.out.println();
    }
}