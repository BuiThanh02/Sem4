package com.example.springexam.api;

import com.example.springexam.entity.Employee;
import com.example.springexam.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "api/v1/employees")
@RestController
@CrossOrigin("*")
public class EmployeeApi {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Employee>> findAll(){
        return ResponseEntity.ok(employeeService.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Employee> create(@RequestBody Employee employee){
        if (!employeeService.findById(employee.getId()).isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(employeeService.save(employee));
    }

}
