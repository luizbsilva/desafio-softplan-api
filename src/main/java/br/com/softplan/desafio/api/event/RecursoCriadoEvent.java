package br.com.softplan.desafio.api.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class RecursoCriadoEvent extends ApplicationEvent {
    
    private static final long serialVersionUID = 1L;
    
    private final HttpServletResponse response;
    
    private final Long codigo;
    
    public RecursoCriadoEvent(final Object source, final HttpServletResponse response, final Long codigo) {
        super(source);
        this.response = response;
        this.codigo = codigo;
    }
    
    public HttpServletResponse getResponse() {
        return this.response;
    }
    
    public Long getCodigo() {
        return this.codigo;
    }
    
}
