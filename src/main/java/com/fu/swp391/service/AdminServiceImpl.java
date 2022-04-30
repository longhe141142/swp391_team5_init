package com.fu.swp391.service;

import com.fu.swp391.common.enumConstants.CvSeedData;
import com.fu.swp391.common.enumConstants.FileSrcEnum;
import com.fu.swp391.common.enumConstants.GenderEnum;
import com.fu.swp391.common.enumConstants.StatusEnum;
import com.fu.swp391.entities.CV;
import com.fu.swp391.entities.Candidate;
import com.fu.swp391.entities.CertificateCV;
import com.fu.swp391.entities.EducateCV;
import com.fu.swp391.entities.ExperienceCV;
import com.fu.swp391.entities.ProjectCV;
import com.fu.swp391.entities.SkillCV;
import com.fu.swp391.helper.HelperUntil;
import com.fu.swp391.repository.CVRepository;
import com.fu.swp391.repository.CandidateRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

  @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
  private String BATCH_SIZE;
//  EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.baeldung.movie_catalog");

  @Autowired
  HelperUntil<Candidate> candidateHelperUntil;
  @Autowired
  CandidateRepository candidateRepository;
  @Autowired
  CvService cvService;

  @Autowired
  CVRepository cvRepository;


  //LONG HE141142
  @Transactional
  @Override
  public void InsertBulkCandidatesCV() throws Exception {
    int batch = Integer.parseInt(BATCH_SIZE);
    List<CV> cvs = new ArrayList<>();
    //lay tat ca candidates
    List<Candidate> candidates = (ArrayList<Candidate>) candidateRepository.findAll();
    if (candidates.size() == 0) {
      throw new Exception("No Candidate Available");
    }
    System.out.println("DEBUGGING\n"
        + "CANDIDATE LIST BEFORE SORT:\n");
    candidates.forEach(candidate -> {
      System.out.println("CANDIDATE_ID: " + candidate.getId() + "\n");
    });
    //sap xep candidates random
    candidates = candidateHelperUntil.SortListRandomly(candidates);
    //chon 1 vung de sap xep [from min to max]
    int parameterToSort = candidateHelperUntil.generateRandomNumber(0, candidates.size());
    // lay candidates list theo vung da chon
    candidates = candidateHelperUntil.pickScopeToRandom(candidates, parameterToSort);

    System.out.println(" \n"
        + "PARAMETER_TO_SORT = " + parameterToSort + "\n"
        + "CANDIDATE AFTER RANDOMIZE: \n");
    candidates.forEach(candidate -> {
      System.out.println("CANDIDATE_ID " + candidate.getId() + "\n");
    });

    System.out.println("DONE SORT CANDIDATE LIST!");
    System.out.println("DEBUGGING END!");

    //bat dau tao cv cho candidate
    for (Candidate candidate : candidates) {
      int pickRandomParamTogGenerateCV = candidateHelperUntil.generateRandomNumber(1, 5);
      System.out.println("START ADD CV FOR CANDIDATE_ID: " + candidate.getId() + "\n"
          + "AMOUNT OF CV WILL BE CREATED: " + pickRandomParamTogGenerateCV + "\n");

      //tao cv cho candidate theo so luong lay random
      for (int i = 0; i < pickRandomParamTogGenerateCV; i++) {
        CV cv = new CV();
        cv.setImageURL(FileSrcEnum.DEFAULT_CV_IMAGE);
        cv.setDob(candidateHelperUntil.parseStringToDate(CvSeedData.DOB_DEF));
        cv.setCertificate(CvSeedData.DEF_CERTIFICATE);
        cv.setCreatedAt(new Date());
        cv.setUpdatedAt(new Date());
        cv.setContent(
            CvSeedData.DEF_CONTENT);
        cv.setEmail(candidateHelperUntil.generateRandomEmail());
        cv.setGender(GenderEnum.MALE);
        cv.setStatus(StatusEnum.PUBLIC);
        cv.setPhone(CvSeedData.DEF_PHONE);
        cv.setPurpose(CvSeedData.DEF_PURPOSE);
        cv.setExperience(4);

        //tao cv project
        ProjectCV projectCV  = new ProjectCV();
        projectCV.setProjectName(CvSeedData.PROJECT_CV_COMPANY);
        projectCV.setCompanyName(CvSeedData.PROJECT_CV_COMPANY);
        projectCV.setDescription(CvSeedData.DEF_CONTENT);
        projectCV.setFinishTime(new Date());
        projectCV.setStartTime(new Date());
        projectCV.setRole("DEVELOPER");
        projectCV.setTask("SOME TASK");
        //bat buoc phai set cv cho project
        projectCV.setCv(cv);
        cv.setProject(projectCV);

        //tao cv skill
        SkillCV skillCV = new SkillCV();
        skillCV.setDescription(CvSeedData.DEF_CONTENT);
        skillCV.setName(CvSeedData.SKILL_CV);
        skillCV.setRate(8);
        //bat buoc phai set cv cho skill
        skillCV.setCv(cv);
        cv.setSkill(skillCV);


        //tao cv Educate
        EducateCV educateCV = new EducateCV();
        educateCV.setDescription(CvSeedData.DEF_CONTENT);
        educateCV.setStartTime(new Date());
        educateCV.setMajor("IT");
        educateCV.setSchoolName(CvSeedData.PROJECT_CV_COMPANY);
        //bat buoc phai set cv cho Educate
        educateCV.setCv(cv);
        cv.setOneEducate(educateCV);

        //tao cv EXPERIENCE
        ExperienceCV experienceCV = new ExperienceCV();
        experienceCV.setDescription(CvSeedData.DEF_CONTENT);
        experienceCV.setCompanyName(CvSeedData.PROJECT_CV_COMPANY);
        experienceCV.setJobTitle("IT");
        experienceCV.setStartTime(new Date());
        experienceCV.setEndTime(new Date());
        experienceCV.setCv(cv);
        //bat buoc phai set cv cho EXPERIENCE_CV
        educateCV.setCv(cv);
        cv.setExperience(experienceCV);

        //tao cv certificate
        CertificateCV certificateCV = new CertificateCV();
        certificateCV.setCertificateName(CvSeedData.SKILL_CV);
        certificateCV.setGraduationDate(new Date());
        certificateCV.setOrganization(CvSeedData.PROJECT_CV_COMPANY);
        //bat buoc phai set cv cho CertificateCV
        certificateCV.setCv(cv);
        cv.setCertificate(certificateCV);


        cv.setOneEducate(educateCV);
        //set Candidate  cho cv
        cv.setCandidate(candidate);
        cvs.add(cv);
        cvRepository.save(cv);
        System.out.println("\nDONE CV ID:"+cv.getId()+" OF CANDIDATE ID: "+candidate.getId());

      }
    }
//    cvRepository.saveAll(cvs);
  }
  //LONG HE141142 END


}
