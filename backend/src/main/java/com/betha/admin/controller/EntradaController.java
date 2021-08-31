package com.betha.admin.controller;

import com.betha.admin.model.Entrada;
import com.betha.admin.repository.EntradaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entradas")
public class EntradaController {

    @Autowired
    private EntradaRepository entradaRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Entrada> findById(@PathVariable long id) {
        Entrada entity = entradaRepository.findById(id);
        if (entity != null) {
            return ResponseEntity.ok(entity);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping
    public List<Entrada> findAll() {
        return entradaRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entrada save(@RequestBody Entrada entrada) {
        return entradaRepository.save(entrada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entrada> update(
            @PathVariable long id,
            @RequestBody Entrada entrada
    ) {
        Entrada entity = entradaRepository.findById(id);
        if (entity != null) {
            BeanUtils.copyProperties(entrada, entity, "id");
            entradaRepository.save(entrada);
            return ResponseEntity.ok(entrada);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Entrada> delete(@PathVariable long id) {
        try {
            Entrada entity = entradaRepository.findById(id);
            if (entity != null) {
                entradaRepository.delete(entity);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
