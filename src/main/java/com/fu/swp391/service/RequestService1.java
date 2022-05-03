package com.fu.swp391.service;

import com.fu.swp391.entities.Request;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RequestService1 {
    Optional<Request> findById(Long aLong);
    List<Request> fillAllRequestCompanyByTo_Id(long to_id);
    void update(long id, String status, String comment);
}
