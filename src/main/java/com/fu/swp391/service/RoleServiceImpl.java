package com.fu.swp391.service;

import com.fu.swp391.entities.Role;
import com.fu.swp391.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository _roleRepository) {
        this.roleRepository = _roleRepository;
    }

    @Override
    public  Optional<Role> findRoleByDescription(String description){
        return this.roleRepository.findRoleByDescription(description);
    };

    @Override
    public Optional<Role> findRoleByName(String name){
        return this.roleRepository.findRoleByName(name);
    }

    @Override
    public List<Role> getAllRole(){
      return this.roleRepository.getAllRole();
    }




}
