package com.example.demo.components;


import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run (ApplicationArguments args) throws Exception {

        //Creating admin user for the database

        User ornerykiwi = User.builder()
                .username("ornerykiwi")
                .email("ornerykiwi@gmail.com")
                .password(passwordEncoder.encode("ilovekiwis"))
                .role(Role.USER)
                .build();
        userRepository.save(ornerykiwi);

        User cerealbagel = User.builder()
                .username("cerealbagel")
                .email("bagel@outlook.com")
                .password(passwordEncoder.encode("ilovebagels"))
                .role(Role.USER)
                .build();
        userRepository.save(cerealbagel);

    }


}
