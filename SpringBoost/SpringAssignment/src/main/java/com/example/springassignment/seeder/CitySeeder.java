package com.example.springassignment.seeder;

import com.example.springassignment.entity.City;
import com.example.springassignment.repository.CityRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class CitySeeder {
    @Autowired
    CityRepository cityRepository;
    Faker faker = new Faker();
    public  static List<City> cities;

    public static final int NUMBER_OF_CITY = 10;

    public void save(){
        for (int i = 0; i < NUMBER_OF_CITY; i++){
            City city = new City();
            city.setId(UUID.randomUUID());
            city.setName(faker.address().city());

            cities.add(city);
        }
        cityRepository.saveAll(cities);
    }
}
