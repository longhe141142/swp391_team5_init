package com.fu.swp391.service;

import com.fu.swp391.entities.*;
import com.fu.swp391.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CvServiceIml implements CvService {
    @Autowired
    private CVRepository cvRepository;
    @Autowired
    private ExperienceRepository experienceRepository;

    @Autowired
    private EducateRepository educateRepository;
    @Autowired
    private CertificateRepository certificateRepository;
    @Autowired
    private SkillCVRepository skillCVRepository;

    public CvServiceIml(CVRepository cvRepository, ExperienceRepository experienceRepository, EducateRepository educateRepository, CertificateRepository certificateRepository, SkillCVRepository skillCVRepository) {
        this.cvRepository = cvRepository;
        this.experienceRepository = experienceRepository;
        this.educateRepository = educateRepository;
        this.certificateRepository = certificateRepository;
        this.skillCVRepository = skillCVRepository;
    }

    @Override
    public List<CV> findCVByCandidate(Candidate candidate) {
        return cvRepository.findCVByCandidate(candidate);
    }

    @Override
    public List<CV> getAllCVById(Long id) {
        return (List<CV>) cvRepository.listAllCVById(id);
    }

    @Override
    public List<CV> getAllCV() {
        return cvRepository.listAllCV();
    }


    @Override
    public List<CV> getAllCVSkill() {
        return cvRepository.findAllCVSkill();
    }


    @Override
    public List<CV> listDetailAllOneCV() {
        return cvRepository.listDetailAllOneCV();
    }

//    @Override
//    public List<skillFake> getSkillFake() {
//        return cvRepository.findSkillFake();
//    }

    @Override
    public void addNewCVe(CV cv) {

    }

    @Override
    public List<ExperienceCV> getExperienceCVById(long id) {
        List<ExperienceCV> optional = experienceRepository.listAllExperienceCV(id);
//        ExperienceCV experienceCV = null;
//        if (optional.isPresent()){
//            experienceCV = optional.get();
//        }else {
//            throw new RuntimeException("Experience CV not Found for id :: " + id);
//        }
        return optional;
    }

    @Override
    public List<EducateCV> getEducateCVById(long id) {

        List<EducateCV> optional1 = educateRepository.listAllEducateCV(id);
        return optional1;
    }

    @Override
    public List<CertificateCV> getCertificateCVById(long id) {
        List<CertificateCV> optional2 = certificateRepository.listAllCertificateCV(id);
        return optional2;
    }

    @Override
    public List<SkillCV> getSkillCVById(long id) {
        List<SkillCV> optional3 = skillCVRepository.listAllSkillCV(id);
        return optional3;
    }

    @Override
    public void deleteCVById(Long id) {
        cvRepository.deleteById(id);
    }

    @Override
    public Optional<CV> getCVBySpecificId(Long id){
        return cvRepository.findById(id);
    }

//    @Override
//    public Optional<CV> findbyId(Long id) {
//        return cvRepository.findById(id);
//    }


}
