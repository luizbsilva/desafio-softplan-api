package br.com.softplan.desafio.api.service;

import java.io.InputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.softplan.desafio.api.dto.TicketEstatisticaPessoa;
import br.com.softplan.desafio.api.mail.Mailer;
import br.com.softplan.desafio.api.model.Pessoa;
import br.com.softplan.desafio.api.model.Ticket;
import br.com.softplan.desafio.api.model.Usuario;
import br.com.softplan.desafio.api.repository.PessoaRepository;
import br.com.softplan.desafio.api.repository.TicketRepository;
import br.com.softplan.desafio.api.repository.UsuarioRepository;
import br.com.softplan.desafio.api.service.exception.PessoaInexistenteOuInativaException;
import br.com.softplan.desafio.api.storage.StorageS3Config;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class TicketService {
    
    private static final String DESTINATARIOS = "ROLE_PESQUISAR_TICKET";
    
    private static final Logger logger = LoggerFactory.getLogger(TicketService.class);
    
    @Autowired
    private PessoaRepository pessoaRepository;
    
    @Autowired
    private TicketRepository ticketRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private Mailer mailer;
    
    @Autowired
    private StorageS3Config s3;
    
    @Scheduled(cron = "0 0 6 * * *")
    public void avisarSobreTicketsVencidos() {
        if (logger.isDebugEnabled()) {
            logger.debug("Preparando envio de "
                    + "e-mails de aviso de tickets vencidos.");
        }
        
        final List<Ticket> vencidos = this.ticketRepository
                .findByDataCriacaoLessThanEqualAndDataVencimentoIsNull(LocalDate.now());
        
        if (vencidos.isEmpty()) {
            logger.info("Sem tickets vencidos para aviso.");
            
            return;
        }
        
        logger.info("Exitem {} Tickets vencidos.", vencidos.size());
        
        final List<Usuario> destinatarios = this.usuarioRepository
                .findByPermissoesDescricao(DESTINATARIOS);
        
        if (destinatarios.isEmpty()) {
            logger.warn("Existem lançamentos vencidos, mas o "
                    + "sistema não encontrou destinatários.");
            
            return;
        }
        
        this.mailer.avisarSobreTicketsVencidos(vencidos, destinatarios);
        
        logger.info("Envio de e-mail de aviso concluído.");
    }
    
    public byte[] relatorioPorPessoa(final LocalDate inicio, final LocalDate fim) throws Exception {
        final List<TicketEstatisticaPessoa> dados = this.ticketRepository.porPessoa(inicio, fim);
        
        final Map<String, Object> parametros = new HashMap<>();
        parametros.put("DT_INICIO", Date.valueOf(inicio));
        parametros.put("DT_FIM", Date.valueOf(fim));
        parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
        
        final InputStream inputStream = this.getClass().getResourceAsStream(
                "/relatorios/ticket-por-pessoa.jasper");
        
        final JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,
                new JRBeanCollectionDataSource(dados));
        
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
    
    public Ticket salvar(final Ticket ticket) {
        this.validarPessoa(ticket);
        this.validarUsuario(ticket);
        
        if (StringUtils.hasText(ticket.getAnexo())) {
            this.s3.salvar(ticket.getAnexo());
        }
        
        return this.ticketRepository.save(ticket);
    }
    
    public Ticket atualizar(final Long codigo, final Ticket ticket) {
        final Ticket ticketSalvo = this.buscarticketExistente(codigo);
        if (!ticket.getPessoa().equals(ticketSalvo.getPessoa())) {
            this.validarPessoa(ticket);
        }
        
        if (StringUtils.isEmpty(ticket.getAnexo())
                && StringUtils.hasText(ticketSalvo.getAnexo())) {
            this.s3.remover(ticketSalvo.getAnexo());
        } else if (StringUtils.hasText(ticket.getAnexo())
                && !ticket.getAnexo().equals(ticketSalvo.getAnexo())) {
            this.s3.substituir(ticketSalvo.getAnexo(), ticket.getAnexo());
        }
        
        BeanUtils.copyProperties(ticket, ticketSalvo, "codigo");
        
        return this.ticketRepository.save(ticketSalvo);
    }
    
    private void validarPessoa(final Ticket ticket) {
        Pessoa pessoa = null;
        if (ticket.getPessoa().getCodigo() != null) {
            pessoa = this.pessoaRepository.getOne(ticket.getPessoa().getCodigo());
        }
        
        if (pessoa == null || pessoa.isInativo()) {
            throw new PessoaInexistenteOuInativaException();
        }
    }
    
    private void validarUsuario(final Ticket ticket) {
        Usuario pessoa = null;
        if (ticket.getUsuario().getCodigo() != null) {
            pessoa = this.usuarioRepository.getOne(ticket.getUsuario().getCodigo());
        }
        
        if (pessoa == null) {
            throw new PessoaInexistenteOuInativaException();
        }
    }
    
    private Ticket buscarticketExistente(final Long codigo) {
        final Optional<Ticket> ticketSalvo = this.ticketRepository.findById(codigo);
        if (!ticketSalvo.isPresent()) {
            throw new IllegalArgumentException();
        }
        return ticketSalvo.get();
    }
    
}
