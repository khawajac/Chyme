package com.example.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name ="username", unique = true, nullable = false)
    private String username;

    @Column(name ="email", unique = true, nullable = false)
    private String email;

    @Column(name="password", nullable = false)
    private String password;


}
