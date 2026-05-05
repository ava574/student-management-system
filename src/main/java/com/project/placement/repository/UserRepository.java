package com.project.placement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.placement.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailAndPassword(String email, String password);

    User findByEmail(String email);
}
