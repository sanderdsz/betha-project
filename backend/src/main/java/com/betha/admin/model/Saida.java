package com.betha.admin.model;

import com.betha.admin.repository.SaidaRepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Saida extends Transacao{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    protected Saida() {}

    public Saida(LocalDate data, Double valor, TipoTransacao tipoTransacao, String descricao) {
        super(data, valor, tipoTransacao, descricao);
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Saida{" +
                "id=" + id +
                '}';
    }
}
