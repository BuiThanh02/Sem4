package com.example.springexam.service;

import com.example.springexam.entity.Employee;
import com.example.springexam.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Iterable<Employee> findAll(){
        return employeeRepository.findAll();
    }
    public Optional<Employee> findById(String proId){
        return employeeRepository.findById(proId);
    }

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public void deleteById(String proId){
        employeeRepository.deleteById(proId);
    }

}
