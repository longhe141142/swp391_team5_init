package com.fu.swp391.service;

import com.fu.swp391.entities.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Optional<Role> findRoleByDescription(String description);

    Optional<Role> findRoleByName(String name);
    List<Role> getAllRole();

}
