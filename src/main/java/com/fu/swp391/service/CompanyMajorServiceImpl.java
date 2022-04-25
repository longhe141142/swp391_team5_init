package com.fu.swp391.service;

import com.fu.swp391.entities.JobPost;
import com.fu.swp391.repository.CompanyMajorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CompanyMajorServiceImpl implements CompanyMajorService{
    @Autowired
    CompanyMajorRepository companyMajorRepository;



    @Override
    public List<JobPost> findCompanyMajorsByCompanyId(long id) {
        return this.companyMajorRepository.findCompanyMajorsByCompanyId(id);
    }
}
