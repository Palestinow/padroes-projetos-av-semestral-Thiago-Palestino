package Q1.src;
import java.math.BigDecimal;

/**
 * Preços de um provedor. É a única classe que conhece a fórmula do custo mensal (RF04),
 * usada tanto na estimativa prévia do orçamento quanto no custo de cada recurso criado.
 */
public class TabelaDePrecos {

    private static final BigDecimal HORAS_NO_MES = BigDecimal.valueOf(730);

    private final BigDecimal custoHoraDaMaquina;
    private final BigDecimal custoGigabyteNoMes;
    private final BigDecimal custoFixoDaFila;

    public TabelaDePrecos(BigDecimal custoHoraDaMaquina, BigDecimal custoGigabyteNoMes, BigDecimal custoFixoDaFila) {
        this.custoHoraDaMaquina = custoHoraDaMaquina;
        this.custoGigabyteNoMes = custoGigabyteNoMes;
        this.custoFixoDaFila = custoFixoDaFila;
    }

    public BigDecimal custoDasMaquinas(int quantidade) {
        return custoHoraDaMaquina.multiply(HORAS_NO_MES).multiply(BigDecimal.valueOf(quantidade));
    }

    public BigDecimal custoDoArmazenamento(int gigabytes) {
        return custoGigabyteNoMes.multiply(BigDecimal.valueOf(gigabytes));
    }

    public BigDecimal custoDaFila() {
        return custoFixoDaFila;
    }

    public BigDecimal custoMensal(int quantidadeDeMaquinas, int gigabytes) {
        return custoDasMaquinas(quantidadeDeMaquinas)
                .add(custoDoArmazenamento(gigabytes))
                .add(custoDaFila());
    }
}