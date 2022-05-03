package com.fu.swp391.repository;

import com.fu.swp391.entities.CV;
import com.fu.swp391.entities.Candidate;
import com.fu.swp391.entities.Company;
import com.fu.swp391.entities.User;
import com.fu.swp391.entities.skillFake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Repository
public interface CVRepository extends JpaRepository<CV, Long> {
    @Query(value = "SELECT * FROM skills_cv join cv where cv.id = skills_cv.cv_id", nativeQuery = true)
    List<CV> findAllCVSkill();

//
//    @Query(value = "SELECT sk.id, sk.name, sk.rate FROM skills_cv sk join cv where cv.id = sk.cv_id", nativeQuery = true)
//    List<skillFake> findSkillFake();

    @Query(value = "SELECT * FROM cv where cv.id = ?1 ", nativeQuery = true)
    List<CV> listAllCVById(Long id);

    @Query(value = "SELECT * FROM cv where cv.id = ?1 ", nativeQuery = true)
    CV listOneAllCVById(Long id);

    @Query(value = "SELECT cv.* FROM swp391.cv join candidates c  on cv.candidate_id = c.id  where c.name = ?1  ", nativeQuery = true )
    List<CV> listAllCV(String name);

    @Query(value = "SSELECT * FROM cv join educate_cv edu join experience_cv exc " +
            "join certificate_cv cev join skills_cv skc \n" +
            "where cv.id = edu.cv_id and edu.id = exc.cv_id " +
            "and exc.id= cev.cv_id and cev.id = skc.cv_id ", nativeQuery = true)
    List<CV> listDetailAllOneCV();

//    @Query(value = "SET FOREIGN_KEY_CHECKS=0; \n" +
//            "DELETE FROM cv WHERE cv.id = ?1 LIMIT 1 ", nativeQuery = true)
//    void deleteAllById(Long id);


//    @Query(value = " ", nativeQuery = true)
//    List<CV> listDetailAllOneCV();

        CV findById(long id);

   // void saveEditCV(CV cv);

//    @Transactional
//    @Modifying
//    @Query(value = "update CV c set c.email = :email," +
//            " c.gender = :gender,c.phone = :phone, c.purpose = :purpose," +
//            " c.dob = :dob where c.id = :id", nativeQuery=true)
//    void saveEditCV(@Param(value = "id") long id, @Param(value = "email") String email,
//                @Param(value = "gender") String gender, @Param(value = "phone") String phone
//                , @Param(value = "purpose") String purpose,
//                @Param(value = "dob") Date dob);


//
//    Optional<CV> findById(Long id);

    List<CV> findCVByCandidate(Candidate candidate);

    CV findCVById(long id);

}
