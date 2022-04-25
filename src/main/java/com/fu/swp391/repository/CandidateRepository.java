package com.fu.swp391.repository;

import com.fu.swp391.entities.CV;
import com.fu.swp391.entities.Candidate;
import com.fu.swp391.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public interface CandidateRepository extends CrudRepository<Candidate,Long> {

//    @Query()
//    Optional<Candidate> addCandidate();

    @Query(value = " SELECT * FROM candidates where name = 'Nguyễn Anh Tuấn' ", nativeQuery = true)
    Candidate getCandidate();

}
