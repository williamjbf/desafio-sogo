package com.github.williamjbf.desafiosogo.repository;

import com.github.williamjbf.desafiosogo.model.Projeto;
import com.github.williamjbf.desafiosogo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto,Long> {

    @Transactional
    @Modifying
    @Query(value = "update projeto set usuario_id = :usuario_id where id = :projeto_id",nativeQuery = true)
    void adicionarIdUsuario(long projeto_id ,long usuario_id);

    @Query(value = "select * from projeto where usuario_id = :id",nativeQuery = true)
    List<Projeto> findAllByUsuarioId(long id);

    @Query(value = "select u.login from projeto p inner join usuario u on u.id = p.usuario_id where p.id =:id",nativeQuery = true)
    String usuarioResponsavel(long id);
}
