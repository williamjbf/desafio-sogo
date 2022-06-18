package com.github.williamjbf.desafiosogo.repository;

import com.github.williamjbf.desafiosogo.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto,Long> {
}
