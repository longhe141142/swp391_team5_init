package com.fu.swp391.service;

import com.fu.swp391.entities.Candidate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CandidateService {

    List<Candidate> getAllCandidate();

     void addNewCandidate(Candidate candidate);
}