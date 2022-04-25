package com.fu.swp391.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fu.swp391.common.enumConstants.roleEnum;
import com.fu.swp391.entities.Candidate;
import com.fu.swp391.entities.Company;
import com.fu.swp391.entities.Role;
import com.fu.swp391.entities.User;
import com.fu.swp391.helper.HelperUntil;
import com.fu.swp391.repository.CompanyRepository;
import com.fu.swp391.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
  @Autowired
  PasswordEncoder encoder;
  @Autowired RoleService roleService;
    @Autowired
    CompanyRepository companyRepository;

  @Autowired UserRepository userRepository;

  @Autowired HelperUntil<Company> helperUntilCompany;
  @Autowired HelperUntil<Candidate> helperUntilCandidate;

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

  @Override
  public Company addCompany(Company company, User user) {
    user = this.addUCompanyUserRole(user);
    user.setPasswordEncoder(encoder.encode(user.getPassword()));
    company.setUser(user);
    company = this.companyRepository.save(company);
    return company;
  }

  @Override
  public Company getJson(String company, MultipartFile file) {
    Company CompanyJson = new Company();
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      CompanyJson = objectMapper.readValue(company, Company.class);
      return CompanyJson;
    } catch (IOException err) {
      System.out.printf("Error", err.toString());
    }
    return CompanyJson;
  }

  public User addUCompanyUserRole(User user) {
    String[] roleArray = new String[] {roleEnum.USER, roleEnum.COMPANY};
    List<String> roleList = new ArrayList<>(Arrays.asList(roleArray));
    roleList.forEach(
        role -> {
          Optional<Role> roleUser = roleService.findRoleByName(role);
          roleUser.ifPresent(user::setRole);
        });
    return user;
  }

//  @Override
//  public List<Company> ListCompanyByPaging(int page, int size) {
//    Pageable pageRequest = PageRequest.of(page, size);
//    return companyRepository.findAllCompany(pageRequest);
//  }


  //method2
  @Override
  public ArrayList<Company> getAllCompanyByPaging(
      ArrayList<Company> companies, int page, int size) {
    return helperUntilCompany.PagingElement(companies, page, size);
  }




}
