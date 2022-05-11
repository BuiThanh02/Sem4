package com.example.javacore_customers_demo.model;

import com.example.javacore_customers_demo.entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryCustomerModelTest {

    InMemoryCustomerModel model;

    @BeforeEach
    void setUp() {
        model = new InMemoryCustomerModel();
    }

    @Test
    void save() {
        System.out.println(model.findAll().size());
        Customer customer = new Customer(
                "6",
                "thanh",
                "0979341091",
                "image",
                LocalDateTime.of(2002, 10, 28, 0, 0)
        );
        model.save(customer);
        System.out.println(model.findAll().size());
    }

    @Test
    void findAll() {
        System.out.println(model.findAll().size());
    }

    @Test
    void findByID() {
        Customer customer = model.findByID("1");
        System.out.println(customer.toString());
    }

    @Test
    void update() {
        Customer customer = model.findByID("1");
        customer.setName("Bui Thanh");
        model.update("1", customer);
        for (Customer cus: model.findAll()) {
            System.out.println(cus.toString());
        }
    }

    @Test
    void delete() {
        model.delete("1");
        for (Customer cus: model.findAll()) {
            System.out.println(cus.toString());
        }
    }
}