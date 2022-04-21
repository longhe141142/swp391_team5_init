package com.fu.swp391.config.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.Instant;
import java.util.List;

public class ApiError {

    private int code;
    private String message;
    private Instant timestamp;
    ObjectMapper mapper = new ObjectMapper();
    ObjectNode responseBody = mapper.createObjectNode();
    private List<String> errors;
    HttpStatus status;

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
    public ApiError(){
        this.timestamp = Instant.now();
    }

    public ApiError(int code, String message) {
        this.code = code;
        this.message = message;
        this.timestamp = Instant.now();
    }

    public ApiError(HttpStatus status, String message, List<String> errors,ObjectNode responseBody) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
        this.responseBody = responseBody;
    }
    public ApiError(HttpStatus status, String message,ObjectNode responseBody) {
        super();
        this.status = status;
        this.message = message;
        this.responseBody = responseBody;
    }

    public ApiError(int code, String message, BindingResult bindingResult) {
        this.code = code;
        this.message = message;
        this.timestamp = Instant.now();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            this.responseBody.put(fieldError.getField(),fieldError.getDefaultMessage());
        }
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public ObjectNode getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(ObjectNode responseBody) {
        this.responseBody = responseBody;
    }

    public ApiError(int code, String message, Instant timestamp) {
        this.code = code;
        this.message = message;
        this.timestamp = timestamp;
    }

    // Getters and setters here...
}