package br.com.softplan.desafio.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.softplan.desafio.api.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    
    public Page<Pessoa> findByNomeContaining(String nome, Pageable pageable);
    
}
