package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountHolderName;
    private double balance;

    // Геттер для получения баланса
    public double getBalance() {
        return balance;
    }

    // Сеттер для установки баланса
    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Другие геттеры и сеттеры, конструкторы и методы, если необходимо
}

