package com.betha.admin.repository;

import com.betha.admin.model.Balanco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalancoRepository extends JpaRepository<Balanco, Long> {

}
