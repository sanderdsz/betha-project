package com.betha.admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
public class Transacao {

    @Column
    private LocalDate data;

    @Column
    private Double valor;

    @Column
    private TipoTransacao tipoTransacao;

    @Column
    private String descricao;

    public Transacao() {}

    public Transacao(LocalDate data, Double valor, TipoTransacao tipoTransacao, String descricao) {
        this.data = data;
        this.valor = valor;
        this.tipoTransacao = tipoTransacao;
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(TipoTransacao tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
