package com.betha.admin.repository;

import com.betha.admin.model.Entrada;
import com.betha.admin.model.Saida;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EntradaRepository extends CrudRepository<Entrada, Long> {

    @Override
    List<Entrada> findAll();

    Entrada findById(long id);

    @Override
    Entrada save(Entrada entrada);

    @Override
    void delete(Entrada entrada);

}
