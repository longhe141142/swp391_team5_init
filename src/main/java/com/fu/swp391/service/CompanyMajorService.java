package com.fu.swp391.service;

import com.fu.swp391.entities.CompanyMajor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CompanyMajorService {
    List<CompanyMajor> findCompanyMajorsByCompanyId(long id);
}
