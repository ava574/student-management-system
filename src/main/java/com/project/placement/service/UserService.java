package com.project.placement.service;

import com.project.placement.entity.User;

public interface UserService {

    User registerStudentUser(String email, String password);

    User saveUser(User u);

    User login(String email, String password);

    boolean emailExists(String email);
}
