package com.example.productmanagementdemo.model;

import com.example.productmanagementdemo.entity.Car;

import java.util.List;

public interface CarModel {
    Car save(Car car);

    List<Car> findAll();

    Car findByID(String id);

    Car update(String id, Car car);

    boolean delete(String id);
}
