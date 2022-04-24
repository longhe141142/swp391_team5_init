package com.fu.swp391.config.entity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public class ResponseSuccess extends ResponseEntity<Object> {
    HttpStatus status;
    Object body;
    public ResponseSuccess(Object obj) {
        super(HttpStatus.OK);
        this.body = obj;
    }
}
