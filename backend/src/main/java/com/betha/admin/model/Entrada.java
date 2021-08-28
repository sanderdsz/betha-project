package com.betha.admin.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Entrada extends Transacao{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public Entrada(LocalDate data, Double valor, TipoTransacao tipoTransacao, String descricao, int id) {
        super(data, valor, tipoTransacao, descricao);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
