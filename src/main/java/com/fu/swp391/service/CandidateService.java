package com.fu.swp391.service;

import com.fu.swp391.entities.Candidate;
import com.fu.swp391.helper.HelperUntil;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface CandidateService {

    // List<Candidate> getAllCandidate();

    Candidate getCandidate( String email);
//  List<Candidate> getAllCandidate();

    void addNewCandidate(Candidate candidate);

    ArrayList<Candidate> getAllCandidateByPaging(ArrayList<Candidate> candidates, int page, int size);

    ArrayList<Candidate> findAllCandidates();

    ArrayList<Candidate> findAllCandidatesByFilter(String searchKeyword);

    ArrayList<Candidate> findAllCandidatesSortBy(String sortField, String sortBy);

    HelperUntil<Candidate> getHelperUntilCandidate();

    Optional<Candidate> getCandidateById(Long id);

    Optional<Candidate> getcandidatebyEmail(String email);
}