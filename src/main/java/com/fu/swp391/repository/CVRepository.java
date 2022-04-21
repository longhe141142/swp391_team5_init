package com.fu.swp391.repository;

import com.fu.swp391.entities.CV;
import com.fu.swp391.entities.Company;
import com.fu.swp391.entities.skillFake;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CVRepository extends CrudRepository<CV, Long> {
    @Query(value = "SELECT * FROM skills_cv join cv where cv.id = skills_cv.cv_id", nativeQuery = true)
    List<CV> findAllCVSkill();

//
//    @Query(value = "SELECT sk.id, sk.name, sk.rate FROM skills_cv sk join cv where cv.id = sk.cv_id", nativeQuery = true)
//    List<skillFake> findSkillFake();

    @Query(value = "SELECT * FROM cv ", nativeQuery = true)
    List<CV> listAllCV();

    Optional<CV> findById(Long id);
}
