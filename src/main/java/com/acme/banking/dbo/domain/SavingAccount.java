package com.acme.banking.dbo.domain;

import com.acme.banking.exception.IllegalAccountArgumentException;

public class SavingAccount implements Account {
    private int id;
    private Client client;
    private double amount;

    public SavingAccount(int id, Client client, double amount) {
        if (client == null) throw new IllegalAccountArgumentException("Accounts client can't be null!");

        if (id < 0) throw new IllegalAccountArgumentException("Accounts id can't be lesser then zero");

        if (amount < 0) throw new IllegalAccountArgumentException("Accounts amount can't be lesser then zero");

        this.id = id;
        this.client = client;
        this.amount = amount;

    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Client getClient() {
        return client;
    }
}
