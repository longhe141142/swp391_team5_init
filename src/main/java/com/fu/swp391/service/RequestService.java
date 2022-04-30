package com.fu.swp391.service;

import com.fu.swp391.entities.Request;

import java.util.List;

public interface RequestService {
    List<Request> findAllByToUserId(Long id);


}
