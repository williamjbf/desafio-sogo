package com.github.williamjbf.desafiosogo.controller;

import com.github.williamjbf.desafiosogo.model.Status;
import com.github.williamjbf.desafiosogo.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("popular")
public class UtilController {

    @Autowired
    StatusRepository statusRepository;

    @PostMapping
    public void popular(){
        Status pendente = new Status();
        pendente.setDescricao("PENDENTE");
        Status concluido = new Status();
        pendente.setDescricao("CONCLUIDO");
        statusRepository.save(pendente);
        statusRepository.save(concluido);
    }

}
