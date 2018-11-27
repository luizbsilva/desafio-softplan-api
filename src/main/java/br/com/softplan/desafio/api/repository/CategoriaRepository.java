package br.com.softplan.desafio.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.softplan.desafio.api.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
