    package com.example.demo.model;
    import jakarta.persistence.*;
    import jakarta.validation.constraints.Min;
    import jakarta.validation.constraints.NotNull;
    import jakarta.validation.constraints.Size;
    import jakarta.validation.constraints.Email;
    import org.springframework.data.jpa.repository.JpaRepository;

    import java.time.LocalDate;


    @Entity(name = "users")
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;


        @Column(name = "dob")
        private LocalDate dob;


        @Size(min = 2, max = 30)
        @Column(name = "username")
        private String username;
        @Size(min = 2, max = 30)
        @Column(name = "name")
        private String name;
        @Size(min = 2, max = 30)
        @Column(name = "surname")
        private String surname;
        @Size(min = 2, max = 30)
        @Column(name = "placeholder")
        private String placeholder;

        @Column(name = "gender")
        private String gender;


        @Column(name = "phone")
        private String phone;


        @Column(name = "email")
        private String email;


        @Column(name = "password")
        private String password;

        @Column(name = "balance")
        private Integer balance;

        public User() {
            // Конструктор по умолчанию
        }

        public String getUsername() {
            return this.username; // Исправлено на правильный геттер
        }

        public void setUsername(String username) {
            this.username = username;
        }

        // Геттеры и сеттеры прочих полей
        public LocalDate getDob() {
            return dob;
        }

        public void setDob(LocalDate dob) {
            this.dob = dob;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public String getPlaceholder() {
            return placeholder;
        }

        public void setPlaceholder(String placeholder) {
            this.placeholder = placeholder;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }


        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Integer getBalance() {
            return balance;
        }


        public void setBalance(Integer balance) {
            this.balance = balance;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        // Конструктор с параметрами
        public User(Long id, String username, String email, String password, String phone, Integer balance, LocalDate dob, String gender,String name,String surname,String placeholder) {
            this.id = id;
            this.username = username;
            this.email = email;
            this.password = password;
            this.phone = phone;
            this.dob = dob;
            this.balance = balance;
            this.gender = gender;
            this.name = name;
            this.surname = surname;
            this.placeholder = placeholder;
            this.profileCompleted = false;

        }
        private Boolean profileCompleted;

        // Геттеры и сеттеры для profileCompleted

        public boolean isProfileCompleted() {
            // Проверяем, равно ли profileCompleted null
            if (this.profileCompleted == null) {
                // Если profileCompleted равно null, возвращаем false
                return false;
            }
            // В противном случае, возвращаем значение profileCompleted
            return this.profileCompleted.booleanValue();
        }

        public void setProfileCompleted(boolean profileCompleted) {
            this.profileCompleted = profileCompleted;
        }


        public interface AccountRepository extends JpaRepository<User, Long> {
        }
    }
