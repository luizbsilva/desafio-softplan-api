package br.com.softplan.desafio.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.softplan.desafio.api.model.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Long> {
    
}
