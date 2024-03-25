package com.example.demo.model;

import jakarta.persistence.*;



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


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }


        public User(){

        }
        public User(Long id, String username, String phone, String email, String password) {
            this.id = id;
            this.username = username;
            this.phone = phone;
            this.email = email;
            this.password = password;
        }
    }



