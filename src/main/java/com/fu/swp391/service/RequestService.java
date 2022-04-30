package com.fu.swp391.service;

import com.fu.swp391.entities.Request;

import java.util.ArrayList;
import java.util.List;

public interface RequestService {
    ArrayList<Request> findAllByToUserId(Long id);


}
