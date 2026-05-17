package com.example.curd.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String subject;
    private int courseId;
    private String email;

    //setter getter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }    

    public void setSubject(String subject) {
        this.subject = subject;
    }    

    public int getCourseId() {
        return courseId;
    }    

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }       
}
