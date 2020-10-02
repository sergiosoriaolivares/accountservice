package com.sergio.accountservice.exception;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException(String id) {
        super(id);
    }
}
