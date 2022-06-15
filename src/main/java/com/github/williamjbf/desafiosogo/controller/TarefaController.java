package com.github.williamjbf.desafiosogo.controller;

import com.github.williamjbf.desafiosogo.model.Tarefa;
import com.github.williamjbf.desafiosogo.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tarefas")
public class TarefaController {

    @Autowired
    TarefaRepository tarefaRepository;

    @PostMapping
    public ResponseEntity<Tarefa> criar(@RequestBody Tarefa tarefa){

        return ResponseEntity.badRequest().build();
    }
}
