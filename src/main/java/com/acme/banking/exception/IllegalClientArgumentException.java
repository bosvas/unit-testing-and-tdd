package com.acme.banking.exception;

public class IllegalClientArgumentException extends IllegalArgumentException{
    public IllegalClientArgumentException() {
        super();
    }

    public IllegalClientArgumentException(String s) {
        super(s);
    }
}
