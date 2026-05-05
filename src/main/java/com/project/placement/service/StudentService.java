package com.project.placement.service;

import java.util.List;

import com.project.placement.entity.Student;

public interface StudentService {

    Student saveStudent(Student s);

    Student getStudentById(Long id);

    List<Student> getAllStudents(String keyword);

    void deleteStudent(Long id);

    Student getByEmail(String email);
}
