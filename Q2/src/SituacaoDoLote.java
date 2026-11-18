public enum SituacaoDoLote {
    ABERTO("aberto"),
    VENDIDO("vendido"),
    NAO_VENDIDO("não vendido");

    private final String descricao;

    SituacaoDoLote(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}