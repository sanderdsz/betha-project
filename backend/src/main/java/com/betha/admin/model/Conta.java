package com.betha.admin.model;

import javax.persistence.*;

@Entity
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String nome;

    @ManyToOne
    @JoinColumn(name = "i_balanco")
    private Balanco balanco;

    @Column
    private TipoConta tipoConta;

    @Column
    private String descricao;

    public Conta(int id, String nome, Balanco balanco, TipoConta tipoConta, String descricao) {
        this.id = id;
        this.nome = nome;
        this.balanco = balanco;
        this.tipoConta = tipoConta;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Balanco getBalanco() {
        return balanco;
    }

    public void setBalanco(Balanco balanco) {
        this.balanco = balanco;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Conta{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", balanco=" + balanco +
                ", tipoConta=" + tipoConta +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
