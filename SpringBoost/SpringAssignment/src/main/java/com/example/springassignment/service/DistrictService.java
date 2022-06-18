package com.example.springassignment.service;

import com.example.springassignment.entity.District;
import com.example.springassignment.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DistrictService {
    @Autowired
    private DistrictRepository districtRepository;

    public Iterable<District> findAll(){
        return districtRepository.findAll();
    }

    public Optional<District> findById(UUID id){
        return districtRepository.findById(id);
    }

}
