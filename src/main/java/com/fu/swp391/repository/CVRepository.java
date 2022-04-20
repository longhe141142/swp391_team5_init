package com.fu.swp391.repository;

import com.fu.swp391.entities.CV;
import com.fu.swp391.entities.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CVRepository extends CrudRepository<CV, Long> {
    @Query(value = "SELECT * FROM cv", nativeQuery = true)
    List<CV> findAllCV();
    Optional<CV> findById(Long id);
}
