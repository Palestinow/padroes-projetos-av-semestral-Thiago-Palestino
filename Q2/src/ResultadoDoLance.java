/** Resultado da tentativa de dar um lance: aceito ou recusado, sempre com uma mensagem explicativa. */
public class ResultadoDoLance {

    private final boolean aceito;
    private final String mensagem;

    private ResultadoDoLance(boolean aceito, String mensagem) {
        this.aceito = aceito;
        this.mensagem = mensagem;
    }

    public static ResultadoDoLance aceito(String mensagem) {
        return new ResultadoDoLance(true, mensagem);
    }

    public static ResultadoDoLance recusado(String mensagem) {
        return new ResultadoDoLance(false, mensagem);
    }

    public boolean foiAceito() {
        return aceito;
    }

    public String getMensagem() {
        return mensagem;
    }
}