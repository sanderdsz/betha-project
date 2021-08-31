package com.betha.admin.repository;

import com.betha.admin.model.Balanco;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BalancoRepository extends CrudRepository<Balanco, Long> {

    @Override
    List<Balanco> findAll();

    Balanco findById(long id);

    @Override
    Balanco save(Balanco balanco);

    @Override
    void delete(Balanco balanco);
}
