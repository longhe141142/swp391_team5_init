package com.fu.swp391.repository;


import com.fu.swp391.entities.JobPost;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JobPostRepository extends CrudRepository<JobPost,Long> {

    List<JobPost> findCompanyMajorsByCompanyId(long id);
}