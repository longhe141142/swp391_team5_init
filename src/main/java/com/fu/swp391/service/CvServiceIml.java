package com.fu.swp391.service;

import com.fu.swp391.entities.CV;
import com.fu.swp391.entities.Candidate;
import com.fu.swp391.repository.CVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CvServiceIml implements CvService {
    @Autowired
    private CVRepository cvRepository;

    public CvServiceIml(CVRepository cvRepository) {
        this.cvRepository = cvRepository;
    }

    @Override
    public List<CV> getAllCV() {
        return (List<CV>) cvRepository.findAll();
    }

    @Override
    public void addNewCVe(CV cv) {

    }

    @Override
    public Optional<CV> findbyId(Long id) {
        return cvRepository.findById(id);
    }
}
