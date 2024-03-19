package com.project.ase_project.exception;

public class GatewayTimeout extends RuntimeException {
    public GatewayTimeout(String message) {
        super(message);
    }
}
