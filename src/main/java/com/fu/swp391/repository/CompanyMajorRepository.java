package com.fu.swp391.repository;


import com.fu.swp391.entities.JobPost;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompanyMajorRepository extends CrudRepository<JobPost,Long> {

    List<JobPost> findCompanyMajorsByCompanyId(long id);

    List<JobPost> findJobPostByCompanyId(long id);
    List<JobPost> findJobPostByCompanyIdAndMajorName(long id,String name);

    @Query("SELECT j.majorName from JobPost j where j.company.id = ?1")
    List<String> findMajorByCompanyId(long id);
}