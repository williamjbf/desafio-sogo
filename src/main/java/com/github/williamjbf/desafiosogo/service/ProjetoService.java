package com.github.williamjbf.desafiosogo.service;

import com.github.williamjbf.desafiosogo.model.Projeto;
import com.github.williamjbf.desafiosogo.model.Tarefa;
import com.github.williamjbf.desafiosogo.repository.ProjetoRepository;
import com.github.williamjbf.desafiosogo.repository.TarefaRepository;
import com.github.williamjbf.desafiosogo.repository.UsuarioRepository;
import com.github.williamjbf.desafiosogo.util.LogadoUtil;
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

    @Autowired
    UsuarioRepository usuarioRepository;


    public Projeto criarProjeto(Projeto projeto){
        projeto = projetoRepository.save(projeto);

        long usuarioId = usuarioRepository.findByLogin(LogadoUtil.usuarioLogado()).get().getId();

        projetoRepository.adicionarIdUsuario(projeto.getId(),usuarioId);
        return projeto;
    }

    public ResponseEntity<Projeto> adicionarTarefa(Long idProjeto, Long idTarefa){
        Optional<Projeto> projeto = projetoRepository.findById(idProjeto);
        Optional<Tarefa> tarefa = tarefaRepository.findById(idTarefa);

        if(projeto.isPresent() && tarefa.isPresent()){

            Projeto projetoAchado = projeto.get();
            Tarefa tarefaAchada = tarefa.get();

            projetoAchado.adicionarTarefas(tarefaAchada);
            projetoRepository.save(projetoAchado);

            return ResponseEntity.ok(projetoAchado);
        }

        return ResponseEntity.notFound().build();
    }
}
