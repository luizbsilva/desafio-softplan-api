package br.com.softplan.desafio.api.dto;

import java.math.BigDecimal;

import br.com.softplan.desafio.api.model.Pessoa;
import br.com.softplan.desafio.api.model.TipoTicket;

public class TicketEstatisticaPessoa {
    
    private TipoTicket tipo;
    
    private Pessoa pessoa;
    
    private BigDecimal total;
    
    public TicketEstatisticaPessoa(final TipoTicket tipo, final Pessoa pessoa, final BigDecimal total) {
        this.tipo = tipo;
        this.pessoa = pessoa;
        this.total = total;
    }
    
    public TipoTicket getTipo() {
        return this.tipo;
    }
    
    public void setTipo(final TipoTicket tipo) {
        this.tipo = tipo;
    }
    
    public Pessoa getPessoa() {
        return this.pessoa;
    }
    
    public void setPessoa(final Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    public BigDecimal getTotal() {
        return this.total;
    }
    
    public void setTotal(final BigDecimal total) {
        this.total = total;
    }
}
