package com.github.williamjbf.desafiosogo.controller;

import com.github.williamjbf.desafiosogo.model.Usuario;
import com.github.williamjbf.desafiosogo.repository.UsuarioRepository;
import com.github.williamjbf.desafiosogo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listar(){
        return usuarioRepository.findAll();
    }

    @PostMapping("cadastrar")
    public HttpEntity<Usuario> cadastrar(@RequestBody @Valid Usuario usuario){
        return usuarioService.cadastrar(usuario);
    }

}
