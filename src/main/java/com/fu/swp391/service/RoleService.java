package com.fu.swp391.service;

import com.fu.swp391.entities.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findRoleByDescription(String description);

}
