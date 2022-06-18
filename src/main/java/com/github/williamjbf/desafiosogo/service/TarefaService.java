package com.github.williamjbf.desafiosogo.service;

import com.github.williamjbf.desafiosogo.model.Tarefa;
import com.github.williamjbf.desafiosogo.repository.TarefaRepository;
import com.github.williamjbf.desafiosogo.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TarefaService {

    @Autowired
    TarefaRepository tarefaRepository;

    public List<Tarefa> gerarAgenda(int tempoMaximo){
        List<Tarefa> tarefasDesorganizada = tarefaRepository.tarefasPendentesPorTempo();
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
        if(dia != null && semana != null){
            LocalDate diaBuscar = DateUtil.diaNaSemana(dia, semana,mes);
            return tarefaRepository.terafasPendenteNoDia(diaBuscar);
        }

        if(dia != null){
            LocalDate diaBuscar = DateUtil.dia(dia, mes);
            return tarefaRepository.terafasPendenteNoDia(diaBuscar);
        }

        if(semana != null){
            LocalDate primeiroDiaSemana = DateUtil.primeiroDiaDaSemana(semana,mes);
            LocalDate ultimoDiaSemana = DateUtil.ultimoDiaDaSemana(semana,mes);
            return tarefaRepository.tarefasPendenteNaSemana(primeiroDiaSemana,ultimoDiaSemana);
        }

        return tarefaRepository.tarefasPendentesPorMes(mes);
    }

}
