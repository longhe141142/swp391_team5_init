package com.fu.swp391.repository;

import com.fu.swp391.entities.EducateCV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EducateRepository extends JpaRepository<EducateCV, Long> {
    @Query(value = "SELECT edu.* FROM  educate_cv edu join cv on edu.cv_id = cv.id where edu.cv_id = ?1", nativeQuery = true)
    List<EducateCV> listAllEducateCV(long id);
}
