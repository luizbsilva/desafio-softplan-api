package br.com.softplan.desafio.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.softplan.desafio.api.model.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
    
    public Page<Permissao> findByDescricaoContaining(String nome, Pageable pageable);
    
}
