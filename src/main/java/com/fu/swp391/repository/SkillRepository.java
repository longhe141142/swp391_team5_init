package com.fu.swp391.repository;

import com.fu.swp391.entities.Skill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface SkillRepository extends JpaRepository<Skill,Long> {

    Skill findById(long id);



}
