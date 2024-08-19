package com.example.demo.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {

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

}