package com.betha.admin.service;

import com.betha.admin.model.Balanco;
import com.betha.admin.repository.BalancoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BalancoService {

    @Autowired
    private BalancoRepository repository;

    public Balanco salvar(Balanco balanco) {
        return repository.save(balanco);
    }

}
