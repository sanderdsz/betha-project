package com.betha.admin.controller;

import com.betha.admin.model.Saida;
import com.betha.admin.repository.SaidaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/saidas")
public class SaidaController {

    @Autowired
    private SaidaRepository saidaRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Saida> findById(@PathVariable long id) {
        Saida entity = saidaRepository.findById(id);
        if (entity != null) {
            return ResponseEntity.ok(entity);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping
    public List<Saida> findAll() {
        return saidaRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Saida save(@RequestBody Saida saida) {
        return saidaRepository.save(saida);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Saida> update(
            @PathVariable long id,
            @RequestBody Saida saida
    ) {
        Saida entity = saidaRepository.findById(id);
        if (entity != null) {
            // Copia os valores que estao chegando para um novo objeto
            BeanUtils.copyProperties(saida, entity, "id");
            entity = saidaRepository.save(saida);
            return ResponseEntity.ok(entity);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Saida> delete(@PathVariable long id) {
        try {
            Saida entity = saidaRepository.findById(id);
            if (entity != null) {
                saidaRepository.delete(entity);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
