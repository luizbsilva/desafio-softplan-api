package br.com.softplan.desafio.api.repository.ticket;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import br.com.softplan.desafio.api.dto.TicketEstatisticaCategoria;
import br.com.softplan.desafio.api.dto.TicketEstatisticaDia;
import br.com.softplan.desafio.api.dto.TicketEstatisticaPessoa;
import br.com.softplan.desafio.api.model.Categoria_;
import br.com.softplan.desafio.api.model.Pessoa_;
import br.com.softplan.desafio.api.model.Ticket;
import br.com.softplan.desafio.api.model.Ticket_;
import br.com.softplan.desafio.api.repository.filter.TicketFilter;
import br.com.softplan.desafio.api.repository.projection.ResumoTicket;

public class TicketRepositoryImpl implements TicketRepositoryQuery {
    
    @PersistenceContext
    private EntityManager manager;
    
    @Override
    public List<TicketEstatisticaPessoa> porPessoa(final LocalDate inicio, final LocalDate fim) {
        final CriteriaBuilder criteriaBuilder = this.manager.getCriteriaBuilder();
        
        final CriteriaQuery<TicketEstatisticaPessoa> criteriaQuery = criteriaBuilder.createQuery(TicketEstatisticaPessoa.class);
        
        final Root<Ticket> root = criteriaQuery.from(Ticket.class);
        
        criteriaQuery.select(criteriaBuilder.construct(TicketEstatisticaPessoa.class,
                root.get(Ticket_.tipo),
                root.get(Ticket_.pessoa),
                criteriaBuilder.sum(root.get(Ticket_.valor))));
        
        criteriaQuery.where(
                criteriaBuilder.greaterThanOrEqualTo(root.get(Ticket_.dataVencimento),
                        inicio),
                criteriaBuilder.lessThanOrEqualTo(root.get(Ticket_.dataVencimento),
                        fim));
        
        criteriaQuery.groupBy(root.get(Ticket_.tipo),
                root.get(Ticket_.pessoa));
        
        final TypedQuery<TicketEstatisticaPessoa> typedQuery = this.manager
                .createQuery(criteriaQuery);
        
        return typedQuery.getResultList();
    }
    
    @Override
    public List<TicketEstatisticaDia> porDia(final LocalDate mesReferencia) {
        final CriteriaBuilder criteriaBuilder = this.manager.getCriteriaBuilder();
        
        final CriteriaQuery<TicketEstatisticaDia> criteriaQuery = criteriaBuilder.createQuery(TicketEstatisticaDia.class);
        
        final Root<Ticket> root = criteriaQuery.from(Ticket.class);
        
        criteriaQuery.select(criteriaBuilder.construct(TicketEstatisticaDia.class,
                root.get(Ticket_.tipo),
                root.get(Ticket_.dataVencimento),
                criteriaBuilder.sum(root.get(Ticket_.valor))));
        
        final LocalDate primeiroDia = mesReferencia.withDayOfMonth(1);
        final LocalDate ultimoDia = mesReferencia.withDayOfMonth(mesReferencia.lengthOfMonth());
        
        criteriaQuery.where(
                criteriaBuilder.greaterThanOrEqualTo(root.get(Ticket_.dataVencimento),
                        primeiroDia),
                criteriaBuilder.lessThanOrEqualTo(root.get(Ticket_.dataVencimento),
                        ultimoDia));
        
        criteriaQuery.groupBy(root.get(Ticket_.tipo),
                root.get(Ticket_.dataVencimento));
        
        final TypedQuery<TicketEstatisticaDia> typedQuery = this.manager
                .createQuery(criteriaQuery);
        
        return typedQuery.getResultList();
    }
    
