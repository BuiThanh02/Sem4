package com.t2010a.demospring.controller;

import com.t2010a.demospring.entity.Student;
import com.t2010a.demospring.repository.StudentRepository;
import com.t2010a.demospring.service.StudentService;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequestMapping(path = "api/v1/students")
@RestController
public class StudentController {
    @Autowired
    StudentService studentService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Student>> findAll(){
        return ResponseEntity.ok(studentService.findAll());
    }

    @RequestMapping(method = RequestMethod.GET, path = {"id"})
    public ResponseEntity<?> findById(@PathVariable String id){
        Optional<Student> optionalStudent = studentService.findById(id);
        if (!optionalStudent.isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalStudent.get());
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Student> save(@RequestBody Student student){
        return ResponseEntity.ok(studentService.save(student));
    }
    @RequestMapping(method = RequestMethod.DELETE, path = {"id"})
    public ResponseEntity<?> deleteById(@PathVariable String id){
        if (!studentService.findById(id).isPresent()){
            ResponseEntity.badRequest().build();
        }
        studentService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @RequestMapping(method = RequestMethod.PUT, path = {"id"})
    public ResponseEntity<Student> update(@PathVariable String id, @RequestBody Student updateStudent) {
        Optional<Student> optionalStudent = studentService.findById(id);
        if (!optionalStudent.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        Student existStudent = optionalStudent.get();

        existStudent.setFullName(updateStudent.getFullName());
        existStudent.setStatus(updateStudent.getStatus());
        existStudent.setNote(updateStudent.getNote());
        existStudent.setDob(updateStudent.getDob());
        existStudent.setEmail(updateStudent.getEmail());
        existStudent.setPhone(updateStudent.getPhone());
        existStudent.setUpdatedAt(updateStudent.getUpdatedAt());
        existStudent.setCreatedAt(updateStudent.getCreatedAt());
        existStudent.setAddress(updateStudent.getAddress());

        return ResponseEntity.ok(studentService.save(existStudent));
    }


}
