package com.github.williamjbf.desafiosogo.service;

import com.github.williamjbf.desafiosogo.model.Projeto;
import com.github.williamjbf.desafiosogo.model.Tarefa;
import com.github.williamjbf.desafiosogo.model.Usuario;
import com.github.williamjbf.desafiosogo.repository.TarefaRepository;
import com.github.williamjbf.desafiosogo.repository.UsuarioRepository;
import com.github.williamjbf.desafiosogo.util.DateUtil;
import com.github.williamjbf.desafiosogo.util.LogadoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.ws.Response;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TarefaService {

    @Autowired
    TarefaRepository tarefaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    public ResponseEntity<List<Tarefa>> listarTodasTarefas() {
        if (LogadoUtil.isAdmin()){
            List<Tarefa> tarefas = tarefaRepository.findAll();
            return ResponseEntity.ok(tarefas);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    public List<Tarefa> listarTarefaUsuarioAtual(){
        Usuario usuario = usuarioRepository.findByLogin(LogadoUtil.usuarioLogado()).get();
        return tarefaRepository.tarefasPorDono(usuario.getId());
    }

    public Tarefa criarTarefa(Tarefa tarefa, Long projetoId){
        if(projetoId == null){
            Usuario usuarioLogado = usuarioRepository.findByLogin(LogadoUtil.usuarioLogado()).get();
            Projeto projeto = usuarioLogado.getProjetos().get(0);
            projetoId = projeto.getId();
        }
        tarefa = tarefaRepository.save(tarefa);
        tarefaRepository.adicionarIdProjeto(tarefa.getId(),projetoId);
        return tarefa;
    }

    public List<Tarefa> gerarAgendaDoUsuario(){

        Usuario usuario = usuarioRepository.findByLogin(LogadoUtil.usuarioLogado()).get();
        int tempoMaximo = usuario.getTempoDisponivelMinutos();

        List<Tarefa> tarefasDesorganizada = tarefaRepository.tarefasPorDonoOrdenadaPorPrioridadeETempo(usuario.getId());
        List<Tarefa> tarefasOrganizada = new ArrayList<>();
        for(Tarefa tarefa:tarefasDesorganizada){
            if(tempoMaximo - tarefa.getMinutosNecessario() >= 0){
                tarefasOrganizada.add(tarefa);
                tempoMaximo -= tarefa.getMinutosNecessario();
            }
        }

        return tarefasOrganizada;
    }

    public List<Tarefa> buscarTarefasPendentePorPeriodo(Integer dia, Integer semana, Integer mes){
        Usuario usuario = usuarioRepository.findByLogin(LogadoUtil.usuarioLogado()).get();
        long idUsuario = usuario.getId();
        if(dia != null && semana != null){
            LocalDate diaBuscar = DateUtil.diaNaSemana(dia, semana,mes);
            return tarefaRepository.terafasPendenteNoDia(diaBuscar,idUsuario);
        }

        if(dia != null){
            LocalDate diaBuscar = DateUtil.dia(dia, mes);
            return tarefaRepository.terafasPendenteNoDia(diaBuscar,idUsuario);
        }

        if(semana != null){
            LocalDate primeiroDiaSemana = DateUtil.primeiroDiaDaSemana(semana,mes);
            LocalDate ultimoDiaSemana = DateUtil.ultimoDiaDaSemana(semana,mes);
            return tarefaRepository.tarefasPendenteNaSemana(primeiroDiaSemana,ultimoDiaSemana,idUsuario);
        }

        return tarefaRepository.tarefasPendentesPorMes(mes,idUsuario);
    }

    private boolean usuarioIsDono(long tarefaId, Usuario usuario){
        return tarefaRepository.usuarioResponsavel(tarefaId).equals(usuario.getLogin());
    }


}
