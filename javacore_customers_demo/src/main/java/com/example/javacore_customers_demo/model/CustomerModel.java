package com.example.javacore_customers_demo.model;

import com.example.javacore_customers_demo.entity.Customer;

import java.util.List;

public interface CustomerModel {

    Customer save(Customer customer);

    List<Customer> findAll();

    Customer findByID(String id);

    Customer update(String id, Customer customer);

    boolean delete(String id);
}
