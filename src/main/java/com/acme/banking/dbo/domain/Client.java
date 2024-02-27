package com.acme.banking.dbo.domain;

import com.acme.banking.exception.IllegalClientArgumentException;

import java.util.ArrayList;
import java.util.Collection;

public class Client {
    private int id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>(); //TODO

    public Client(int id, String name) {
        if ((name == null) || (name.isBlank())) throw new IllegalClientArgumentException("Client name can't be empty!");

        if (id < 0) throw new IllegalClientArgumentException("Client id can't be lesser then zero");

        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
