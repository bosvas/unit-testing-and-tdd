package com.acme.banking.dbo.domain;

import com.acme.banking.exception.IllegalAccountOwnerArgumentException;
import com.acme.banking.exception.IllegalClientArgumentException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Client {
    private int id;
    private String name;
    private Collection<Account> accounts = new ArrayList<>();

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

    public Collection<Account> getAccounts() {
        return Collections.unmodifiableCollection(accounts);
    }

    public void addAccount(Account account) {

        if (account == null) throw new NullPointerException("Account can't be null");

        if (account.getClient().equals(this)) {
            this.accounts.add(account);
        } else {
            throw new IllegalAccountOwnerArgumentException("This Account already has another Client!");
        }
    }
}
