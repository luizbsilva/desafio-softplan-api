package br.com.softplan.desafio.api.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class TicketFilter {
    
    private String descricao;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataVencimentoDe;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataVencimentoAte;
    
    public String getDescricao() {
        return this.descricao;
    }
    
    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }
    
    public LocalDate getDataVencimentoDe() {
        return this.dataVencimentoDe;
    }
    
    public void setDataVencimentoDe(final LocalDate dataVencimentoDe) {
        this.dataVencimentoDe = dataVencimentoDe;
    }
    
    public LocalDate getDataVencimentoAte() {
        return this.dataVencimentoAte;
    }
    
    public void setDataVencimentoAte(final LocalDate dataVencimentoAte) {
        this.dataVencimentoAte = dataVencimentoAte;
    }
    
}
