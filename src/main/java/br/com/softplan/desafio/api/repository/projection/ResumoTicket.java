package br.com.softplan.desafio.api.repository.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.softplan.desafio.api.model.TipoTicket;

public class ResumoTicket {
    
    private Long codigo;
    
    private String descricao;
    
    private LocalDate dataVencimento;
    
    private LocalDate dataPagamento;
    
    private BigDecimal valor;
    
    private TipoTicket tipo;
    
    private String categoria;
    
    private String pessoa;
    
    public ResumoTicket(final Long codigo, final String descricao, final LocalDate dataVencimento, final LocalDate dataPagamento,
            final BigDecimal valor, final TipoTicket tipo, final String categoria, final String pessoa) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.valor = valor;
        this.tipo = tipo;
        this.categoria = categoria;
        this.pessoa = pessoa;
    }
    
    public Long getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(final Long codigo) {
        this.codigo = codigo;
    }
    
    public String getDescricao() {
        return this.descricao;
    }
    
    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }
    
    public LocalDate getDataVencimento() {
        return this.dataVencimento;
    }
    
    public void setDataVencimento(final LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
    
    public LocalDate getDataPagamento() {
        return this.dataPagamento;
    }
    
    public void setDataPagamento(final LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
    
    public BigDecimal getValor() {
        return this.valor;
    }
    
    public void setValor(final BigDecimal valor) {
        this.valor = valor;
    }
    
    public TipoTicket getTipo() {
        return this.tipo;
    }
    
    public void setTipo(final TipoTicket tipo) {
        this.tipo = tipo;
    }
    
    public String getCategoria() {
        return this.categoria;
    }
    
    public void setCategoria(final String categoria) {
        this.categoria = categoria;
    }
    
    public String getPessoa() {
        return this.pessoa;
    }
    
    public void setPessoa(final String pessoa) {
        this.pessoa = pessoa;
    }
    
}
