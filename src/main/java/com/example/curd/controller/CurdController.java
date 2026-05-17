package com.example.curd.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.curd.model.Student;

@RestController
@RequestMapping("/api")
public class CurdController {

    private final Map<Integer, Student> studentDb = new ConcurrentHashMap<>();

    @GetMapping("/hello")
    public String getHello() {
        return "Hello, World!";
    }

    @PostMapping("/students")
    public Student saveStudent(@RequestBody Student student) {
        studentDb.put(student.getId(), student);
        return student;
    }

    @PostMapping("/students/bulk")
    public List<Student> saveStudents(@RequestBody List<Student> students) {
        for (Student student : students) {
            studentDb.put(student.getId(), student);
        }
        return students;
    }

    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable int id) {
        return studentDb.get(id);
    }

    @PutMapping("/students/update")
    public Student updateStudent(@RequestBody Student student) {
        studentDb.put(student.getId(), student);
        System.out.println("Student updated: " + student.getName());
        return student;
    }

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentDb.values().stream().collect(Collectors.toList());
    }
}
