package com.fu.swp391.service;

import com.fu.swp391.entities.CompanyMajor;
import com.fu.swp391.repository.CompanyMajorRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class CompanyMajorServiceImpl implements CompanyMajorService {
    CompanyMajorRepository companyMajorRepository;


    @Override
    public <S extends CompanyMajor> S save(S entity) {
        return companyMajorRepository.save(entity);
    }
    @Override
    public <S extends CompanyMajor> S saveAndFlush(S entity) {
        return companyMajorRepository.saveAndFlush(entity);
    }
}
