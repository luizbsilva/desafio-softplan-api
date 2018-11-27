package br.com.softplan.desafio.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.softplan.desafio.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    public Optional<Usuario> findByEmail(String email);
    
    public List<Usuario> findByPermissoesDescricao(String permissaoDescricao);
    
    public Page<Usuario> findByNomeContaining(String nome, Pageable pageable);
    
}
