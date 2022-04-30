package com.fu.swp391.repository;

import com.fu.swp391.entities.CV;
import com.fu.swp391.entities.Candidate;
import com.fu.swp391.entities.Company;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public interface CandidateRepository extends CrudRepository<Candidate,Long> {

  List<Candidate> findAll(Sort by);
  @Query("SELECT c FROM Candidate c WHERE c.name LIKE %?1%")
  ArrayList<Candidate> search(String keyword);
//    @Query()
//    Optional<Candidate> addCandidate();

    @Query(value = " SELECT c.* FROM candidates c join users u on c.user_id = u.id where u.email =  ?1 ", nativeQuery = true)
    Candidate getCandidate(String email);



}
