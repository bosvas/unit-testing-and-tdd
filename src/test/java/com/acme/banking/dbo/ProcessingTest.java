package com.acme.banking.dbo;


import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.dbo.service.ClientAccountRepository;
import com.acme.banking.dbo.service.Processing;
import com.acme.banking.exception.InvalidAmountException;
import com.acme.banking.exception.NotEnoughAmountOnAccountException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

//public void transfer(int fromAccountId, int toAccountId, Double amount) {
//    SavingAccount accountFrom = clientAccountRepository.getAccountById(fromAccountId);
//
//    if (accountFrom.getBalance() < amount){
//        throw new NotEnoughAmountOnAccountException();
//    }
//
//    SavingAccount accountTo = clientAccountRepository.getAccountById(fromAccountId);
//
//    accountFrom.setBalance(accountFrom.getBalance() - amount);
//    accountTo.setBalance(accountTo.getBalance() + amount);
//
//}


public class ProcessingTest {

    private final ClientAccountRepository clientAccountRepositoryMock = Mockito.mock(ClientAccountRepository.class);

    private final Processing processing = new Processing(clientAccountRepositoryMock);

    private int testAccountToId;
    private int testAccountFromId;

    private SavingAccount testAccountFrom;
    private SavingAccount testAccountTo;


    @BeforeEach
    void setup() {
        testAccountFromId = 1;
        double testAccountFromBalance = 7.0;
        Client testAccountFromClient = new Client(38, "ivan");
        testAccountFrom = new SavingAccount(testAccountFromId, testAccountFromClient, testAccountFromBalance);

        testAccountToId = 2;
        double testAccountToBalance = 2.0;
        Client testAccountToClient = new Client(66, "maria");
        testAccountTo = new SavingAccount(testAccountToId, testAccountToClient, testAccountToBalance);

        Mockito.when(clientAccountRepositoryMock.getAccountById(testAccountFromId)).thenReturn(testAccountFrom);
        Mockito.when(clientAccountRepositoryMock.getAccountById(testAccountToId)).thenReturn(testAccountTo);
    }

    @Test
    public void notTransferWhenAccountFromHasNotEnoughAmountOnBalance() {
        double amount = 7.4;

        Assertions.assertThrows(NotEnoughAmountOnAccountException.class,
                () -> processing.transfer(testAccountFromId, testAccountToId, amount));
    }

    @Test
    public void validTransferWhenValidAccountsAndBalances() {
        double amount = 1.0;

        processing.transfer(testAccountFromId, testAccountToId, amount);
        Assertions.assertAll(
                () -> Assertions.assertEquals(6.0, testAccountFrom.getBalance()),
                () -> Assertions.assertEquals(3.0, testAccountTo.getBalance())
        );

    }


    @ParameterizedTest()
    @ValueSource(doubles = {0.0, -7.0})
    public void notTransferWhenInvalidAmount(double amount) {

        Assertions.assertThrows(InvalidAmountException.class,
                () -> processing.transfer(testAccountFromId, testAccountToId, amount));
    }

}
