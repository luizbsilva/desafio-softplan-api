package br.com.softplan.desafio.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.desafio.api.repository.filter.PermissaoFilter;
import br.com.softplan.desafio.api.service.PermissaoService;

@RestController
@RequestMapping("/permissoes")
public class PermissaoResource {
    
    @Autowired
    private PermissaoService permissaoService;
    
    @GetMapping("/por-usuario")
    @PreAuthorize("hasAuthority('ROLE_PESQUISAR_PERMISSAO') and #oauth2.hasScope('read')")
    public List<PermissaoFilter> listar(final Long codigo) {
        return this.permissaoService.buscarPermissaoUsuario(codigo);
    }
    
    @PutMapping("/{codigoPermissao}/{codigoUsuario}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_CADASTRAR_PERMISSAO') and #oauth2.hasScope('write')")
    public void atualizarPropriedadeAtivo(@PathVariable final Long codigoPermissao, @PathVariable final Long codigoUsuario, @RequestBody final Boolean ativo) {
        this.permissaoService.atualizarPropriedadeAtivo(codigoPermissao, codigoUsuario, ativo);
    }
    
}
