package com.betha.admin.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Entrada extends Transacao{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    protected Entrada() {}

    public Entrada(LocalDate data, Double valor, TipoTransacao tipoTransacao, String descricao, Long id) {
        super(data, valor, tipoTransacao, descricao);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
