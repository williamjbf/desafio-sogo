package com.github.williamjbf.desafiosogo.service;

import com.github.williamjbf.desafiosogo.detail.UsuarioDetail;
import com.github.williamjbf.desafiosogo.model.Usuario;
import com.github.williamjbf.desafiosogo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Usuario> usuario = repository.findByLogin(username);
        if(!usuario.isPresent()){
            throw new UsernameNotFoundException("Usuario ["+ username+"[ nao encontrado");
        }
        return new UsuarioDetail(usuario);
    }
}
