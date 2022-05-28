package com.example.wcdexam.model;

import com.example.wcdexam.entity.Phone;

import java.util.List;

public interface PhoneModel {
    Phone save(Phone phone);
    List<Phone> findAll();
    Phone findById(int id);
    Phone update(int id,Phone updatePhone);
    boolean delete(int id);
}
