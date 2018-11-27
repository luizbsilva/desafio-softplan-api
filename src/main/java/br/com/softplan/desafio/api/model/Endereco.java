package br.com.softplan.desafio.api.model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class Endereco {
    
    private String logradouro;
    
    private String numero;
    
    private String complemento;
    
    private String bairro;
    
    private String cep;
    
    @ManyToOne
    @JoinColumn(name = "codigo_cidade")
    private Cidade cidade;
    
    public String getLogradouro() {
        return this.logradouro;
    }
    
    public void setLogradouro(final String logradouro) {
        this.logradouro = logradouro;
    }
    
    public String getNumero() {
        return this.numero;
    }
    
    public void setNumero(final String numero) {
        this.numero = numero;
    }
    
    public String getComplemento() {
        return this.complemento;
    }
    
    public void setComplemento(final String complemento) {
        this.complemento = complemento;
    }
    
    public String getBairro() {
        return this.bairro;
    }
    
    public void setBairro(final String bairro) {
        this.bairro = bairro;
    }
    
    public String getCep() {
        return this.cep;
    }
    
    public void setCep(final String cep) {
        this.cep = cep;
    }
    
    public Cidade getCidade() {
        return this.cidade;
    }
    
    public void setCidade(final Cidade cidade) {
        this.cidade = cidade;
    }
}
