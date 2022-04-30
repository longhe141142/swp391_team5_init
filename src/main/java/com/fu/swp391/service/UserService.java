package com.fu.swp391.service;

import com.fu.swp391.binding.entiity.UserCandidate;
import com.fu.swp391.entities.Role;
import com.fu.swp391.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends UserDetailsService {
    Iterable<User> findAll();
    Optional<User> findById(Long id);
    User findByEmail(String email);
     User save(User user) throws Exception;
    List<String> getListGender();
    User addRoleToUser(List<String> roles, UserCandidate userCandidate);
    Optional<User> findUserByEmail(String email);

}
