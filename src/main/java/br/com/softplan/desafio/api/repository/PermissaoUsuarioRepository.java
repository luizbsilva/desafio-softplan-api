package br.com.softplan.desafio.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.softplan.desafio.api.model.Permissao;
import br.com.softplan.desafio.api.model.PermissaoUsuario;
import br.com.softplan.desafio.api.model.Usuario;

public interface PermissaoUsuarioRepository extends JpaRepository<PermissaoUsuario, Long> {
    
    PermissaoUsuario findByPermissaoAndUsuario(final Permissao permissao, final Usuario usuario);
    
}
