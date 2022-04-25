package com.fu.swp391.repository;

import com.fu.swp391.entities.CV;
import com.fu.swp391.entities.Company;
import com.fu.swp391.entities.skillFake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CVRepository extends JpaRepository<CV, Long> {
    @Query(value = "SELECT * FROM skills_cv join cv where cv.id = skills_cv.cv_id", nativeQuery = true)
    List<CV> findAllCVSkill();

//
//    @Query(value = "SELECT sk.id, sk.name, sk.rate FROM skills_cv sk join cv where cv.id = sk.cv_id", nativeQuery = true)
//    List<skillFake> findSkillFake();

    @Query(value = "SELECT * FROM cv ", nativeQuery = true)
    List<CV> listAllCV();



    @Query(value = "SSELECT * FROM cv join educate_cv edu join experience_cv exc " +
            "join certificate_cv cev join skills_cv skc \n" +
            "where cv.id = edu.cv_id and edu.id = exc.cv_id " +
            "and exc.id= cev.cv_id and cev.id = skc.cv_id ", nativeQuery = true)
    List<CV> listDetailAllOneCV();


//    @Query(value = " ", nativeQuery = true)
//    List<CV> listDetailAllOneCV();




//
//    Optional<CV> findById(Long id);



}
