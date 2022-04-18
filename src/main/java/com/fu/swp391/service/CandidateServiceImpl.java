package com.fu.swp391.service;

import com.fu.swp391.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    CandidateRepository candidateRepository;

    public CandidateServiceImpl(CandidateRepository _candidateRepository){
        this.candidateRepository = _candidateRepository;
    }
}
