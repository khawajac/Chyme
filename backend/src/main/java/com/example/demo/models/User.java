package com.example.demo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    @Column(name ="username", unique = true, nullable = false)
    private String username;

    @Setter
    @Column(name ="email", unique = true, nullable = false)
    private String email;

    @Setter
    @Column(name="password", nullable = false)
    private String password;


    public User(String username, String email, String password){
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User() {
    }

}