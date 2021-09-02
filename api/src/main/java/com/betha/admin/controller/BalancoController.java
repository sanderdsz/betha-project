package com.betha.admin.controller;

import com.betha.admin.model.Balanco;
import com.betha.admin.repository.BalancoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/balancos")
public class BalancoController {

    @Autowired
    private BalancoRepository balancoRepository;

    @GetMapping
    public List<Balanco> findAll() {
        return balancoRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Balanco save(@RequestBody Balanco balanco) {
        return balancoRepository.save(balanco);
    }

}
