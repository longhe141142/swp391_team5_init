package com.fu.swp391.service;

import com.fu.swp391.entities.Skill;
import com.fu.swp391.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService{


    @Autowired
    SkillRepository skillRepository;

    @Override
    public Skill findById(long id) {
        return skillRepository.findById(id);
    }

    @Override
    public List<Skill> findAll() {
        return skillRepository.findAll();
    }
//    @Override
//    public ArrayList<Skill> getSkillByMajOrId(long id) {
//        return skillRepository.getSkillByMajOrId(id);
//    }


}

