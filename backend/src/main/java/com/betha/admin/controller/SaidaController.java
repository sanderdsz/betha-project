package com.betha.admin.controller;

import com.betha.admin.model.Saida;
import com.betha.admin.repository.SaidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/saida")
public class SaidaController {

    @Autowired
    private SaidaRepository saidaRepository;

    @GetMapping("/{id}")
    public Saida findById(long id) {
        return saidaRepository.findById(id);
    }

    @GetMapping
    public List<Saida> findAll() {
        return saidaRepository.findAll();
    }

}
