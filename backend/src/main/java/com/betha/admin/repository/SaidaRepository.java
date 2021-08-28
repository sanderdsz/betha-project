package com.betha.admin.repository;

import com.betha.admin.model.Saida;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SaidaRepository extends CrudRepository<Saida, Long> {

    Saida findById(long id);

}
