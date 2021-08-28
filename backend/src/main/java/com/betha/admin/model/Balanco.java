package com.betha.admin.model;

import java.time.LocalDate;

public class Balanco {

    private int id;
    private LocalDate data;
    private Double balanco;
    private Entrada entrada;
    private Saida saida;

    public Balanco(int id, LocalDate data, Double balanco, Entrada entrada, Saida saida) {
        this.id = id;
        this.data = data;
        this.balanco = balanco;
        this.entrada = entrada;
        this.saida = saida;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Double getBalanco() {
        return balanco;
    }

    public void setBalanco(Double balanco) {
        this.balanco = balanco;
    }

    public Entrada getEntrada() {
        return entrada;
    }

    public void setEntrada(Entrada entrada) {
        this.entrada = entrada;
    }

    public Saida getSaida() {
        return saida;
    }

    public void setSaida(Saida saida) {
        this.saida = saida;
    }

    @Override
    public String toString() {
        return "Balanco{" +
                "id=" + id +
                ", data=" + data +
                ", balanco=" + balanco +
                ", entrada=" + entrada +
                ", saida=" + saida +
                '}';
    }
}
