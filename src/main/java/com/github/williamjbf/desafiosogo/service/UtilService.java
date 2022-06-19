package com.github.williamjbf.desafiosogo.service;

import com.github.williamjbf.desafiosogo.model.Projeto;
import com.github.williamjbf.desafiosogo.model.Status;
import com.github.williamjbf.desafiosogo.model.Tarefa;
import com.github.williamjbf.desafiosogo.model.Usuario;
import com.github.williamjbf.desafiosogo.repository.ProjetoRepository;
import com.github.williamjbf.desafiosogo.repository.StatusRepository;
import com.github.williamjbf.desafiosogo.repository.TarefaRepository;
import com.github.williamjbf.desafiosogo.repository.UsuarioRepository;
import com.github.williamjbf.desafiosogo.util.DateUtil;
import com.github.williamjbf.desafiosogo.util.TextoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UtilService {

    private final PasswordEncoder encoder;

    private String[] nomeUsuarios = {"Paulo","Maria","Fernando","Fernanda","Jorge"};
    private String[] nomeTarefas = {"Tarefa 1","tarefa 2","tarefa 3", "tarefa 4", "tarefa 5",
            "tarefa 6", "tarefa 7", "tarefa 8", "tarefa 9","tarefa 10"};
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ProjetoRepository projetoRepository;

    @Autowired
    TarefaRepository tarefaRepository;

    @Autowired
    StatusRepository statusRepository;

    public UtilService(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public void popular(){
        Status pendente = new Status();
        pendente.setDescricao("PENDENTE");
        Status concluido = new Status();
        concluido.setDescricao("CONCLUIDO");
        statusRepository.save(pendente);
        statusRepository.save(concluido);

        popularUsuario();
    }

    private void popularUsuario(){
        Usuario usuario = new Usuario();
        usuario.setNome("admin");
        usuario.setTempoDisponivelMinutos(500);
        usuario.setLogin("admin");
        usuario.setSenha(encoder.encode("admin"));
        usuarioRepository.save(usuario);

        popularProjeto(usuario.getId());

        for (int i=0; i< nomeUsuarios.length; i++) {
            usuario = new Usuario();
            usuario.setNome(nomeUsuarios[i]);
            usuario.setTempoDisponivelMinutos(gerarNumero(500));
            usuario.setLogin(TextoUtil.gerarTextoAleatorio(10));
            usuario.setSenha(encoder.encode(TextoUtil.gerarTextoAleatorio(10)));

            usuario = usuarioRepository.save(usuario);

            popularProjeto(usuario.getId());
        }
    }

    private void popularProjeto(long usuarioId){
        Projeto projeto = new Projeto();
        projeto.setTitulo("padrao");
        projeto = projetoRepository.save(projeto);
        projetoRepository.adicionarIdUsuario(projeto.getId(),usuarioId);

        popularTarefa(projeto.getId());
    }

    private void popularTarefa(long projetoId){
        for(String nomeTarefa:nomeTarefas){
            Tarefa tarefa = new Tarefa();
            tarefa.setTitulo(nomeTarefa);
            tarefa.setMinutosNecessario(gerarNumero(100));
            tarefa.setFrequencia(gerarNumero(10));
            tarefa.setPrioridade(gerarNumero(5));
            tarefa.setStatus(gerarStatusTarefa());
            tarefa.setDiaAgendado(
                    DateUtil.dia(gerarNumero(30),6)
            );
            tarefa = tarefaRepository.save(tarefa);
            tarefaRepository.adicionarIdProjeto(tarefa.getId(),projetoId);
        }
    }

    private Status gerarStatusTarefa(){
        return statusRepository.findById((long) gerarNumero(1)).get();
    }

    private int gerarNumero(int max){
        Random random = new Random();
        return random.nextInt(max)+1;
    }

}
