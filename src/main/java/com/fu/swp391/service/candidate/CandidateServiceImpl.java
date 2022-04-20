package com.fu.swp391.service.candidate;

import com.fu.swp391.entities.Candidate;
import com.fu.swp391.repository.candidate.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {
    @Autowired
   private CandidateRepository candidateRepository;

    public CandidateServiceImpl(CandidateRepository _candidateRepository){
        super();
        this.candidateRepository = _candidateRepository;
    }

    @Override
    public List<Candidate> getAllCandidate(){
        return (List<Candidate>) candidateRepository.findAll();
    }
    @Override
    public void addNewCandidate(Candidate candidate){
    }
}
