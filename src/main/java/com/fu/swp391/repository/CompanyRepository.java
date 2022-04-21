package com.fu.swp391.repository;

import com.fu.swp391.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company,Long> {
    @Query(value = "SELECT * FROM company", nativeQuery = true)
    List<Company> findAllCompany();

    @Override
    Company getById(Long aLong);
}
