package br.com.softplan.desafio.api.model;

public enum TipoTicket {
    
    ORCAMENTO("Orçamento"),
    SERVICO("Serviço");
    
    private final String descricao;
    
    TipoTicket(final String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return this.descricao;
    }
}
