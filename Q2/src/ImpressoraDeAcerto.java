/** Imprime o acerto do vendedor (RF10). */
public class ImpressoraDeAcerto {

    public void imprimir(AcertoDoVendedor acerto) {
        System.out.println("===== ACERTO DO VENDEDOR =====");
        System.out.println("Lote: " + acerto.getNomeDoLote());
        System.out.println("Valor de venda: " + FormatadorMonetario.formatar(acerto.getValorDeVenda()));
        System.out.println("Regra de comissão aplicada: " + acerto.getDescricaoDaRegra());
        System.out.println("Comissão cobrada: " + FormatadorMonetario.formatar(acerto.getComissaoCobrada()));
        System.out.println("Valor líquido a repassar: " + FormatadorMonetario.formatar(acerto.getValorLiquido()));
        System.out.println();
    }
}