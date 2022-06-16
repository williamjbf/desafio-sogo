package com.github.williamjbf.desafiosogo.repository;

import com.github.williamjbf.desafiosogo.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    @Query
    List<Tarefa> findAllByOrderByStatus();
}
