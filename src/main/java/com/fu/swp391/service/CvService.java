package com.fu.swp391.service;

import com.fu.swp391.entities.CV;
import com.fu.swp391.entities.Company;
import com.fu.swp391.entities.skillFake;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CvService {
    List<CV> getAllCV();

    List<CV> getAllCVSkill();

   // List<skillFake> getSkillFake();

    void addNewCVe(CV cv);

    Optional<CV> findbyId(Long id);
}
