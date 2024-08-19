package com.example.demo.components;


import com.example.demo.models.User;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    UserService userService;

    @Override
    public void run (ApplicationArguments args) throws Exception {

        //Creating some users for the database, they should be able to create a Room with each other and send messages to each other

//        User kiwi = new User("ornerykiwi", "kiwi@gmail.com", "ilovekiwis");
//        userService.saveUser(kiwi);
//        User bagel = new User ("cerealbagel", "bagel@gmail.com", "ilovebagels");
//        userService.saveUser(bagel);
    }


}
