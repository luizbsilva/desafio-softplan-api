package br.com.softplan.desafio.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.desafio.api.model.Cidade;
import br.com.softplan.desafio.api.repository.CidadeRepository;

@RestController
@RequestMapping("/cidades")
public class CidadeResource {
    
    @Autowired
    private CidadeRepository cidadeRepository;
    
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<Cidade> pesquisar(@RequestParam final Long estado) {
        return this.cidadeRepository.findByEstadoCodigo(estado);
    }
    
}
