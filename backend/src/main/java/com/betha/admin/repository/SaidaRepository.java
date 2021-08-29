package com.betha.admin.repository;

import com.betha.admin.model.Saida;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SaidaRepository extends CrudRepository<Saida, Long> {

    @Override
    List<Saida> findAll();

    Saida findById(long id);

}
