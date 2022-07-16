package com.example.ead.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationSeeder implements CommandLineRunner {
    @Autowired
    ProductSeeder productSeeder;

    @Autowired
    SaleSeeder saleSeeder;

    @Override
    public void run(String... args) {

        productSeeder.generate();
        saleSeeder.generate();
    }
}
