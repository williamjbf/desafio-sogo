package com.github.williamjbf.desafiosogo.repository;

import com.github.williamjbf.desafiosogo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query
    Usuario findUsuarioByLogin(String login);

}
