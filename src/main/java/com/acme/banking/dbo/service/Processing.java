package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.exception.InvalidAmountException;
import com.acme.banking.exception.NotEnoughAmountOnAccountException;

import java.util.Collection;
import java.util.Collections;

public class Processing {

    private ClientAccountRepository clientAccountRepository;


    public Processing(ClientAccountRepository clientAccountRepository) {
        this.clientAccountRepository = clientAccountRepository;
    }

//
//    public Client createClient(String name) {
//
//        int nextId = clientAccountRepository.generateNextClientId();
//
//        Client client = new Client(nextId, name);
//
//        clientAccountRepository.saveClient(client);
//
//        return client;
//    }
//
//    public Collection<Account> getAccountsByClientId(int clientId) {
//        Client client = clientAccountRepository.getClientById(clientId);
//
//        return Collections.unmodifiableCollection(client.getAccounts());
//
//    }

    public void transfer(int fromAccountId, int toAccountId, Double amount) {
        if (amount <= 0){
            throw new InvalidAmountException();
        }

        SavingAccount accountFrom = clientAccountRepository.getAccountById(fromAccountId);

        if (accountFrom.getBalance() < amount){
            throw new NotEnoughAmountOnAccountException();
        }

        SavingAccount accountTo = clientAccountRepository.getAccountById(toAccountId);

        accountFrom.setBalance(accountFrom.getBalance() - amount);
        accountTo.setBalance(accountTo.getBalance() + amount);

    }


    public void cash(double amount, int fromAccountId) {
        Cash.log(amount, fromAccountId);
    }
}
