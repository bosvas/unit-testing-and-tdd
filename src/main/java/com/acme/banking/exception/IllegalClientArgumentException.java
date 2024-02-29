package com.acme.banking.exception;

public class IllegalClientArgumentException extends IllegalArgumentException{
    public IllegalClientArgumentException(String s) {
        super(s);
    }
}
