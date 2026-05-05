package com.project.placement.service.impl;

import org.springframework.stereotype.Service;

import com.project.placement.entity.User;
import com.project.placement.repository.UserRepository;
import com.project.placement.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public User registerStudentUser(String email, String password) {
        User u = new User();
        u.setEmail(email);
        u.setPassword(password);
        u.setRole("STUDENT");
        return repo.save(u);
    }

    @Override
    public User saveUser(User u) {
        return repo.save(u);
    }

    @Override
    public User login(String email, String password) {
        return repo.findByEmailAndPassword(email, password);
    }

    @Override
    public boolean emailExists(String email) {
        return repo.findByEmail(email) != null;
    }
}
