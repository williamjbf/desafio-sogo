package com.github.williamjbf.desafiosogo.service;

import com.github.williamjbf.desafiosogo.model.Projeto;
import com.github.williamjbf.desafiosogo.model.Tarefa;
import com.github.williamjbf.desafiosogo.repository.ProjetoRepository;
import com.github.williamjbf.desafiosogo.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjetoService {

    @Autowired
    ProjetoRepository projetoRepository;

    @Autowired
    TarefaRepository tarefaRepository;


    public Projeto criarProjeto(Projeto projeto){
        return projetoRepository.save(projeto);
    }
//    public Projeto criarProjeto(String titulo){
//        Projeto projeto = new Projeto();
//        projeto.setTitulo(titulo);
//        return projetoRepository.save(projeto);
//    }

    public ResponseEntity<Projeto> adicionarTarefa(Long idProjeto, Long idTarefa){
        Optional<Projeto> projeto = projetoRepository.findById(idProjeto);
        Optional<Tarefa> tarefa = tarefaRepository.findById(idTarefa);

        if(projeto.isPresent() && tarefa.isPresent()){

            Projeto projetoAchado = projeto.get();
            Tarefa tarefaAchada = tarefa.get();

            projetoAchado.adicionarTarefa(tarefaAchada);
            projetoRepository.save(projeto.get());

            return ResponseEntity.ok(projetoAchado);
        }

        return ResponseEntity.notFound().build();
    }
}
