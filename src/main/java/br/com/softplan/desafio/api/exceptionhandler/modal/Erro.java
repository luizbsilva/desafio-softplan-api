package br.com.softplan.desafio.api.exceptionhandler.modal;

public class Erro {
    
    private final String mensagemUsuario;
    
    private final String mensagemDesenvolvedor;
    
    public Erro(final String mensagemUsuario, final String mensagemDesenvolvedor) {
        this.mensagemUsuario = mensagemUsuario;
        this.mensagemDesenvolvedor = mensagemDesenvolvedor;
    }
    
    public String getMensagemUsuario() {
        return this.mensagemUsuario;
    }
    
    public String getMensagemDesenvolvedor() {
        return this.mensagemDesenvolvedor;
    }
    
}
