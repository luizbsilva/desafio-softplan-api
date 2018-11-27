package br.com.softplan.desafio.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.softplan.desafio.api.model.Permissao;
import br.com.softplan.desafio.api.model.PermissaoUsuario;
import br.com.softplan.desafio.api.model.Usuario;
import br.com.softplan.desafio.api.repository.PermissaoRepository;
import br.com.softplan.desafio.api.repository.PermissaoUsuarioRepository;
import br.com.softplan.desafio.api.repository.filter.PermissaoFilter;

@Service
public class PermissaoService {
    
    @Autowired
    private PermissaoRepository permissaoRepository;
    
    @Autowired
    private PermissaoUsuarioRepository permissaoUsuarioRepository;
    
    @Autowired
    private UsuarioService usuarioService;
    
    public void atualizarPropriedadeAtivo(final Long codigoPermissao, final Long codigoUsuario, final Boolean ativo) {
        final Usuario usuario = this.usuarioService.buscarUsuarioPeloCodigo(codigoUsuario);
        final Permissao permissao = this.buscarPermissaoPeloCodigo(codigoPermissao);
        
        if (!ativo) {
            final PermissaoUsuario permissaoUsuario = this.permissaoUsuarioRepository.findByPermissaoAndUsuario(permissao, usuario);
            this.permissaoUsuarioRepository.delete(permissaoUsuario);
            
        } else {
            
            final PermissaoUsuario permissaoUsuario = new PermissaoUsuario();
            permissaoUsuario.setPermissao(permissao);
            permissaoUsuario.setUsuario(usuario);
            
            this.permissaoUsuarioRepository.save(permissaoUsuario);
            
        }
    }
    
    public List<PermissaoFilter> buscarPermissaoUsuario(final Long codigo) {
        final List<PermissaoFilter> permissaoFilter = new ArrayList<>();
        final List<Permissao> permissoes = this.permissaoRepository.findAll();
        final Usuario usuario = this.usuarioService.buscarUsuarioPeloCodigo(codigo);
        
        for (final Permissao permissao : permissoes) {
            permissaoFilter.add(new PermissaoFilter(permissao, validarPorcedimentoUltilizado(usuario.getPermissoes(), permissao)));
            
        }
        return permissaoFilter;
    }
    
    public static Boolean validarPorcedimentoUltilizado(final List<Permissao> permissoes, final Permissao permissao) {
        return permissoes.contains(permissao);
        
    }
    
    public Permissao buscarPermissaoPeloCodigo(final Long codigo) {
        final Optional<Permissao> permissao = this.permissaoRepository.findById(codigo);
        if (!permissao.isPresent()) {
            throw new EmptyResultDataAccessException(1);
        }
        return permissao.get();
    }
    
}