    @Override
    public List<TicketEstatisticaCategoria> porCategoria(final LocalDate mesReferencia) {
        final CriteriaBuilder criteriaBuilder = this.manager.getCriteriaBuilder();
        
        final CriteriaQuery<TicketEstatisticaCategoria> criteriaQuery = criteriaBuilder.createQuery(TicketEstatisticaCategoria.class);
        
        final Root<Ticket> root = criteriaQuery.from(Ticket.class);
        
        criteriaQuery.select(criteriaBuilder.construct(TicketEstatisticaCategoria.class,
                root.get(Ticket_.categoria),
                criteriaBuilder.sum(root.get(Ticket_.valor))));
        
        final LocalDate primeiroDia = mesReferencia.withDayOfMonth(1);
        final LocalDate ultimoDia = mesReferencia.withDayOfMonth(mesReferencia.lengthOfMonth());
        
        criteriaQuery.where(
                criteriaBuilder.greaterThanOrEqualTo(root.get(Ticket_.dataVencimento),
                        primeiroDia),
                criteriaBuilder.lessThanOrEqualTo(root.get(Ticket_.dataVencimento),
                        ultimoDia));
        
        criteriaQuery.groupBy(root.get(Ticket_.categoria));
        
        final TypedQuery<TicketEstatisticaCategoria> typedQuery = this.manager
                .createQuery(criteriaQuery);
        
        return typedQuery.getResultList();
    }
    
    @Override
    public Page<Ticket> filtrar(final TicketFilter ticketFilter, final Pageable pageable) {
        final CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        final CriteriaQuery<Ticket> criteria = builder.createQuery(Ticket.class);
        final Root<Ticket> root = criteria.from(Ticket.class);
        
        final Predicate[] predicates = this.criarRestricoes(ticketFilter, builder, root);
        criteria.where(predicates);
        
        final TypedQuery<Ticket> query = this.manager.createQuery(criteria);
        this.adicionarRestricoesDePaginacao(query, pageable);
        
        return new PageImpl<>(query.getResultList(), pageable, this.total(ticketFilter));
    }
    
    @Override
    public Page<ResumoTicket> resumir(final TicketFilter ticketFilter, final Pageable pageable) {
        final CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        final CriteriaQuery<ResumoTicket> criteria = builder.createQuery(ResumoTicket.class);
        final Root<Ticket> root = criteria.from(Ticket.class);
        
        criteria.select(builder.construct(ResumoTicket.class, root.get(Ticket_.codigo), root.get(Ticket_.descricao), root.get(Ticket_.dataVencimento), root.get(Ticket_.dataCriacao),
                root.get(Ticket_.valor), root.get(Ticket_.tipo), root.get(Ticket_.categoria).get(Categoria_.nome), root.get(Ticket_.pessoa).get(Pessoa_.nome)));
        
        final Predicate[] predicates = this.criarRestricoes(ticketFilter, builder, root);
        criteria.where(predicates);
        
        final TypedQuery<ResumoTicket> query = this.manager.createQuery(criteria);
        this.adicionarRestricoesDePaginacao(query, pageable);
        
        return new PageImpl<>(query.getResultList(), pageable, this.total(ticketFilter));
    }
    
    private Predicate[] criarRestricoes(final TicketFilter ticketFilter, final CriteriaBuilder builder,
            final Root<Ticket> root) {
        final List<Predicate> predicates = new ArrayList<>();
        
        if (!StringUtils.isEmpty(ticketFilter.getDescricao())) {
            predicates.add(builder.like(
                    builder.lower(root.get(Ticket_.descricao)), "%" + ticketFilter.getDescricao().toLowerCase() + "%"));
        }
        
        if (ticketFilter.getDataVencimentoDe() != null) {
            predicates.add(
                    builder.greaterThanOrEqualTo(root.get(Ticket_.dataVencimento), ticketFilter.getDataVencimentoDe()));
        }
        
        if (ticketFilter.getDataVencimentoAte() != null) {
            predicates.add(
                    builder.lessThanOrEqualTo(root.get(Ticket_.dataVencimento), ticketFilter.getDataVencimentoAte()));
        }
        
        return predicates.toArray(new Predicate[predicates.size()]);
    }
    
    private void adicionarRestricoesDePaginacao(final TypedQuery<?> query, final Pageable pageable) {
        final int paginaAtual = pageable.getPageNumber();
        final int totalRegistrosPorPagina = pageable.getPageSize();
        final int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
        
        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistrosPorPagina);
    }
    
    private Long total(final TicketFilter ticketFilter) {
        final CriteriaBuilder builder = this.manager.getCriteriaBuilder();
        final CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        final Root<Ticket> root = criteria.from(Ticket.class);
        
        final Predicate[] predicates = this.criarRestricoes(ticketFilter, builder, root);
        criteria.where(predicates);
        
        criteria.select(builder.count(root));
        return this.manager.createQuery(criteria).getSingleResult();
    }
    
}
