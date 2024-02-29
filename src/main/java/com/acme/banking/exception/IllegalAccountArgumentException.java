package com.acme.banking.exception;

public class IllegalAccountArgumentException extends IllegalArgumentException{
    public IllegalAccountArgumentException(String s) {
        super(s);
    }
}
