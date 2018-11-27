package br.com.softplan.desafio.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.softplan.desafio.api.model.TipoTicket;

public class TicketEstatisticaDia {
    
    private TipoTicket tipo;
    
    private LocalDate dia;
    
    private BigDecimal total;
    
    public TicketEstatisticaDia(final TipoTicket tipo, final LocalDate dia, final BigDecimal total) {
        this.tipo = tipo;
        this.dia = dia;
        this.total = total;
    }
    
    public TipoTicket getTipo() {
        return this.tipo;
    }
    
    public void setTipo(final TipoTicket tipo) {
        this.tipo = tipo;
    }
    
    public LocalDate getDia() {
        return this.dia;
    }
    
    public void setDia(final LocalDate dia) {
        this.dia = dia;
    }
    
    public BigDecimal getTotal() {
        return this.total;
    }
    
    public void setTotal(final BigDecimal total) {
        this.total = total;
    }
}
