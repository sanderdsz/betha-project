package com.betha.admin.service;

import com.betha.admin.model.Saida;
import com.betha.admin.repository.SaidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaidaService {

    @Autowired
    private SaidaRepository repository;

    public Saida salvar(Saida entity) {
        return repository.save(entity);
    }

    public List<Saida> findByDescricao(String descricao) {
        return repository.findByDescricao(descricao);
    }

}
