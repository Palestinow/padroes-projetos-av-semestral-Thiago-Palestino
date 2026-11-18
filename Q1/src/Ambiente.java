package Q1.src;
import java.math.BigDecimal;

/** Ambiente provisionado. Só existe se os três recursos forem do mesmo provedor (RNF01). */
public class Ambiente {

    private final String nome;
    private final String regiao;
    private final MaquinaVirtual maquinasVirtuais;
    private final ArmazenamentoDeObjetos armazenamento;
    private final FilaDeMensagens fila;

    public Ambiente(String nome, String regiao, MaquinaVirtual maquinasVirtuais,
                    ArmazenamentoDeObjetos armazenamento, FilaDeMensagens fila) {
        String provedor = maquinasVirtuais.getProvedor();
        if (!provedor.equals(armazenamento.getProvedor()) || !provedor.equals(fila.getProvedor())) {
            throw new IllegalArgumentException("Os recursos de um ambiente devem pertencer ao mesmo provedor");
        }
        this.nome = nome;
        this.regiao = regiao;
        this.maquinasVirtuais = maquinasVirtuais;
        this.armazenamento = armazenamento;
        this.fila = fila;
    }

    public String getNome() {
        return nome;
    }

    public String getProvedor() {
        return maquinasVirtuais.getProvedor();
    }

    public String getRegiao() {
        return regiao;
    }

    public MaquinaVirtual getMaquinasVirtuais() {
        return maquinasVirtuais;
    }

    public ArmazenamentoDeObjetos getArmazenamento() {
        return armazenamento;
    }

    public FilaDeMensagens getFila() {
        return fila;
    }

    public BigDecimal getCustoMensal() {
        return maquinasVirtuais.getCustoMensal()
                .add(armazenamento.getCustoMensal())
                .add(fila.getCustoMensal());
    }
}