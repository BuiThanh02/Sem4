package com.example.springbootassignment.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationSeeder implements CommandLineRunner {
    @Autowired
    CategorySeeder categorySeeder;
    @Autowired
    ProductSeeder productSeeder;
    @Autowired
    OrderSeeder orderSeeder;

    @Override
    public void run(String... args) throws Exception {
        categorySeeder.generate();
        productSeeder.generate();
        orderSeeder.generate();
    }
}
