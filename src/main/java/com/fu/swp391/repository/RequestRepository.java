package com.fu.swp391.repository;

import com.fu.swp391.entities.Request;
import com.fu.swp391.entities.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends CrudRepository<Request,Long> {

  @Override
  Optional<Request> findById(Long aLong);

  @Query(value = "SELECT MAX(id) FROM requests",nativeQuery = true)
  Long findMaxId();
}
