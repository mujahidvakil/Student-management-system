package com.example.curd.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.curd.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

}
