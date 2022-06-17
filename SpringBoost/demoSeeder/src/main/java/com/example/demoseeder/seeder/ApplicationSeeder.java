package com.example.demoseeder.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationSeeder implements CommandLineRunner {
    @Autowired
    OrderSeeder orderSeeder;
    @Autowired
    ProductSeeder productSeeder;
    @Autowired
    UserSeeder userSeeder;
    @Override
    public void run(String... args) throws Exception {

        productSeeder.save();
        userSeeder.save();
        orderSeeder.save();
    }
}
