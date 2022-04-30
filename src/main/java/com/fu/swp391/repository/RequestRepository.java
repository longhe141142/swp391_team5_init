package com.fu.swp391.repository;

import com.fu.swp391.entities.Company;
import com.fu.swp391.entities.Request;
import com.fu.swp391.entities.User;
import lombok.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Repository
public interface RequestRepository extends CrudRepository<Request,Long> {

    ArrayList<Request> findAllByToUserId(Long id);

}
