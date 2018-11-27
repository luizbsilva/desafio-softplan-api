package br.com.softplan.desafio.api.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.softplan.desafio.api.event.RecursoCriadoEvent;

@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent> {
    
    @Override
    public void onApplicationEvent(final RecursoCriadoEvent recursoCriadoEvent) {
        final HttpServletResponse response = recursoCriadoEvent.getResponse();
        final Long codigo = recursoCriadoEvent.getCodigo();
        
        this.adicionarHeaderLocation(response, codigo);
    }
    
    private void adicionarHeaderLocation(final HttpServletResponse response, final Long codigo) {
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
                .buildAndExpand(codigo).toUri();
        response.setHeader("Location", uri.toASCIIString());
    }
    
}
