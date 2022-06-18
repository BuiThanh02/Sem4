package com.example.springassignment.service;

import com.example.springassignment.entity.District;
import com.example.springassignment.entity.Street;
import com.example.springassignment.repository.DistrictRepository;
import com.example.springassignment.repository.StreetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class StreetService {
    @Autowired
    private StreetRepository streetRepository;
    private DistrictRepository districtRepository;

    public Iterable<Street> findAll(){
        return streetRepository.findAll();
    }

    public Street findByDistrict(UUID id){
        Optional<District> district = districtRepository.findById(id);
        District dis = district.get();
        return streetRepository.findStreetsByDistrict(dis);
    }

    public Iterable<Street> findByName(String name){
        return streetRepository.findStreetByName(name);
    }

    public Street save(Street street){
        return streetRepository.save(street);
    }
}
