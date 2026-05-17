package com.example.curd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.example.curd.service.TeacherCurdService;
import com.example.curd.model.Student;
import com.example.curd.model.Teacher;
import com.example.curd.service.GenericCurdServices;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.curd.DTO.StudentDTO;
import com.example.curd.model.DTOMapping;



@RestController
@RequestMapping("/generic")
public class GenericCurdController {

    @Autowired
    GenericCurdServices genericCurdServices;

    @Autowired
    TeacherCurdService teacherCurdService;

    @GetMapping("/find/{id}")
    public Student getStudent(@PathVariable int id) {
        return genericCurdServices.getStudent(id);
    }

    @GetMapping("/hello")
    public String getHello() {
        return "Hello, World!";
    }

    @PostMapping("/saveStudent")
    public StudentDTO saveStudent(@RequestBody Student std) {
        std = genericCurdServices.saveStudent(std);
        return DTOMapping.mapToDTO(std);
    }

    @PutMapping("/updateStudent")
    public StudentDTO updateStudent(@RequestBody Student std) {
        std = genericCurdServices.updateStudent(std);
        
        return DTOMapping.mapToDTO(std);
    }

    @DeleteMapping("/deleteStudent")
    public void deleteStudent(@RequestBody Student std) {
        genericCurdServices.deleteStudent(std.getId());
    }

    @GetMapping("/getAllStudents")
    public List<Student> getAllStudents() {
        return genericCurdServices.getAllStudents();
    }

    @GetMapping("/getStudentById/{id}")
    public Student getStudentById(@PathVariable int id) {
        return genericCurdServices.getStudent(id);
    }

    @GetMapping("/getTeacherById/{id}")
    public Teacher getTeacherById(@PathVariable int id) {
        return teacherCurdService.getTeacher(id);
    }

    @PostMapping("path")
    public Teacher savTeacher(@RequestBody Teacher teacher) {
        teacher = teacherCurdService.saveTeacher(teacher);
        return teacher;
    }
    

    
}
