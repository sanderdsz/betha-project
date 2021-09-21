package com.betha.admin.controller;

import com.betha.admin.model.Balanco;
import com.betha.admin.model.Entrada;
import com.betha.admin.repository.BalancoRepository;
import com.betha.admin.service.BalancoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/balancos")
public class BalancoController {

    @Autowired
    private BalancoRepository balancoRepository;

    @Autowired
    private BalancoService balancoService;

    @GetMapping
    public List<Balanco> findAll() {
        return balancoRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Balanco save(@RequestBody Balanco balanco) {
        return balancoRepository.save(balanco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Balanco> update(
            @PathVariable Long id,
            @RequestBody Balanco balanco
    ) {
        Optional<Balanco> entity = balancoRepository.findById(id);
        if (entity.isPresent()) {
            BeanUtils.copyProperties(balanco, entity.get(), "id");
            balancoRepository.save(entity.get());
            return ResponseEntity.ok(entity.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Balanco> parcialUpdate(
            @PathVariable Long id,
            @RequestBody Map<String, Object> campos
    ) {
        Optional<Balanco> balancoAtual = balancoRepository.findById(id);
        if (balancoAtual.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        merge(campos, balancoAtual.get());
        return update(id, balancoAtual.get());
    }

    private void merge(Map<String, Object> dadosOrigem, Balanco balancoDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Balanco balancoOrigem = objectMapper.convertValue(dadosOrigem, Balanco.class);

        dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Balanco.class, nomePropriedade);
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, balancoOrigem);
            System.out.println(nomePropriedade + " = " + valorPropriedade + " = " + novoValor);
            ReflectionUtils.setField(field, balancoDestino, novoValor);
        });
    }
}
