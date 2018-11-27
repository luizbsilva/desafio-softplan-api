package br.com.softplan.desafio.api.mail;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.com.softplan.desafio.api.model.Ticket;
import br.com.softplan.desafio.api.model.Usuario;

@Component
public class Mailer {
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    private TemplateEngine thymeleaf;
    
    public void avisarSobreTicketsVencidos(
            final List<Ticket> vencidos, final List<Usuario> destinatarios) {
        final Map<String, Object> variaveis = new HashMap<>();
        variaveis.put("tickets", vencidos);
        
        final List<String> emails = destinatarios.stream()
                .map(u -> u.getEmail())
                .collect(Collectors.toList());
        
        this.enviarEmail("testes.desafio@gmail.com",
                emails,
                "Tickets vencidos",
                "mail/aviso-tickets-vencidos",
                variaveis);
    }
    
    public void enviarEmail(final String remetente,
            final List<String> destinatarios, final String assunto, final String template,
            final Map<String, Object> variaveis) {
        final Context context = new Context(new Locale("pt", "BR"));
        
        variaveis.entrySet().forEach(
                e -> context.setVariable(e.getKey(), e.getValue()));
        
        final String mensagem = this.thymeleaf.process(template, context);
        
        this.enviarEmail(remetente, destinatarios, assunto, mensagem);
    }
    
    public void enviarEmail(final String remetente,
            final List<String> destinatarios, final String assunto, final String mensagem) {
        try {
            final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            
            final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
            helper.setFrom(remetente);
            helper.setTo(destinatarios.toArray(new String[destinatarios.size()]));
            helper.setSubject(assunto);
            helper.setText(mensagem, true);
            
            this.mailSender.send(mimeMessage);
        } catch (final MessagingException e) {
            throw new RuntimeException("Problemas com o envio de e-mail!", e);
        }
    }
}
