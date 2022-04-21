package com.fu.swp391.service;

import com.fu.swp391.entities.Company;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    List<Company> findAllCompany();
    Optional<Company> findbyId(Long id);
}
