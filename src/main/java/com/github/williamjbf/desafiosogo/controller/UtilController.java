package com.github.williamjbf.desafiosogo.controller;

import com.github.williamjbf.desafiosogo.model.Status;
import com.github.williamjbf.desafiosogo.repository.StatusRepository;
import com.github.williamjbf.desafiosogo.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("popular")
public class UtilController {

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    UtilService utilService;

    @PostMapping
    public void popular(){
        utilService.popular();
    }

}
