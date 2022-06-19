package com.github.williamjbf.desafiosogo.service;

import com.github.williamjbf.desafiosogo.model.Projeto;
import com.github.williamjbf.desafiosogo.model.Usuario;
import com.github.williamjbf.desafiosogo.repository.ProjetoRepository;
import com.github.williamjbf.desafiosogo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.soap.Addressing;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ProjetoRepository projetoRepository;

    public Usuario cadastrar(Usuario usuario){
        usuario = usuarioRepository.save(usuario);
        Projeto projeto = new Projeto();
        projeto.setTitulo("padrao");
        projeto = projetoRepository.save(projeto);
        projetoRepository.adicionarIdUsuario(projeto.getId(), usuario.getId());
        return usuario;
    }

}
