package com.example.springassignment.seeder;

import com.example.springassignment.entity.City;
import com.example.springassignment.entity.District;
import com.example.springassignment.entity.Street;
import com.example.springassignment.entity.enums.SimpleEnum;
import com.example.springassignment.repository.DistrictRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
public class DistrictSeeder {
    @Autowired
    DistrictRepository districtRepository;

    Faker faker = new Faker();
    public  static List<District> districts;
    public static final int NUMBER_OF_DISTRICT = 30;
    public static final int NUMBER_OF_STREET = 50;

    public void save(){
        for (int i = 0; i < NUMBER_OF_DISTRICT; i++){
            int randomCityNumber = faker.number().numberBetween(0, CitySeeder.cities.size() - 1);
            City city = CitySeeder.cities.get(randomCityNumber);

            District district = new District();
            district.setId(UUID.randomUUID());
            district.setName(faker.address().state());
            district.setCityId(city.getId());
            district.setCity(city);
            district.setCreatedAt(LocalDateTime.now());
            Set<Street> streets = new HashSet<>();

            for (int j = 0; j < NUMBER_OF_STREET; i++){
                Street street = new Street();
                street.setId(UUID.randomUUID());
                street.setName(faker.address().streetName());
                street.setDescription(faker.lorem().sentence());
                street.setStatus(SimpleEnum.ACTIVE);
                street.setDistrictId(district.getId());
                street.setDistrict(district);
                streets.add(street);
            }
            district.setStreets(streets);
            districts.add(district);
        }
        districtRepository.saveAll(districts);
    }
}
