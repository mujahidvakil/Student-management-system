package com.example.curd.model;

import com.example.curd.DTO.StudentDTO;

public class DTOMapping {

    public static StudentDTO mapToDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setRollNo(student.getRollNo());
        studentDTO.setName(student.getName());
        studentDTO.setAge(student.getAge());
        studentDTO.setCourse(student.getCourse());
        return studentDTO;
    }
    
}
