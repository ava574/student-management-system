package com.project.placement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.placement.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    List<Company> findByNameContainingIgnoreCaseOrRoleContainingIgnoreCase(
            String name, String role);
}
