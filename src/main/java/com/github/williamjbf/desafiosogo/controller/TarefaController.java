package com.github.williamjbf.desafiosogo.controller;

import com.github.williamjbf.desafiosogo.model.Projeto;
import com.github.williamjbf.desafiosogo.model.Tarefa;
import com.github.williamjbf.desafiosogo.repository.TarefaRepository;
import com.github.williamjbf.desafiosogo.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/listar")
    public ResponseEntity<List<Tarefa>> listarTodasTarefas(){
        return tarefaService.listarTodasTarefas();
    }

    @GetMapping
    public List<Tarefa> exibirTarefaUsuarioAtual(){
        return tarefaService.listarTarefaUsuarioAtual();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tarefa criar(@RequestParam(required = false,name = "projeto_id") Long projetoId,@RequestBody @Valid Tarefa tarefa){
        return tarefaService.criarTarefa(tarefa,projetoId);
    }

    @GetMapping("agenda")
    public List<Tarefa> gerarAgenda(){
        return tarefaService.gerarAgendaDoUsuario();
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
