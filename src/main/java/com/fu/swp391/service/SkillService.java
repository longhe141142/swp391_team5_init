package com.fu.swp391.service;

import com.fu.swp391.entities.Skill;

import java.util.ArrayList;
import java.util.List;

public interface SkillService {
    Skill findById(long id);

    List<Skill> findAll();
}
