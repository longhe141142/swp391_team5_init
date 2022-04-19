package com.fu.swp391.repository;

import com.fu.swp391.entities.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role,Long> {

    Optional<Role> findRoleByDescription(String description);
    Optional<Role> findRoleByName(String name);
}
