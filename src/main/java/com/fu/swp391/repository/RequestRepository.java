package com.fu.swp391.repository;

import com.fu.swp391.entities.Company;
import com.fu.swp391.entities.Request;
import com.fu.swp391.entities.Role;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface RequestRepository extends CrudRepository<Request,Long> {
    @Transactional
    @Modifying
    @Query(value = "select *  from requests r where r.to_id = :to_id", nativeQuery = true)
    List<Request> fillAllRequestCompanyByTo_Id(@Param(value = "to_id") long to_id);

  @Override
  Optional<Request> findById(Long aLong);

  @Query(value = "SELECT MAX(id) FROM requests",nativeQuery = true)
  Long findMaxId();

    @Transactional
    @Modifying
    @Query(value = "update requests r set r.status = :status,r.comment = :comment where r.id = :id", nativeQuery = true)
    void update(@Param(value = "id") long id, @Param(value = "status") String status, @Param(value = "comment") String comment);

    ArrayList<Request> findAllByToUserId(Long id);
}
