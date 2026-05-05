package com.project.placement.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.placement.entity.Company;
import com.project.placement.repository.CompanyRepository;
import com.project.placement.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository repo;

    public CompanyServiceImpl(CompanyRepository repo) {
        this.repo = repo;
    }

    @Override
    public Company saveCompany(Company c) {
        return repo.save(c);
    }

    @Override
    public Company getCompanyById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public List<Company> getAllCompanies(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return repo.findAll();
        }
        return repo.findByNameContainingIgnoreCaseOrRoleContainingIgnoreCase(keyword, keyword);
    }

    @Override
    public void deleteCompany(Long id) {
        repo.deleteById(id);
    }
}
