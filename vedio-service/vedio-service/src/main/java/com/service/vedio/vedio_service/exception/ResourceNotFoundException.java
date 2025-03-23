package com.service.vedio.vedio_service.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException() {

        super("Resource not found");
    }

    public ResourceNotFoundException(String courseNotFound) {

        super(courseNotFound);
    }
}
