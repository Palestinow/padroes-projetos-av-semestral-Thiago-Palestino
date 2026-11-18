package Q1.src;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/**
 * Singleton da configuração. O arquivo é lido uma única vez, no construtor privado,
 * e a inicialização preguiçosa é thread-safe pelo idiom da classe interna estática.
 */
public final class ConfiguracaoDoSistema {

    private static final Path ARQUIVO_DE_CONFIGURACAO = Path.of("config.properties");
    private static final String CHAVE_REGIAO = "regiao.padrao";
    private static final String CHAVE_ORCAMENTO = "orcamento.mensal.maximo";

    private final String regiaoPadrao;
    private final BigDecimal orcamentoMensalMaximo;

    private ConfiguracaoDoSistema() {
        Properties propriedades = new Properties();
        try (Reader leitor = Files.newBufferedReader(ARQUIVO_DE_CONFIGURACAO, StandardCharsets.UTF_8)) {
            propriedades.load(leitor);
        } catch (IOException erro) {
            throw new IllegalStateException(
                    "Não foi possível ler " + ARQUIVO_DE_CONFIGURACAO.toAbsolutePath(), erro);
        }
        this.regiaoPadrao = obterPropriedade(propriedades, CHAVE_REGIAO);
        this.orcamentoMensalMaximo = new BigDecimal(obterPropriedade(propriedades, CHAVE_ORCAMENTO));
        System.out.println("[configuração] " + ARQUIVO_DE_CONFIGURACAO + " lido: região padrão = "
                + regiaoPadrao + ", orçamento mensal máximo = "
                + FormatadorMonetario.formatar(orcamentoMensalMaximo));
    }

    private static String obterPropriedade(Properties propriedades, String chave) {
        String valor = propriedades.getProperty(chave);
        if (valor == null || valor.isBlank()) {
            throw new IllegalStateException("Propriedade obrigatória ausente em config.properties: " + chave);
        }
        return valor.trim();
    }

    private static final class Instancia {
        private static final ConfiguracaoDoSistema UNICA = new ConfiguracaoDoSistema();
    }

    public static ConfiguracaoDoSistema obterInstancia() {
        return Instancia.UNICA;
    }

    public String getRegiaoPadrao() {
        return regiaoPadrao;
    }

    public BigDecimal getOrcamentoMensalMaximo() {
        return orcamentoMensalMaximo;
    }
}