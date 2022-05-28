package com.example.wcdexam.model;


import com.example.wcdexam.entity.Brand;

import java.util.List;

public interface BrandModel {
    Brand save(Brand brand);
    List<Brand> findAll();
    Brand findById(int id);
    Brand update(int id,Brand updateBrand);
    boolean delete(int id);
}
