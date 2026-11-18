import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;

/** Formata valores em reais (arredondamento HALF_UP apenas na exibição). */
public final class FormatadorMonetario {

    private static final Locale BRASIL = Locale.forLanguageTag("pt-BR");

    private FormatadorMonetario() {
    }

    public static String formatar(BigDecimal valor) {
        return String.format(BRASIL, "R$ %,.2f", valor.setScale(2, RoundingMode.HALF_UP));
    }
}