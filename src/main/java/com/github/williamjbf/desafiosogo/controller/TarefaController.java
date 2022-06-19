package com.github.williamjbf.desafiosogo.controller;

import com.github.williamjbf.desafiosogo.model.Tarefa;
import com.github.williamjbf.desafiosogo.repository.TarefaRepository;
import com.github.williamjbf.desafiosogo.service.TarefaService;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public Tarefa criar(@RequestBody @Valid Tarefa tarefa){
        return tarefaRepository.save(tarefa);
    }

    @GetMapping("agenda")
    public List<Tarefa> gerarAgenda(@RequestParam(name = "tempoMaximo") int tempoMaximo){
        return tarefaService.gerarAgenda(tempoMaximo);
    }

    @GetMapping("pendentes")
    public List<Tarefa> pendentesPorTempo(
            @RequestParam(required = true,name = "mes") Integer mes,
            @RequestParam(required = false, name = "semana")Integer semana,
            @RequestParam(required = false, name = "dia")Integer dia
    ){
        return tarefaService.buscarTarefasPendentePorPeriodo(dia,semana,mes);
    }
}
