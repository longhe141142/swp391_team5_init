package com.fu.swp391.service;

import com.fu.swp391.entities.Candidate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface CandidateService {

   // List<Candidate> getAllCandidate();

    Candidate getCandidate();


     void addNewCandidate(Candidate candidate);
    ArrayList<Candidate> getAllCandidateByPaging(ArrayList<Candidate> candidates, int page, int size);
    ArrayList<Candidate> findAllCandidates();
}