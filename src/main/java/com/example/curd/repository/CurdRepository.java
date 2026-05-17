package com.example.curd.repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.curd.model.Student;

@Repository
public class CurdRepository {

    private final JdbcTemplate jdbcTemplate;

    private static final RowMapper<Student> studentRowMapper = (rs, rowNum) -> {
        Student student = new Student();
        student.setId(rs.getInt("id"));
        student.setName(rs.getString("name"));
        student.setAge(rs.getInt("age"));
        student.setCourse(rs.getString("course"));
        return student;
    };

    private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS students (" +
            "id SERIAL PRIMARY KEY, " +
            "name VARCHAR(255) NOT NULL, " +
            "age INTEGER, " +
            "course VARCHAR(255)" +
            ")";

    public CurdRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcTemplate.execute(CREATE_TABLE_SQL);
    }

    public Student save(Student student) {
        if (student.getId() != 0 && existsById(student.getId())) {
            return update(student);
        }

        String sql = "INSERT INTO students (name, age, course) VALUES (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, student.getName());
            ps.setInt(2, student.getAge());
            ps.setString(3, student.getCourse());
            return ps;
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            student.setId(keyHolder.getKey().intValue());
        }
        return student;
    }

    public Optional<Student> findById(int id) {
        String sql = "SELECT id, name, age, course FROM student WHERE id = ?";
        List<Student> results = jdbcTemplate.query(sql, studentRowMapper, id);
        return results.stream().findFirst();
    }

    public List<Student> findAll() {
        String sql = "SELECT id, name, age, course FROM students";
        return jdbcTemplate.query(sql, studentRowMapper);
    }

    public Student update(Student student) {
        String sql = "UPDATE students SET name = ?, age = ?, course = ? WHERE id = ?";
        int rowsUpdated = jdbcTemplate.update(sql,
                student.getName(),
                student.getAge(),
                student.getCourse(),
                student.getId());
        return rowsUpdated > 0 ? student : null;
    }

    public boolean deleteById(int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }

    public boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM students WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }
}
