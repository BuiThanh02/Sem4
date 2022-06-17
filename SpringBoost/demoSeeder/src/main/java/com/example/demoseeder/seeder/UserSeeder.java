package com.example.demoseeder.seeder;

import com.example.demoseeder.entity.User;
import com.example.demoseeder.repository.UserRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class UserSeeder {
    @Autowired
    UserRepository userRepository;
    public static List<User> users;
    Faker faker = new Faker();
    public static final int NUMBER_OF_USER = 20;
    public void save(){
        for (int i = 0; i < NUMBER_OF_USER; i++){
            User user = new User();
            user.setId(UUID.randomUUID().toString());
            user.setName(String.valueOf(faker.harryPotter()));
            users.add(user);
        }
        userRepository.saveAll(users);
    }
}
