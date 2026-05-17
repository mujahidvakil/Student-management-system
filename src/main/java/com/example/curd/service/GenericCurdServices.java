package com.example.curd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.curd.model.Student;
import com.example.curd.repository.StudentRepository;

@Service
public class GenericCurdServices {

    
    private final StudentRepository studentRepository;
    
    public GenericCurdServices(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student getStudent(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }
}

