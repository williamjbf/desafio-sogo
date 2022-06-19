package com.github.williamjbf.desafiosogo.service;

import com.github.williamjbf.desafiosogo.model.Projeto;
import com.github.williamjbf.desafiosogo.model.Tarefa;
import com.github.williamjbf.desafiosogo.model.Usuario;
import com.github.williamjbf.desafiosogo.repository.ProjetoRepository;
import com.github.williamjbf.desafiosogo.repository.TarefaRepository;
import com.github.williamjbf.desafiosogo.repository.UsuarioRepository;
import com.github.williamjbf.desafiosogo.util.LogadoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjetoService {

    @Autowired
    ProjetoRepository projetoRepository;

    @Autowired
    TarefaRepository tarefaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    public ResponseEntity<List<Projeto>> listarTodosProjetos(){
        if (LogadoUtil.isAdmin()){
            List<Projeto> projetos = projetoRepository.findAll();
            return ResponseEntity.ok(projetos);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    public List<Projeto> listarProjetoUsuarioAtual(){
        Usuario usuario = usuarioRepository.findByLogin(LogadoUtil.usuarioLogado()).get();
        return projetoRepository.findAllByUsuarioId(usuario.getId());
    }

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
            Usuario usuarioLogado = usuarioRepository.findByLogin(LogadoUtil.usuarioLogado()).get();
            Projeto projetoAchado = projeto.get();
            Tarefa tarefaAchada = tarefa.get();

            if(usuarioIsDono(projetoAchado.getId(), usuarioLogado) || LogadoUtil.isAdmin()){
                projetoAchado.adicionarTarefas(tarefaAchada);
                projetoRepository.save(projetoAchado);

                return ResponseEntity.ok(projetoAchado);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.notFound().build();
    }

    private boolean usuarioIsDono(long projetoId, Usuario usuario){
        return projetoRepository.usuarioResponsavel(projetoId).equals(usuario.getLogin());
    }
}
