package com.fu.swp391.service;

import com.fu.swp391.common.enumConstants.SortEnum;
import com.fu.swp391.entities.Candidate;
import com.fu.swp391.helper.HelperUntil;
import com.fu.swp391.repository.CandidateRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {
    @Autowired
    HelperUntil<Candidate> helperUntilCandidate;
    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateServiceImpl(CandidateRepository _candidateRepository, HelperUntil<Candidate> helperUntilCandidate) {
        super();
        this.candidateRepository = _candidateRepository;
        this.helperUntilCandidate = helperUntilCandidate;
    }

    public HelperUntil<Candidate> getHelperUntilCandidate() {
        return helperUntilCandidate;
    }

    public void setHelperUntilCandidate(HelperUntil<Candidate> helperUntilCandidate) {
        this.helperUntilCandidate = helperUntilCandidate;
    }

    public CandidateRepository getCandidateRepository() {
        return candidateRepository;
    }

    public void setCandidateRepository(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

//    @Override
//    public List<Candidate> getAllCandidate(){
//        return (List<Candidate>) candidateRepository.listAllCandidate();
//    }

    @Override
    public Candidate getCandidate(String email) {
        return candidateRepository.getCandidate(email);
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

    @Override
    public ArrayList<Candidate> findAllCandidatesSortBy(String sortField,String sortBy){
        ArrayList<Candidate> candidates = new ArrayList<>();

        switch (sortBy){
            case SortEnum.ASCENDING:
                for (Candidate candidate : candidateRepository.findAll(Sort.by(sortField).descending())) {
                    candidates.add(candidate);
                }
                break;
            case SortEnum.DESCENDING:
                for (Candidate candidate : candidateRepository.findAll(Sort.by(sortField).ascending())) {
                    candidates.add(candidate);
                }
                break;
            default:
                for (Candidate candidate : candidateRepository.findAll()) {
                    candidates.add(candidate);
                }
        }

        return candidates;
    }

    @Override
    public ArrayList<Candidate> findAllCandidatesByFilter(String searchKeyword){
        return candidateRepository.search(searchKeyword);
    }

    @Override
    public Optional<Candidate> getCandidateById(Long id){
        return candidateRepository.findById(id);
    }

    @Override
    public Optional<Candidate> getcandidatebyEmail(String email) {
        return candidateRepository.findCandidateByMailUser(email);
    }
}