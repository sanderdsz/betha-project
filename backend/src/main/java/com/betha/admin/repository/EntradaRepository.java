package com.betha.admin.repository;

import com.betha.admin.model.Entrada;
import org.springframework.data.repository.CrudRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class EntradaRepository implements IRepository<Entrada> {


    @Override
    public List<Entrada> findAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Entrada findById(Integer id) throws SQLException, ClassNotFoundException {
        return null;
    }
}
