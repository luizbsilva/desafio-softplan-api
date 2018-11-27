package br.com.softplan.desafio.api.dto;

import java.math.BigDecimal;

import br.com.softplan.desafio.api.model.Categoria;

public class TicketEstatisticaCategoria {
    
    private Categoria categoria;
    
    private BigDecimal total;
    
    public TicketEstatisticaCategoria(final Categoria categoria, final BigDecimal total) {
        this.categoria = categoria;
        this.total = total;
    }
    
    public Categoria getCategoria() {
        return this.categoria;
    }
    
    public void setCategoria(final Categoria categoria) {
        this.categoria = categoria;
    }
    
    public BigDecimal getTotal() {
        return this.total;
    }
    
    public void setTotal(final BigDecimal total) {
        this.total = total;
    }
}
