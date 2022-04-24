package com.fu.swp391.service;

import com.fu.swp391.entities.CompanyMajor;

public interface CompanyMajorService {
    <S extends CompanyMajor> S save(S entity);
    <S extends CompanyMajor> S saveAndFlush(S entity);
}
