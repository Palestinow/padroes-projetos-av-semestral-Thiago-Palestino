import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Lote do leilão. Atua como Subject do Observer (comunica lances aceitos a quem o acompanha, conhecendo
 * apenas a interface AcompanhanteDoLote) e como Context do Strategy (delega o cálculo da comissão
 * à RegraDeComissao vigente, que pode ser trocada em tempo de execução).
 */
public class Lote {

    private static final BigDecimal FATOR_DO_LANCE_MINIMO = new BigDecimal("1.05");

    private final String nome;
    private final CategoriaDeLote categoria;
    private final BigDecimal precoInicial;
    private final BigDecimal precoDeReserva;

    private final List<AcompanhanteDoLote> acompanhantes = new ArrayList<>();
    private final List<Lance> lances = new ArrayList<>();
    private RegraDeComissao regraDeComissao;
    private SituacaoDoLote situacao = SituacaoDoLote.ABERTO;

    public Lote(String nome, CategoriaDeLote categoria, BigDecimal precoInicial, BigDecimal precoDeReserva) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do lote é obrigatório");
        }
        if (precoInicial.signum() <= 0 || precoDeReserva.signum() <= 0) {
            throw new IllegalArgumentException("Os preços inicial e de reserva devem ser positivos");
        }
        this.nome = nome;
        this.categoria = categoria;
        this.precoInicial = precoInicial;
        this.precoDeReserva = precoDeReserva;
        this.regraDeComissao = categoria.regraDeComissaoPadrao();
    }

    // ---------------- Observer (Subject) ----------------

    public void acompanhar(AcompanhanteDoLote acompanhante) {
        if (!acompanhantes.contains(acompanhante)) {
            acompanhantes.add(acompanhante);
        }
    }

    public void deixarDeAcompanhar(AcompanhanteDoLote acompanhante) {
        acompanhantes.remove(acompanhante);
    }

    private void notificarAcompanhantes(Lance lance) {
        for (AcompanhanteDoLote acompanhante : new ArrayList<>(acompanhantes)) {
            if (acompanhante != lance.getLicitante()) {
                acompanhante.aoAceitarLance(this, lance);
            }
        }
    }

    // ---------------- Lances ----------------

    public ResultadoDoLance registrarLance(Licitante licitante, BigDecimal valor) {
        if (licitante == null || valor == null || valor.signum() <= 0) {
            throw new IllegalArgumentException("O lance exige um licitante e um valor positivo");
        }
        if (situacao != SituacaoDoLote.ABERTO) {
            return ResultadoDoLance.recusado("Lance de " + FormatadorMonetario.formatar(valor)
                    + " recusado: o lote '" + nome + "' está encerrado e não aceita novos lances.");
        }
        BigDecimal lanceMinimo = getLanceMinimo();
        if (valor.compareTo(lanceMinimo) < 0) {
            return ResultadoDoLance.recusado("Lance de " + FormatadorMonetario.formatar(valor)
                    + " recusado: o lance mínimo para o lote '" + nome + "' é "
                    + FormatadorMonetario.formatar(lanceMinimo) + " (5% acima do lance atual de "
                    + FormatadorMonetario.formatar(getLanceAtual()) + ").");
        }
        Lance lance = new Lance(licitante, valor);
        lances.add(lance);
        notificarAcompanhantes(lance);
        return ResultadoDoLance.aceito("Lance de " + FormatadorMonetario.formatar(valor)
                + " aceito no lote '" + nome + "'.");
    }

    public BigDecimal getLanceAtual() {
        return lances.isEmpty() ? precoInicial : lances.get(lances.size() - 1).getValor();
    }

    /** Lance mínimo aceito: 5% acima do lance atual, arredondado para cima ao centavo. */
    public BigDecimal getLanceMinimo() {
        return getLanceAtual().multiply(FATOR_DO_LANCE_MINIMO).setScale(2, RoundingMode.UP);
    }

    public int getQuantidadeDeLances() {
        return lances.size();
    }

    public List<Lance> getLances() {
        return Collections.unmodifiableList(lances);
    }

    // ---------------- Encerramento ----------------

    /** Vende ao maior lance se atingir o preço de reserva; sem lances ou abaixo da reserva, não vende. */
    public SituacaoDoLote encerrar() {
        if (situacao != SituacaoDoLote.ABERTO) {
            throw new IllegalStateException("O lote '" + nome + "' já foi encerrado");
        }
        boolean atingiuReserva = !lances.isEmpty() && getLanceAtual().compareTo(precoDeReserva) >= 0;
        situacao = atingiuReserva ? SituacaoDoLote.VENDIDO : SituacaoDoLote.NAO_VENDIDO;
        return situacao;
    }

    public BigDecimal getValorDeVenda() {
        exigirLoteVendido();
        return getLanceAtual();
    }

    public Licitante getComprador() {
        exigirLoteVendido();
        return lances.get(lances.size() - 1).getLicitante();
    }

    private void exigirLoteVendido() {
        if (situacao != SituacaoDoLote.VENDIDO) {
            throw new IllegalStateException("O lote '" + nome + "' não foi vendido");
        }
    }

    // ---------------- Strategy (Context) ----------------

    /** Troca a regra de comissão do lote, sem recriá-lo; só é possível antes do encerramento. */
    public void trocarRegraDeComissao(RegraDeComissao novaRegra) {
        if (situacao != SituacaoDoLote.ABERTO) {
            throw new IllegalStateException("A regra de comissão só pode ser trocada antes do encerramento do lote");
        }
        if (novaRegra == null) {
            throw new IllegalArgumentException("A nova regra de comissão é obrigatória");
        }
        this.regraDeComissao = novaRegra;
    }

    public BigDecimal calcularComissao() {
        return regraDeComissao.calcular(getValorDeVenda());
    }

    public RegraDeComissao getRegraDeComissao() {
        return regraDeComissao;
    }

    // ---------------- Dados do lote ----------------

    public String getNome() {
        return nome;
    }

    public CategoriaDeLote getCategoria() {
        return categoria;
    }

    public BigDecimal getPrecoInicial() {
        return precoInicial;
    }

    public BigDecimal getPrecoDeReserva() {
        return precoDeReserva;
    }

    public SituacaoDoLote getSituacao() {
        return situacao;
    }
}