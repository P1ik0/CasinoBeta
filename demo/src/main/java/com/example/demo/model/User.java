package com.example.demo.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity (name = "users")
    public class User{
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        @Column(name = "username")
        private String username;
        @Column(name = "phone")
        private String phone;
        @Column(name = "email")
        private String email;
        @Column(name = "password")
        private String password;
        // Конструкторы



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhone(String phone) {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public User(){

    }

    public User(Long userId, String username, String email, String password, LocalDateTime createdAt) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;

 ;
    }


}

