package com.fu.swp391.service;

import com.fu.swp391.entities.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public interface CvService {
    List<CV> findCVByCandidate(Candidate candidate);

    List<CV> getAllCVById(Long id);

    List<CV> getAllCV(String name);
    List<CV> getAllCVSkill();

    CV listOneAllCVById(Long id);

    List<CV> listDetailAllOneCV();

   // List<skillFake> getSkillFake();

    CV saveCV(CV cv);


    List<ExperienceCV> getExperienceCVById(long id);

    List<EducateCV> getEducateCVById(long id);

    List<CertificateCV> getCertificateCVById(long id);

    List<SkillCV> getSkillCVById(long id);

    void deleteCVById(Long id);

    Optional<CV> getCVBySpecificId(Long id);

    CV findCVById(long id);
    // Optional<CV> findbyId(Long id);

    ExperienceCV SaveExperienceCV(String companyName, String des, Date endTime, String job, Date startTime, long id);
    CV findById(long id);


    //add experience
    void saveExperience(ExperienceCV experienceCV);


    void saveEducation(EducateCV educateCV);

    void saveCertificate(CertificateCV certificateCV);

    void saveSkillCV(SkillCV skillCV);

    CV UpdateCV(CV cv);

}
