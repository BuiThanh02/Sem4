package com.example.springassignment.api;

import com.example.springassignment.entity.District;
import com.example.springassignment.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "api/v1/districts")
@RestController
public class DistrictApi {
    @Autowired
    DistrictService districtService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<District>> findAll(){
        return ResponseEntity.ok(districtService.findAll());
    }
}
