package com.fu.swp391.repository;

import com.fu.swp391.entities.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository

public interface UserRepository extends CrudRepository<User, Long> {
//    User findByEmail(String email);

//    Optional<User> findUserByEmail(String email);

//    User findByResetPasswordToken(String token);

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByEmail(String email);

    User findByResetPasswordToken(String token);

    Optional<User> findUserByEmail(String email);
}
