package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.exception.IllegalAccountArgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SavingAccountTest {

    @Test
    public void shouldCreateSavingAccountWithValidFields(){
        int testClientId = 5;
        String testClientName = "Barney Stinson";
        Client testClient = new Client(testClientId, testClientName);

        int testAccountId = 7;
        double testAmount = 2.5;

        SavingAccount sut = new SavingAccount(testAccountId, testClient, testAmount);

        assertEquals(testAccountId, sut.getId());
        assertEquals(testAmount, sut.getAmount());
        assertEquals(testClient, sut.getClient());
    }

    @Test
    public void shouldNotCreateSavingAccountWithInvalidId(){
        int testClientId = 5;
        String testClientName = "Barney Stinson";
        Client testClient = new Client(testClientId, testClientName);

        int testAccountId = -7;
        double testAmount = 2.5;

        try {
            new SavingAccount(testAccountId, testClient, testAmount);
            fail();
        } catch (IllegalAccountArgumentException e) {
            assertEquals(IllegalAccountArgumentException.class, e.getClass());
            assertEquals("Accounts id can't be lesser then zero", e.getMessage());
        }
    }

    @Test
    public void shouldNotCreateSavingAccountWithInvalidClient(){

        Client testClient = null;

        int testAccountId = 7;
        double testAmount = 2.5;

        try {
            new SavingAccount(testAccountId, testClient, testAmount);
            fail();
        } catch (IllegalAccountArgumentException e) {
            assertEquals(IllegalAccountArgumentException.class, e.getClass());
            assertEquals("Accounts client can't be null!", e.getMessage());
        }
    }

    @Test
    public void shouldNotCreateSavingAccountWithInvalidAmount(){
        int testClientId = 5;
        String testClientName = "Barney Stinson";
        Client testClient = new Client(testClientId, testClientName);

        int testAccountId = 7;
        double testAmount = -2.5;

        try {
            new SavingAccount(testAccountId, testClient, testAmount);
            fail();
        } catch (IllegalAccountArgumentException e) {
            assertEquals(IllegalAccountArgumentException.class, e.getClass());
            assertEquals("Accounts amount can't be lesser then zero", e.getMessage());
        }
    }

}
