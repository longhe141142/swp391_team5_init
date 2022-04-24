package com.fu.swp391.repository;

import com.fu.swp391.entities.EducateCV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EducateRepository extends JpaRepository<EducateCV, Long> {
    @Query(value = "SELECT * FROM cv join educate_cv edc where cv.id = edc.id and edc.cv_id = ?1", nativeQuery = true)
    List<EducateCV> listAllEducateCV(long id);
}
