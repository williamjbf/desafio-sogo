package com.github.williamjbf.desafiosogo.repository;

import com.github.williamjbf.desafiosogo.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    @Query
    List<Tarefa> findAllByOrderByStatus();

    @Query(value = "select * from tarefa where status_id = 1 order by prioridade desc , minutos_necessario", nativeQuery = true)
    List<Tarefa> tarefasPendentesPorTempo();
}
