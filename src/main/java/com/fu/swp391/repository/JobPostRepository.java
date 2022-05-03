package com.fu.swp391.repository;


import com.fu.swp391.entities.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface JobPostRepository extends JpaRepository<JobPost,Long> {

    List<JobPost> findCompanyMajorsByCompanyId(long id);

    List<JobPost> findJobPostByCompanyId(long id);

    JobPost findJobPostById(long id);
    List<JobPost> findJobPostByCompanyIdAndMajorName(long id,String name);

    @Query("SELECT j.majorName from JobPost j where j.company.id = ?1")
    List<String> findMajorByCompanyId(long id);

//    @Modifying
//    @Query(value = "DELETE FROM job_post j  WHERE j.id = ?1", nativeQuery = true)
//    void deleteId( long id);
   void deleteById(long id);
}