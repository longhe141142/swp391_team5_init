package com.fu.swp391.repository;

import com.fu.swp391.entities.CertificateCV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateRepository extends JpaRepository<CertificateCV, Long> {
    @Query(value = "SELECT cer.* FROM  certificate_cv cer join cv on cer.cv_id = cv.id where cer.cv_id = ?1", nativeQuery = true)
    List<CertificateCV> listAllCertificateCV(long id);
}
