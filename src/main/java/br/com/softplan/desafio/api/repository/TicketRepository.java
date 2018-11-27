package br.com.softplan.desafio.api.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.softplan.desafio.api.model.Ticket;
import br.com.softplan.desafio.api.repository.ticket.TicketRepositoryQuery;

public interface TicketRepository extends JpaRepository<Ticket, Long>, TicketRepositoryQuery {
    
    List<Ticket> findByDataCriacaoLessThanEqualAndDataVencimentoIsNull(LocalDate data);
    
}
