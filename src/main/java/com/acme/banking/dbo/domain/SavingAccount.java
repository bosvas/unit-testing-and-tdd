package com.acme.banking.dbo.domain;

import com.acme.banking.exception.IllegalAccountArgumentException;

public class SavingAccount implements Account {
    private int id;
    private Client client;
    private double balance;

    public SavingAccount(int id, Client client, double balance) {
        if (client == null) throw new IllegalAccountArgumentException("Accounts client can't be null!");

        if (id < 0) throw new IllegalAccountArgumentException("Accounts id can't be lesser then zero");

        if (balance < 0) throw new IllegalAccountArgumentException("Accounts amount can't be lesser then zero");

        this.id = id;
        this.client = client;
        this.balance = balance;

    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
