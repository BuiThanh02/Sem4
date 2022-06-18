package com.example.springassignment.api;

import com.example.springassignment.entity.District;
import com.example.springassignment.entity.Street;
import com.example.springassignment.service.DistrictService;
import com.example.springassignment.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/street")
public class StreetApi {
    @Autowired
    StreetService streetService;
    DistrictService districtService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Street>> findAll(){
        return ResponseEntity.ok(streetService.findAll());
    }

    @RequestMapping(method = RequestMethod.GET, path = {"name"})
    public ResponseEntity<Iterable<Street>> findByName(@PathVariable String name){
        return ResponseEntity.ok(streetService.findByName(name));
    }

    @RequestMapping(method = RequestMethod.GET, path = {"id"})
    public ResponseEntity<Street> findByDistrict(@PathVariable UUID id){
        Optional<District> district = districtService.findById(id);
        UUID dis = district.get().getId();
        return ResponseEntity.ok(streetService.findByDistrict(dis));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Street> save(@RequestBody Street street){
        return ResponseEntity.ok(streetService.save(street));
    }
}
