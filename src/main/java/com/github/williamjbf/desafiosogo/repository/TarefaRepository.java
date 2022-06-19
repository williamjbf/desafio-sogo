package com.github.williamjbf.desafiosogo.repository;

import com.github.williamjbf.desafiosogo.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    @Query
    List<Tarefa> findAllByOrderByStatus();

    @Query(value = "select * from tarefa where status_id = 1 order by prioridade desc , minutos_necessario", nativeQuery = true)
    List<Tarefa> tarefasPendentesPorTempo();

    @Query(value = "select * from tarefa where status_id =1 and extract(month from dia_agendado)= :mes", nativeQuery = true)
    List<Tarefa> tarefasPendentesPorMes(int mes);
    @Query(value = "select * from tarefa where status_id =1 and dia_agendado = :dia", nativeQuery = true)
    List<Tarefa> terafasPendenteNoDia(LocalDate dia);

    @Query(value = "select * from tarefa where status_id =1 and (dia_agendado between :primeiroDia and :ultimoDia)", nativeQuery = true)
    List<Tarefa> tarefasPendenteNaSemana(LocalDate primeiroDia, LocalDate ultimoDia);

    @Transactional
    @Modifying
    @Query(value = "update tarefa set projeto_id = :projeto_id where id = :tarefa_id",nativeQuery = true)
    void adicionarIdProjeto(long tarefa_id ,long projeto_id);
}
