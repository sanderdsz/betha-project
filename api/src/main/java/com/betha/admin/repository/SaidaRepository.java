package com.betha.admin.repository;

import com.betha.admin.model.Saida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaidaRepository extends JpaRepository<Saida, Long> {

    List<Saida> findAllByDescricaoContaining(String descricao);

    List<Saida> findByDescricao(String descricao);


}
