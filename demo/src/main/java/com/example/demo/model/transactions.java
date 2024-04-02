package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity(name = "transaction")
public class transactions{

    @Column(name = "deposite")
    private Integer deposite;
    @Id
    private Long id;

    public transactions() {

    }

    public void setId(Long id) {
        this.id = id;
    }
    public transactions(Long id,Integer deposite ) {
        this.id = id;
        this.deposite = deposite;
    }
    public Long getId() {
        return id;
    }
}
