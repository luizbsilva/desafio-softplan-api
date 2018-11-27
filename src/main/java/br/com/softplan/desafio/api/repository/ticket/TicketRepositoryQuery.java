package br.com.softplan.desafio.api.repository.ticket;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.softplan.desafio.api.dto.TicketEstatisticaCategoria;
import br.com.softplan.desafio.api.dto.TicketEstatisticaDia;
import br.com.softplan.desafio.api.dto.TicketEstatisticaPessoa;
import br.com.softplan.desafio.api.model.Ticket;
import br.com.softplan.desafio.api.repository.filter.TicketFilter;
import br.com.softplan.desafio.api.repository.projection.ResumoTicket;

public interface TicketRepositoryQuery {
    
    public List<TicketEstatisticaPessoa> porPessoa(LocalDate inicio, LocalDate fim);
    
    public List<TicketEstatisticaCategoria> porCategoria(LocalDate mesReferencia);
    
    public List<TicketEstatisticaDia> porDia(LocalDate mesReferencia);
    
    public Page<Ticket> filtrar(TicketFilter ticketFilter, Pageable pageable);
    
    public Page<ResumoTicket> resumir(TicketFilter ticketFilter, Pageable pageable);
    
}
