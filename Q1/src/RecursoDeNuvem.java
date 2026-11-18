package Q1.src;
import java.math.BigDecimal;

/** Contrato comum a todos os recursos de um ambiente, de qualquer provedor. */
public interface RecursoDeNuvem {

    String getIdentificador();

    String getProvedor();

    BigDecimal getCustoMensal();
}