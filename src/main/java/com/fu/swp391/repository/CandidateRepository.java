package com.fu.swp391.repository;

import com.fu.swp391.entities.Candidate;
import com.fu.swp391.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;


public interface CandidateRepository extends JpaRepository<Candidate,Long> {

//    @Query()
//    Optional<Candidate> addCandidate();

}
