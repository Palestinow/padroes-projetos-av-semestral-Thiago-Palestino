import java.math.BigDecimal;
import java.util.List;

/**
 * Component do Decorator: a fatura do comprador. Quem a imprime a trata sempre da mesma forma,
 * tenha ela nenhum ou vários serviços adicionais (RNF04).
 */
public interface Fatura {

    String getDescricaoDoLote();

    String getNomeDoComprador();

    BigDecimal getValorDeVenda();

    List<ServicoContratado> getServicosContratados();

    BigDecimal getTotalAPagar();
}