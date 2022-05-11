package com.example.javacore_customers_demo.model;

import com.example.javacore_customers_demo.entity.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MySqlCustomerModelTest {
    CustomerModel model;

    @BeforeEach
    void setUp() {
        model = new MySqlCustomerModel();
    }

    @Test
    void save() {
        System.out.println(model.findAll().size());
        model.save(new Customer("7", "Bui Huu Thanh", "0979341091", "image", LocalDateTime.of(2002, 10, 28, 10, 10)));
        System.out.println(model.findAll().size());
    }

    @Test
    void findAll() {
        List<Customer> list = model.findAll();
        for (Customer cus : list){
            System.out.println(cus.toString());
        }
    }

    @Test
    void findByID() {
        Customer customer = model.findByID("1");
        assertEquals("thanh", customer.getName());
    }

    @Test
    void update() {
        Customer customer = model.findByID("1");
        customer.setName("HT");
        model.update("1", customer);
        Customer newUpdateCus = model.findByID("1");
        assertEquals("HT", newUpdateCus.getName());
    }

    @Test
    void delete() {
        model.delete("1");
        Customer customer = model.findByID("1");
        assertNull(customer);
    }
}