package com.project.placement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.placement.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String name, String email);

    Student findByEmail(String email);
}
