package com.example.clientsmanagement.exception;

public class DatabaseViolationExeption extends RuntimeException {


    public DatabaseViolationExeption(String message) {
        super(message);
    }
}
