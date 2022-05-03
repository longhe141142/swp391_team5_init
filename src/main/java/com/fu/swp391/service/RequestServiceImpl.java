package com.fu.swp391.service;

import com.fu.swp391.entities.Request;
import com.fu.swp391.repository.RequestRepository;
import org.springframework.stereotype.Service;

@Service
public class RequestServiceImpl implements RequestService{

    RequestRepository requestRepository;

    @Override
    public <S extends Request> S save(S entity) {
        return requestRepository.save(entity);
    }
}
