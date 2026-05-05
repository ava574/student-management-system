package com.project.placement.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.placement.entity.Student;
import com.project.placement.repository.StudentRepository;
import com.project.placement.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repo;

    public StudentServiceImpl(StudentRepository repo) {
        this.repo = repo;
    }

    @Override
    public Student saveStudent(Student s) {
        return repo.save(s);
    }

    @Override
    public Student getStudentById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Student> getAllStudents(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return repo.findAll();
        }
        return repo.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(keyword, keyword);
    }

    @Override
    public void deleteStudent(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Student getByEmail(String email) {
        return repo.findByEmail(email);
    }
}
