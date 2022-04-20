package com.fu.swp391.service;

import com.fu.swp391.entities.Company;
import com.fu.swp391.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository _compaCompanyRepository){
        this.companyRepository = _compaCompanyRepository;
    }


    @Override
    public List<Company> findAllCompany() {
        return this.companyRepository.findAllCompany();
    }

    @Override
    public Optional<Company> findbyId(Long id) {
        return this.companyRepository.findById(id);
    }

}
