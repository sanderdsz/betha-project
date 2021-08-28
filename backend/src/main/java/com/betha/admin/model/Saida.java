package com.betha.admin.model;

import java.time.LocalDate;

public class Saida extends Transacao{

    private int id;

    public Saida(LocalDate data, Double valor, TipoTransacao tipoTransacao, String descricao, int id) {
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
