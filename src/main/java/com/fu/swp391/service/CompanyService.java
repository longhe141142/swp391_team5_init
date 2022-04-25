package com.fu.swp391.service;

import com.fu.swp391.entities.Candidate;
import com.fu.swp391.entities.Company;
import com.fu.swp391.entities.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface CompanyService {

    List<Company> findAllCompany();
  //    ArrayList<Company> findAllCompany();

  Optional<Company> findbyId(Long id);

  //  List<Company> ListCompanyByPaging(int page, int size);

  Company addCompany(Company company, User user);

  Optional<Company> save(Company company);

  Company getJson(String user, MultipartFile file);

  ArrayList<Company> getAllCompanyByPaging(ArrayList<Company> companies, int page, int size);

    ArrayList<Company> findAllCandidatesByFilter(String searchKeyword);

}
