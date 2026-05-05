package com.project.placement.service;

import java.util.List;

import com.project.placement.entity.Company;

public interface CompanyService {

    Company saveCompany(Company c);

    Company getCompanyById(Long id);

    List<Company> getAllCompanies(String keyword);

    void deleteCompany(Long id);
}
