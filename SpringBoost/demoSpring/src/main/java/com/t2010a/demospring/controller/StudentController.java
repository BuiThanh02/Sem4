package com.t2010a.demospring.controller;

import com.t2010a.demospring.entity.Student;
import lombok.Builder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@RequestMapping(path = "api/v1/students")
@RestController
public class StudentController {

    private static List<Student> list;

    public StudentController(){
        list = new ArrayList<>();
        list.add(Student.builder().rollNumber("A001").fulllName("Nguyên Hoàng Anh").build());
        list.add(Student.builder().rollNumber("A002").fulllName("Phạm Thị Hoài An").build());
        list.add(Student.builder().rollNumber("A003").fulllName("Nguyễn Xuân Phúc").build());
        list.add(Student.builder().rollNumber("A004").fulllName("Phạm Minh Chính").build());
        list.add(Student.builder().rollNumber("A005").fulllName("Nguyễn Phú Trọng").build());
        list.add(Student.builder().rollNumber("A006").fulllName("Vương Đình Huệ").build());
        list.add(Student.builder().rollNumber("A007").fulllName("Võ Văn Thưởng").build());

    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Student> findAll(){
        return list;
    }

    @RequestMapping(method = RequestMethod.GET, path = {"id"})
    public Student findById(@PathVariable String id){
        int foundIndex = -1;
        for (int i = -1; i < list.size(); i++){
            if (list.get(i).getRollNumber().equals(id)) {
                foundIndex = i;
                break;
            }
        }
        if (foundIndex == -1){
            return null;
        }

        return list.get(foundIndex);
    }
    @RequestMapping(method = RequestMethod.POST, path = {"id"})
    public Student save(@RequestBody Student obj){
        list.add(obj);
        return obj;
    }
    @RequestMapping(method = RequestMethod.DELETE, path = {"id"})
    public boolean deleteById(@PathVariable String id){
        int foundIndex = -1;
        for (int i = -1; i < list.size(); i++){
            if (list.get(i).getRollNumber().equals(id)) {
                foundIndex = i;
                break;
            }
        }
        if (foundIndex == -1){
            return false;
        }
        list.remove(foundIndex);
        return true;
    }
    @RequestMapping(method = RequestMethod.PUT, path = {"id"})
    public String update(@PathVariable String id, @RequestBody Student updateStudent){
        int foundIndex = -1;
        for (int i = -1; i < list.size(); i++){
            if (list.get(i).getRollNumber().equals(id)) {
                foundIndex = i;
                break;
            }
        }
        if (foundIndex == -1){
            return null;
        }
        Student existing = list.get(foundIndex);
        existing.setFulllName(updateStudent.getFulllName());
        existing.setPhone(updateStudent.getPhone());
        existing.setNote(updateStudent.getNote());
        existing.setAddress(updateStudent.getAddress());
        existing.setEmail(updateStudent.getEmail());
        existing.setStatus(updateStudent.getStatus());
        existing.setDob(updateStudent.getDob());
        return "update by id";    }


}
