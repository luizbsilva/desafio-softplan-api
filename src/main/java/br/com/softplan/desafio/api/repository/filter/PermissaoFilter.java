package br.com.softplan.desafio.api.repository.filter;

import br.com.softplan.desafio.api.model.Permissao;

public class PermissaoFilter {
    
    private Long codigo;
    
    private String descricao;
    
    private Boolean selecionado;
    
    public PermissaoFilter() {}
    
    public PermissaoFilter(final Permissao permissao, final Boolean permissaoUltilizada) {
        this.codigo = permissao.getCodigo();
        this.descricao = permissao.getDescricao();
        this.selecionado = permissaoUltilizada;
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
    
    public Boolean getSelecionado() {
        return this.selecionado;
    }
    
    public void setSelecionado(final Boolean selecionado) {
        this.selecionado = selecionado;
    }
    
}
