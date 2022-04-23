package com.fu.swp391.service;

import com.fu.swp391.entities.Candidate;
import com.fu.swp391.helper.HelperUntil;
import com.fu.swp391.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {
    @Autowired
    HelperUntil<Candidate> helperUntilCandidate;
    @Autowired
   private CandidateRepository candidateRepository;

    public CandidateServiceImpl(CandidateRepository _candidateRepository){
        super();
        this.candidateRepository = _candidateRepository;
    }

//    @Override
//    public List<Candidate> getAllCandidate(){
//        return (List<Candidate>) candidateRepository.listAllCandidate();
//    }

    @Override
    public Candidate getCandidate() {
        return candidateRepository.getCandidate();
    }

    @Override
    public void addNewCandidate(Candidate candidate){
    }

    @Override
    public ArrayList<Candidate> getAllCandidateByPaging(ArrayList<Candidate> candidates, int page, int size) {
        return helperUntilCandidate.PagingElement(candidates, page, size);
    }

    @Override
    public ArrayList<Candidate> findAllCandidates(){
        ArrayList<Candidate> candidates = new ArrayList<>();
        for (Candidate candidate : candidateRepository.findAll()) {
            candidates.add(candidate);
        }
       return candidates;
    }
}
