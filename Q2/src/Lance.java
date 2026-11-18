import java.math.BigDecimal;

/** Lance aceito em um lote: quem o deu e o valor. */
public class Lance {

    private final Licitante licitante;
    private final BigDecimal valor;

    public Lance(Licitante licitante, BigDecimal valor) {
        this.licitante = licitante;
        this.valor = valor;
    }

    public Licitante getLicitante() {
        return licitante;
    }

    public BigDecimal getValor() {
        return valor;
    }
}