package com.fu.swp391.repository;

import com.fu.swp391.entities.CertificateCV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateRepository extends JpaRepository<CertificateCV, Long> {
    @Query(value = "SELECT * FROM cv join certificate_cv ctv where cv.id = ctv.cv_id and ctv.cv_id = ?1", nativeQuery = true)
    List<CertificateCV> listAllCertificateCV(long id);
}
