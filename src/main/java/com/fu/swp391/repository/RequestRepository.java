package com.fu.swp391.repository;

import com.fu.swp391.entities.Request;
import com.fu.swp391.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends CrudRepository<Request,Long> {

}
