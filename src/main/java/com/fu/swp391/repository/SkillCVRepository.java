package com.fu.swp391.repository;

import com.fu.swp391.entities.SkillCV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillCVRepository extends JpaRepository<SkillCV, Long> {

    @Query(value = "SELECT ski.* FROM  skills_cv ski join cv on ski.cv_id = cv.id where ski.cv_id = ?1", nativeQuery = true)
    List<SkillCV> listAllSkillCV(long id);
}
