package com.fu.swp391.repository.candidate;

import com.fu.swp391.entities.Candidate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Repository
public interface CandidateRepository extends CrudRepository<Candidate, Long> {

//    @Query()
//    Optional<Candidate> addCandidate();
}
