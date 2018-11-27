package br.com.softplan.desafio.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.desafio.api.model.Estado;
import br.com.softplan.desafio.api.repository.EstadoRepository;

@RestController
@RequestMapping("/estados")
public class EstadoResource {
    
    @Autowired
    private EstadoRepository estadoRepository;
    
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<Estado> listar() {
        return this.estadoRepository.findAll();
    }
}
