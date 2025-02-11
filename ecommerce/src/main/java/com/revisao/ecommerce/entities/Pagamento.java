package com.revisao.ecommerce.entities;


import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "tb_pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant momento;

    @OneToOne
    @MapsId
    private Pedido pedido;

    public Pagamento(){
    }

    public Pagamento(Long id, Instant momento) {
        this.id = id;
        this.momento = momento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMomento() {
        return momento;
    }

    public void setMomento(Instant momento) {
        this.momento = momento;
    }
}
