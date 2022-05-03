package com.fu.swp391.service;

import com.fu.swp391.entities.Request;
import com.fu.swp391.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class RequestServiceImpl1 implements RequestService1{

    @Autowired
    RequestRepository requestRepository;

    @Override
    public Optional<Request> findById(Long aLong) {
        return requestRepository.findById(aLong);
    }

    @Override
    public List<Request> fillAllRequestCompanyByTo_Id(long to_id) {
        return requestRepository.fillAllRequestCompanyByTo_Id(to_id);
    }

    @Override
    public void update(long id, String status, String comment) {

    }
}
