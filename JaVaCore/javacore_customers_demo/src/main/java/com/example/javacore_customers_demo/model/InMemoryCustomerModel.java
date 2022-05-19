package com.example.javacore_customers_demo.model;

import com.example.javacore_customers_demo.entity.Customer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InMemoryCustomerModel implements CustomerModel {

    private static List<Customer> customers;

    public InMemoryCustomerModel() {
        customers = new ArrayList<>();

        customers.add(
                new Customer(
                        "1",
                        "thanh",
                        "0979341091",
                        "image",
                        LocalDateTime.of(2002, 10, 28, 0, 0)
                )
        );
        customers.add(
                new Customer(
                        "2",
                        "tung",
                        "0979341091",
                        "image",
                        LocalDateTime.of(2002, 10, 28, 0, 0)
                )
        );
        customers.add(
                new Customer(
                        "3",
                        "trung",
                        "0979341091",
                        "image",
                        LocalDateTime.of(2002, 10, 28, 0, 0)
                )
        );
        customers.add(
                new Customer(
                        "4",
                        "long",
                        "0979341091",
                        "image",
                        LocalDateTime.of(2002, 10, 28, 0, 0)
                )
        );
        customers.add(
                new Customer(
                        "5",
                        "nguyen",
                        "0979341091",
                        "image",
                        LocalDateTime.of(2002, 10, 28, 0, 0)
                )
        );
    }


    @Override
    public Customer save(Customer customer) {
        customers.add(customer);
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        return customers;
    }

    @Override
    public Customer findByID(String id) {
        Customer foundCustomer = null;
        for (Customer cus :
             customers) {
            if (cus.getID().equals(id)){
                foundCustomer = cus;
                break;
            }
        }
        return foundCustomer;
    }

    @Override
    public Customer update(String id, Customer customer) {
        Customer existingStudent = findByID(id);
        if (existingStudent == null){
            return null;
        }
        existingStudent.setName(customer.getName());
        existingStudent.setImage(customer.getImage());
        existingStudent.setPhone(customer.getPhone());
        existingStudent.setDOB(customer.getDOB());
        existingStudent.setUpdatedAt(LocalDateTime.now());
        existingStudent.setStatus(customer.getStatus());

        return existingStudent;
    }

    @Override
    public boolean delete(String id) {
        int foundIndex = -1;
        for (int i = 0; i < customers.size(); i++){
            if (customers.get(i).getID().equals(id)){
                foundIndex = i;
            }
        }
        if (foundIndex != -1){
            customers.remove(foundIndex);
            return true;
        }
        return false;
    }
}
