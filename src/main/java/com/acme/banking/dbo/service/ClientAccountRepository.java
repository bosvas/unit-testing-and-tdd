package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;

public interface ClientAccountRepository {
    void saveClient(Client client);

    int generateNextClientId();

    Client getClientById(int clientId);

    SavingAccount getAccountById(int fromAccountId);
}
