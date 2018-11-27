package br.com.softplan.desafio.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.softplan.desafio.api.repository.listener.TicketAnexoListener;

@EntityListeners(TicketAnexoListener.class)
@Entity
@Table(name = "ticket")
public class Ticket {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    
    @NotNull
    private String descricao;
    
    @NotNull
    @Column(name = "data_cricao")
    private LocalDate dataCriacao;
    
    @NotNull
    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;
    
    @NotNull
    private BigDecimal valor;
    
    private String observacao;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoTicket tipo;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "codigo_categoria")
    private Categoria categoria;
    
    @ManyToOne
    @JoinColumn(name = "codigo_usuario")
    private Usuario usuario;
    
    @JsonIgnoreProperties("contatos")
    @NotNull
    @ManyToOne
    @JoinColumn(name = "codigo_pessoa")
    private Pessoa pessoa;
    
    private String anexo;
    
    @Transient
    private String urlAnexo;
    
    @JsonIgnore
    public boolean isOrcamento() {
        return TipoTicket.ORCAMENTO.equals(this.tipo);
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
    
    public LocalDate getDataCriacao() {
        return this.dataCriacao;
    }
    
    public void setDataCriacao(final LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    
    public LocalDate getDataVencimento() {
        return this.dataVencimento;
    }
    
    public void setDataVencimento(final LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
    
    public BigDecimal getValor() {
        return this.valor;
    }
    
    public void setValor(final BigDecimal valor) {
        this.valor = valor;
    }
    
    public String getObservacao() {
        return this.observacao;
    }
    
    public void setObservacao(final String observacao) {
        this.observacao = observacao;
    }
    
    public TipoTicket getTipo() {
        return this.tipo;
    }
    
    public void setTipo(final TipoTicket tipo) {
        this.tipo = tipo;
    }
    
    public Categoria getCategoria() {
        return this.categoria;
    }
    
    public void setCategoria(final Categoria categoria) {
        this.categoria = categoria;
    }
    
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(final Usuario usuario) {
        this.usuario = usuario;
    }
    
    public Pessoa getPessoa() {
        return this.pessoa;
    }
    
    public void setPessoa(final Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    public String getAnexo() {
        return this.anexo;
    }
    
    public void setAnexo(final String anexo) {
        this.anexo = anexo;
    }
    
    public String getUrlAnexo() {
        return this.urlAnexo;
    }
    
    public void setUrlAnexo(final String urlAnexo) {
        this.urlAnexo = urlAnexo;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.codigo == null) ? 0 : this.codigo.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final Ticket other = (Ticket) obj;
        if (this.codigo == null) {
            if (other.codigo != null) {
                return false;
            }
        } else if (!this.codigo.equals(other.codigo)) {
            return false;
        }
        return true;
    }
    
}
