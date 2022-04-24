package com.fu.swp391.repository;

import com.fu.swp391.entities.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MajorRepository extends JpaRepository<Major,Long> {

    List<Major> findById(long id);
}
