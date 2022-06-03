package com.t2010a.demospring.service;

import com.t2010a.demospring.entity.Student;
import com.t2010a.demospring.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Iterable<Student> findAll(){
        return studentRepository.findAll();
    }

    public Optional<Student> findById(String rollNumber){
        return studentRepository.findById(rollNumber);
    }

    public Student save(Student student){
        return studentRepository.save(student);
    }

    public void deleteById(String rollNumber){
        studentRepository.deleteById(rollNumber);
    }
}
