package com.fu.swp391.service;

import com.fu.swp391.entities.JobPost;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CompanyMajorService {
    List<JobPost> findCompanyMajorsByCompanyId(long id);
}
