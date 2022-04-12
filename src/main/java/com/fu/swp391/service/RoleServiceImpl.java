package com.fu.swp391.service;

import com.fu.swp391.entities.Role;
import com.fu.swp391.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
