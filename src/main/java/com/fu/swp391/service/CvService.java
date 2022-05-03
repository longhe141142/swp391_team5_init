package com.fu.swp391.service;

import com.fu.swp391.entities.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CvService {
    List<CV> findCVByCandidate(Candidate candidate);

    List<CV> getAllCVById(Long id);

    List<CV> getAllCV();
    List<CV> getAllCVSkill();



    List<CV> listDetailAllOneCV();

   // List<skillFake> getSkillFake();

    void addNewCVe(CV cv);


    List<ExperienceCV> getExperienceCVById(long id);

    List<EducateCV> getEducateCVById(long id);

    List<CertificateCV> getCertificateCVById(long id);

    List<SkillCV> getSkillCVById(long id);

    void deleteCVById(Long id);

    Optional<CV> getCVBySpecificId(Long id);

    CV findCVById(long id);
    // Optional<CV> findbyId(Long id);
}
