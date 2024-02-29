package com.acme.banking.exception;

public class IllegalAccountOwnerArgumentException extends IllegalStateException{
    public IllegalAccountOwnerArgumentException(String s) {
        super(s);
    }
}
