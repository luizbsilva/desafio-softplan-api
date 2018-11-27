package br.com.softplan.desafio.api.repository.listener;

import javax.persistence.PostLoad;

import org.springframework.util.StringUtils;

import br.com.softplan.desafio.api.DesafioApiApplication;
import br.com.softplan.desafio.api.model.Ticket;
import br.com.softplan.desafio.api.storage.StorageS3Config;

public class TicketAnexoListener {
    
    @PostLoad
    public void postLoad(final Ticket ticket) {
        if (StringUtils.hasText(ticket.getAnexo())) {
            final StorageS3Config s3 = DesafioApiApplication.getBean(StorageS3Config.class);
            ticket.setUrlAnexo(s3.configurarUrl(ticket.getAnexo()));
        }
    }
    
}
