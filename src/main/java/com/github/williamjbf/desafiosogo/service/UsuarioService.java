package com.github.williamjbf.desafiosogo.service;

import com.github.williamjbf.desafiosogo.model.Projeto;
import com.github.williamjbf.desafiosogo.model.Usuario;
import com.github.williamjbf.desafiosogo.repository.ProjetoRepository;
import com.github.williamjbf.desafiosogo.repository.UsuarioRepository;
import com.github.williamjbf.desafiosogo.util.LogadoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final PasswordEncoder encoder;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ProjetoRepository projetoRepository;

    public UsuarioService(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public ResponseEntity<List<Usuario>> listar(){
        if (LogadoUtil.isAdmin()){
            List<Usuario> usuarios = usuarioRepository.findAll();
            return ResponseEntity.ok(usuarios);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    public Usuario exibirUsuarioAtual(){
        return usuarioRepository.findByLogin(LogadoUtil.usuarioLogado()).get();
    }

    public ResponseEntity<Usuario> cadastrar(Usuario usuario){
        Optional<Usuario> usuarioExiste = usuarioRepository.findByLogin(usuario.getLogin());
        if (usuarioExiste.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
        usuario.setSenha(encoder.encode(usuario.getSenha()));
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
