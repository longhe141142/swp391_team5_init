package com.fu.swp391.repository;

import com.fu.swp391.entities.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill,Long> {

    Skill findById(long id);



}
