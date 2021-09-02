package com.betha.admin.repository;

import com.betha.admin.model.Conta;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContaRepository extends CrudRepository<Conta, Long> {

    @Override
    List<Conta> findAll();

    Conta findById(long id);

    @Override
    Conta save(Conta conta);

    @Override
    void delete(Conta conta);

}
