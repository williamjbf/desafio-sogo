package com.github.williamjbf.desafiosogo.controller;

import com.github.williamjbf.desafiosogo.model.Projeto;
import com.github.williamjbf.desafiosogo.repository.ProjetoRepository;
import com.github.williamjbf.desafiosogo.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("projetos")
public class ProjetoController {

    @Autowired
    ProjetoRepository projetoRepository;
    @Autowired
    ProjetoService projetoService;

    @GetMapping
    public List<Projeto> listar(){
        return projetoRepository.findAll();
    }

    @PostMapping
    public Projeto criarProjeto(@RequestBody @Valid Projeto projeto){
        return projetoService.criarProjeto(projeto);
    }

    @PutMapping("{idProjeto}/adicionar/{idTarefa}")
    public ResponseEntity<Projeto> adicionarTarefa(@PathVariable Long idProjeto, @PathVariable Long idTarefa){
        return projetoService.adicionarTarefa(idProjeto,idTarefa);
    }

}
