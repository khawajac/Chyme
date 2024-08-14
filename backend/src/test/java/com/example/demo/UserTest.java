package com.example.demo;

import com.example.demo.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserTest {

    User user;

    @BeforeEach
    public void setUp(){
        user = new User("ornerykiwi", "kiwi@gmail.com", "ilovekiwis");
    }

    @Test
    public void testUserCreated(){
       String expected = "ornerykiwi";
       String result = user.getUsername();
       assertThat(expected).isEqualTo(result);
    }
}
