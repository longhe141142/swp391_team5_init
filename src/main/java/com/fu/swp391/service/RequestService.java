package com.fu.swp391.service;

import com.fu.swp391.entities.Request;

public interface RequestService {
    <S extends Request> S save(S entity);
}
