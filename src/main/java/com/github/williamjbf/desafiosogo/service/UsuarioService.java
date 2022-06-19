package com.github.williamjbf.desafiosogo.service;

import com.github.williamjbf.desafiosogo.model.Projeto;
import com.github.williamjbf.desafiosogo.model.Usuario;
import com.github.williamjbf.desafiosogo.repository.ProjetoRepository;
import com.github.williamjbf.desafiosogo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.ws.soap.Addressing;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ProjetoRepository projetoRepository;

    public ResponseEntity<Usuario> cadastrar(Usuario usuario){
        Usuario usuarioExiste = usuarioRepository.findUsuarioByLogin(usuario.getLogin());
        if (usuarioExiste != null){
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
        usuario = usuarioRepository.save(usuario);
        adicionarProjetoPadrao(usuario.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    private void adicionarProjetoPadrao(long usuario_id){
        Projeto projeto = new Projeto();
        projeto.setTitulo("padrao");
        projeto = projetoRepository.save(projeto);
        projetoRepository.adicionarIdUsuario(projeto.getId(), usuario_id);
    }

}
