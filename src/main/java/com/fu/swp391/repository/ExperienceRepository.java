package com.fu.swp391.repository;

import com.fu.swp391.entities.CV;
import com.fu.swp391.entities.EducateCV;
import com.fu.swp391.entities.ExperienceCV;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface  ExperienceRepository extends JpaRepository<ExperienceCV, Long> {
    @Query(value = "SELECT * FROM cv join experience_cv exc where cv.id = exc.id and exc.cv_id = ?1", nativeQuery = true)
    List<ExperienceCV> listAllExperienceCV(long id);


}
