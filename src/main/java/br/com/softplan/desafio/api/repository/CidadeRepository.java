package br.com.softplan.desafio.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.softplan.desafio.api.model.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    
    List<Cidade> findByEstadoCodigo(Long estadoCodigo);
    
}
