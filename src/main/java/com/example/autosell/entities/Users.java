package com.example.autosell.entities;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class Users {

    @Column(name = "id_users")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    @Column(unique = true,length = 100)
    String email;

    @Column(length = 100)
    String password;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
