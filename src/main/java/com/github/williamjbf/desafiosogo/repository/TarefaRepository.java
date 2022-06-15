package com.github.williamjbf.desafiosogo.repository;

import com.github.williamjbf.desafiosogo.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
