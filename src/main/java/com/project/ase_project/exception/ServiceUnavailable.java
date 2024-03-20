package com.project.ase_project.exception;

public class ServiceUnavailable extends RuntimeException {
    public ServiceUnavailable(String message) {
        super(message);
    }
}
