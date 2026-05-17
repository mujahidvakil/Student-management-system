package com.example.curd.service;
import com.example.curd.repository.TeacherRepository;

import org.springframework.stereotype.Service;

import com.example.curd.model.Teacher;

@Service
public class TeacherCurdService {

    private final TeacherRepository teacherRepository;

    public TeacherCurdService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }   

    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public Teacher updateTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public void deleteTeacher(int id) {
        teacherRepository.deleteById(id);
    }

    public Teacher getTeacher(int id) {
        return teacherRepository.findById(id).orElse(null);
    }

}
