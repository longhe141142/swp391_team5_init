package com.fu.swp391.repository;

import com.fu.swp391.entities.SkillCV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillCVRepository extends JpaRepository<SkillCV, Long> {

    @Query(value = "SELECT * FROM cv join skills_cv skv where cv.id = skv.cv_id and skv.cv_id = ?1", nativeQuery = true)
    List<SkillCV> listAllSkillCV(long id);
}
