package com.fu.swp391.service;

import com.fu.swp391.entities.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CvService {
    List<CV> getAllCVById(Long id);

    List<CV> getAllCV(String name);
    List<CV> getAllCVSkill();



    List<CV> listDetailAllOneCV();

   // List<skillFake> getSkillFake();

    CV saveCV(CV cv);


    List<ExperienceCV> getExperienceCVById(long id);

    List<EducateCV> getEducateCVById(long id);

    List<CertificateCV> getCertificateCVById(long id);

    List<SkillCV> getSkillCVById(long id);

    void deleteCVById(Long id);

    Optional<CV> getCVBySpecificId(Long id);
    // Optional<CV> findbyId(Long id);
}
