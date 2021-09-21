package com.betha.admin.controller;

import com.betha.admin.model.Saida;
import com.betha.admin.repository.SaidaRepository;
import com.betha.admin.service.SaidaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/saidas")
public class SaidaController {

    @Autowired
    private SaidaRepository saidaRepository;

    @Autowired
    private SaidaService service;

    @GetMapping("/{id}")
    public ResponseEntity<Saida> findById(@PathVariable long id) {
        Optional<Saida> entity = saidaRepository.findById(id);
        if (entity.isPresent()) {
            return ResponseEntity.ok(entity.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping
    public List<Saida> findAll() {
        return saidaRepository.findAll();
    }

    @GetMapping("/descricao")
    public List<Saida> findByDescricao(String descricao) {
        return saidaRepository.findByDescricao(descricao);
    }

    @GetMapping("/descricao-contains")
    public List<Saida> findAllByDescricaoContaining(String descricao) {
        return saidaRepository.findAllByDescricaoContaining(descricao);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Saida save(@RequestBody Saida saida) {
        return service.salvar(saida);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Saida> update(
            @PathVariable long id,
            @RequestBody Saida saida
    ) {
        Optional<Saida> entity = saidaRepository.findById(id);
        if (entity != null) {
            // Copia os valores que estao chegando para um novo objeto
            BeanUtils.copyProperties(saida, entity, "id");
            Saida savedEntity = service.salvar(saida);
            return ResponseEntity.ok(entity.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Saida> delete(@PathVariable long id) {
        try {
            Optional<Saida> entity = saidaRepository.findById(id);
            if (entity != null) {
                saidaRepository.delete(entity.get());
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
