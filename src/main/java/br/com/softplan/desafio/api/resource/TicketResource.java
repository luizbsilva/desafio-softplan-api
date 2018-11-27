package br.com.softplan.desafio.api.resource;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.softplan.desafio.api.dto.Anexo;
import br.com.softplan.desafio.api.dto.TicketEstatisticaCategoria;
import br.com.softplan.desafio.api.dto.TicketEstatisticaDia;
import br.com.softplan.desafio.api.event.RecursoCriadoEvent;
import br.com.softplan.desafio.api.exceptionhandler.modal.Erro;
import br.com.softplan.desafio.api.model.Ticket;
import br.com.softplan.desafio.api.repository.TicketRepository;
import br.com.softplan.desafio.api.repository.filter.TicketFilter;
import br.com.softplan.desafio.api.repository.projection.ResumoTicket;
import br.com.softplan.desafio.api.service.TicketService;
import br.com.softplan.desafio.api.service.exception.PessoaInexistenteOuInativaException;
import br.com.softplan.desafio.api.storage.StorageS3Config;

@RestController
@RequestMapping("/tickets")
public class TicketResource {
    
    @Autowired
    private TicketRepository ticketRepository;
    
    @Autowired
    private TicketService ticketService;
    
    @Autowired
    private ApplicationEventPublisher publisher;
    
    @Autowired
    private MessageSource messageSource;
    
    @Autowired
    private StorageS3Config s3;
    
    @PostMapping("/anexo")
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_TICKET') and #oauth2.hasScope('write')")
    public Anexo uploadAnexo(@RequestParam final MultipartFile anexo) throws IOException {
        final String nome = this.s3.salvarTemporariamente(anexo);
        return new Anexo(nome, this.s3.configurarUrl(nome));
    }
    
    @GetMapping("/relatorios/por-pessoa")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_TICKET') and #oauth2.hasScope('read')")
    public ResponseEntity<byte[]> relatorioPorPessoa(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") final LocalDate inicio,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") final LocalDate fim) throws Exception {
        final byte[] relatorio = this.ticketService.relatorioPorPessoa(inicio, fim);
        
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
                .body(relatorio);
    }
    
    @GetMapping("/estatisticas/por-dia")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_TICKET') and #oauth2.hasScope('read')")
    public List<TicketEstatisticaDia> porDia() {
        return this.ticketRepository.porDia(LocalDate.now());
    }
    
    @GetMapping("/estatisticas/por-categoria")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_TICKET') and #oauth2.hasScope('read')")
    public List<TicketEstatisticaCategoria> porCategoria() {
        return this.ticketRepository.porCategoria(LocalDate.now());
    }
    
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_TICKET') and #oauth2.hasScope('read')")
    public Page<Ticket> pesquisar(final TicketFilter ticketFilter, final Pageable pageable) {
        return this.ticketRepository.filtrar(ticketFilter, pageable);
    }
    
    @GetMapping(params = "resumo")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_TICKET') and #oauth2.hasScope('read')")
    public Page<ResumoTicket> resumir(final TicketFilter ticketFilter, final Pageable pageable) {
        return this.ticketRepository.resumir(ticketFilter, pageable);
    }
    
    @GetMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_TICKET') and #oauth2.hasScope('read')")
    public ResponseEntity<Ticket> buscarPeloCodigo(@PathVariable final Long codigo) {
        final Optional<Ticket> ticket = this.ticketRepository.findById(codigo);
        return ticket.isPresent() ? ResponseEntity.ok(ticket.get()) : ResponseEntity.notFound().build();
    }
    
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_TICKET') and #oauth2.hasScope('write')")
    public ResponseEntity<Ticket> criar(@Valid @RequestBody final Ticket formulario, final HttpServletResponse response) {
        final Ticket ticketSalvo = this.ticketService.salvar(formulario);
        this.publisher.publishEvent(new RecursoCriadoEvent(this, response, ticketSalvo.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketSalvo);
    }
    
    @ExceptionHandler({PessoaInexistenteOuInativaException.class})
    public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(final PessoaInexistenteOuInativaException ex) {
        final String mensagemUsuario = this.messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
        final String mensagemDesenvolvedor = ex.toString();
        final List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return ResponseEntity.badRequest().body(erros);
    }
    
    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_REMOVER_TICKET') and #oauth2.hasScope('write')")
    public void remover(@PathVariable final Long codigo) {
        this.ticketRepository.deleteById(codigo);
    }
    
    @PutMapping("/{codigo}")
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_TICKET')")
    public ResponseEntity<Ticket> atualizar(@PathVariable final Long codigo, @Valid @RequestBody final Ticket ticket) {
        try {
            final Ticket ticketSalvo = this.ticketService.atualizar(codigo, ticket);
            return ResponseEntity.ok(ticketSalvo);
        } catch (final IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
}
