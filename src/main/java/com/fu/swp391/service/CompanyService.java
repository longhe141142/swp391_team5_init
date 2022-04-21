package com.fu.swp391.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fu.swp391.entities.Company;
import com.fu.swp391.entities.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

public interface CompanyService {

    List<Company> findAllCompany();
    Optional<Company> findbyId(Long id);

    Company addCompany(Company company, User user);

    public Company getJson(String user, MultipartFile file);

    Company getById(Long id);
}
