package com.github.williamjbf.desafiosogo.controller;

import com.github.williamjbf.desafiosogo.model.Tarefa;
import com.github.williamjbf.desafiosogo.repository.TarefaRepository;
import com.github.williamjbf.desafiosogo.service.TarefaService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tarefas")
public class TarefaController {

    @Autowired
    TarefaRepository tarefaRepository;

    @Autowired
    TarefaService tarefaService;

    @GetMapping
    public List<Tarefa> listar(){
        return tarefaRepository.findAllByOrderByStatus();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tarefa criar(@RequestBody Tarefa tarefa){
        return tarefaRepository.save(tarefa);
    }

    @GetMapping("agenda")
    public List<Tarefa> gerarAgenda(@RequestParam(name = "tempoMaximo") Integer tempoMaximo){
        return tarefaService.gerarAgenda(tempoMaximo);
    }
}
