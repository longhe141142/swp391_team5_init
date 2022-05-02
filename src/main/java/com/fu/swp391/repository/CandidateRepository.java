package com.fu.swp391.repository;

import com.fu.swp391.entities.CV;
import com.fu.swp391.entities.Candidate;
import com.fu.swp391.entities.Company;

import java.util.*;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface CandidateRepository extends CrudRepository<Candidate,Long> {

  List<Candidate> findAll(Sort by);
  @Query("SELECT c FROM Candidate c WHERE c.name LIKE %?1%")
  ArrayList<Candidate> search(String keyword);
//    @Query()
//    Optional<Candidate> addCandidate();


  @Query(value = " SELECT c.* FROM candidates c join users u on c.user_id = u.id where u.email =  ?1 ", nativeQuery = true)
  Optional<Candidate> findCandidateByMailUser(String email);

  @Query(value = " SELECT * FROM candidates where name = 'Nguyễn Anh Tuấn' ", nativeQuery = true)
    Candidate getCandidate();

  @Transactional
  @Modifying
  @Query(value = "update candidates c set  c.phone_number = :phone, c.dob = :dob where c.id = :id", nativeQuery=true)
  void updateCandidate(@Param(value = "id") long id,
              @Param(value = "phone") String phone,
              @Param(value = "dob") Date dob);

}
