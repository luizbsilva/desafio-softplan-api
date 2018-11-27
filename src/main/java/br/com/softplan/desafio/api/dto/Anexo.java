package br.com.softplan.desafio.api.dto;

public class Anexo {
    
    private final String nome;
    
    private final String url;
    
    public Anexo(final String nome, final String url) {
        this.nome = nome;
        this.url = url;
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public String getUrl() {
        return this.url;
    }
}
