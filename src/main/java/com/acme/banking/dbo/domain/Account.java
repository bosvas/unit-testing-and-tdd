package com.acme.banking.dbo.domain;

public interface Account {
    int getId();
    double getBalance();
    Client getClient(); //TODO reference integrity
}
