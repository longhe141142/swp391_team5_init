package com.fu.swp391.repository;

import com.fu.swp391.entities.Company;
import com.fu.swp391.entities.CompanyMajor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyMajorRepository extends CrudRepository<CompanyMajor,Long> {

    List<CompanyMajor> findCompanyMajorsByCompanyId(long id);
}