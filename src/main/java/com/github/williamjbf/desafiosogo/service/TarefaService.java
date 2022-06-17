package com.github.williamjbf.desafiosogo.service;

import com.github.williamjbf.desafiosogo.model.Tarefa;
import com.github.williamjbf.desafiosogo.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TarefaService {

    @Autowired
    TarefaRepository tarefaRepository;

    public List<Tarefa> gerarAgenda(int tempoMaximo){
        List<Tarefa> tarefasDesorganizada = tarefaRepository.tarefasPendentesPorTempo();
        List<Tarefa> tarefasOrganizada = new ArrayList<>();
        int tempoTarefasNaAgenda = 0;
        for(Tarefa tarefa:tarefasDesorganizada){
            if(tempoMaximo - tarefa.getMinutosNecessario() >= 0){
                tarefasOrganizada.add(tarefa);
                tempoMaximo -= tarefa.getMinutosNecessario();
            }
        }

        return tarefasOrganizada;
    }

}
