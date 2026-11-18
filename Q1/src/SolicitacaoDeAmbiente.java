/** Pedido de provisionamento de um ambiente (RF03). */
package Q1.src;
public class SolicitacaoDeAmbiente {

    private final String nomeDoAmbiente;
    private final String provedor;
    private final int quantidadeDeMaquinas;
    private final int gigabytesDeArmazenamento;

    public SolicitacaoDeAmbiente(String nomeDoAmbiente, String provedor,
                                 int quantidadeDeMaquinas, int gigabytesDeArmazenamento) {
        if (nomeDoAmbiente == null || nomeDoAmbiente.isBlank()) {
            throw new IllegalArgumentException("O nome do ambiente é obrigatório");
        }
        if (provedor == null || provedor.isBlank()) {
            throw new IllegalArgumentException("O provedor é obrigatório");
        }
        if (quantidadeDeMaquinas < 1) {
            throw new IllegalArgumentException("A quantidade de máquinas virtuais deve ser ao menos 1");
        }
        if (gigabytesDeArmazenamento < 1) {
            throw new IllegalArgumentException("O armazenamento deve ser de ao menos 1 GB");
        }
        this.nomeDoAmbiente = nomeDoAmbiente;
        this.provedor = provedor;
        this.quantidadeDeMaquinas = quantidadeDeMaquinas;
        this.gigabytesDeArmazenamento = gigabytesDeArmazenamento;
    }

    public String getNomeDoAmbiente() {
        return nomeDoAmbiente;
    }

    public String getProvedor() {
        return provedor;
    }

    public int getQuantidadeDeMaquinas() {
        return quantidadeDeMaquinas;
    }

    public int getGigabytesDeArmazenamento() {
        return gigabytesDeArmazenamento;
    }
}