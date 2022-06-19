package com.github.williamjbf.desafiosogo.repository;

import com.github.williamjbf.desafiosogo.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto,Long> {

    @Transactional
    @Modifying
    @Query(value = "update projeto set usuario_id = :usuario_id where id = :projeto_id",nativeQuery = true)
    void adicionarIdUsuario(long projeto_id ,long usuario_id);

}
