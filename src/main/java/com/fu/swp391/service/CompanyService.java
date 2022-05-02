package com.fu.swp391.service;

import com.fu.swp391.entities.Candidate;
import com.fu.swp391.entities.Company;
import com.fu.swp391.entities.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.web.multipart.MultipartFile;

public interface CompanyService {

    List<Company> findAllCompany();
    //    ArrayList<Company> findAllCompany();

    Optional<Company> findbyId(Long id);

    //  List<Company> ListCompanyByPaging(int page, int size);

    Company addCompany(Company company, User user);

    Company getJson(String user, MultipartFile file);

    ArrayList<Company> getAllCompanyByPaging(ArrayList<Company> companies, int page, int size);

    ArrayList<Company> findAllCandidatesByFilter(String searchKeyword);

    Optional<Candidate> getCandidateById(Long id);

  void update(long id,  String name);

    Optional<Company> findCompanyByEmail(String email);
}
