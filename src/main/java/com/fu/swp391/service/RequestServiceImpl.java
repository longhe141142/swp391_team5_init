package com.fu.swp391.service;

import com.fu.swp391.entities.Request;
import com.fu.swp391.repository.RequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService{
    @Autowired
    RequestRepository requestRespository;
    @Override
    public List<Request> findAllByToUserId(Long id) {
        return requestRespository.findAllByToUserId(id);
    }




}
