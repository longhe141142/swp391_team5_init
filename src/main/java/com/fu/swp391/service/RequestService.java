package com.fu.swp391.service;

import com.fu.swp391.entities.Request;
import java.util.ArrayList;

public interface RequestService {

    <S extends Request> S save(S entity);
    ArrayList<Request> findAllByToUserId(Long id);


}
