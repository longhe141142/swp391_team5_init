package com.fu.swp391.repository;

import com.fu.swp391.entities.CV;
import com.fu.swp391.entities.EducateCV;
import com.fu.swp391.entities.ExperienceCV;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface  ExperienceRepository extends JpaRepository<ExperienceCV, Long> {
    @Query(value = "SELECT exc.* FROM  experience_cv exc join cv on exc.cv_id = cv.id where exc.cv_id = ?1", nativeQuery = true)
    List<ExperienceCV> listAllExperienceCV(long id);

    @Query(value = "INSERT INTO `swp391`.`experience_cv`\n" +
            "(`company_name`,\n" +
            "`description`,\n" +
            "`end_time`,\n" +
            "`job_title`,\n" +
            "`start_time`,\n" +
            "`cv_id`)\n" +
            "VALUES\n" +
            "(\n" +
            "?1,\n" +
            "?2,\n" +
            "?3,\n" +
            "?4,\n" +
            "?5,\n" +
            "?6);", nativeQuery = true)
    ExperienceCV SaveExperienceCV(String companyName, String des, Date endTime, String job, Date startTime, long id);



}
