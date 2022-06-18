package com.example.springassignment.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class AplicationSeeder implements CommandLineRunner {
    @Autowired
    CitySeeder citySeeder;
    @Autowired
    DistrictSeeder districtSeeder;


    @Override
    public void run(String... args) throws Exception {
        citySeeder.save();
        districtSeeder.save();
    }
}
