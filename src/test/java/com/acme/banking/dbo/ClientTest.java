package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import com.acme.banking.exception.IllegalAccountOwnerArgumentException;
import com.acme.banking.exception.IllegalClientArgumentException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Test suite")
public class ClientTest {

    @Test
    public void shouldCreateClientWithValidFields() {
        int testId = 5;
        String testName = "Barney Stinson";

        Client sut = new Client(testId, testName);

        Assertions.assertAll(
                () -> assertEquals(testId, sut.getId()),
                () -> assertEquals(testName, sut.getName())
        );
    }

    @Test
    public void shouldNotCreateClientWithInvalidId() {
        int testId = -5;
        String testName = "Barney Stinson";

        try {
            new Client(testId, testName);
            fail();
        } catch (IllegalClientArgumentException e) {
            Assertions.assertAll(
                    () -> assertEquals(IllegalClientArgumentException.class, e.getClass()),
                    () -> assertEquals("Client id can't be lesser then zero", e.getMessage())
            );
        }
    }

    @Test
    public void shouldNotCreateClientWithInvalidNameBlanc() {
        int testId = 5;
        String testName = "";

        try {
            new Client(testId, testName);
            fail();
        } catch (IllegalClientArgumentException e) {
            Assertions.assertAll(
                    () -> assertEquals(IllegalClientArgumentException.class, e.getClass()),
                    () -> assertEquals("Client name can't be empty!", e.getMessage())
            );
        }
    }

    @Test
    public void shouldNotCreateClientWithInvalidNameNull() {
        int testId = 5;
        String testName = null;

        try {
            new Client(testId, testName);
            fail();
        } catch (IllegalClientArgumentException e) {
            Assertions.assertAll(
                    () -> assertEquals(IllegalClientArgumentException.class, e.getClass()),
                    () -> assertEquals("Client name can't be empty!", e.getMessage()));
        }
    }

    @Test
    public void clientShouldAddAccountWhenAccountValid() {
        int testClientId = 5;
        String testClientName = "Barney Stinson";
        Client testClient = new Client(testClientId, testClientName);

        int testAccountId = 7;
        double testAmount = 2.5;

        SavingAccount testAccount = new SavingAccount(testAccountId, testClient, testAmount);

        testClient.addAccount(testAccount);

        assertTrue(testClient.getAccounts().contains(testAccount));
    }

    @Test
    public void clientShouldNotAddAccountWhenAccountNotValidHasAnotherClient() {
        int validTestClientId = 5;
        String validTestClientName = "Barney Stinson";
        Client validTestClient = new Client(validTestClientId, validTestClientName);

        int invalidTestClientId2 = 6;
        String invalidTestClientName2 = "Ted Mosby";
        Client invalidTestClient2 = new Client(invalidTestClientId2, invalidTestClientName2);

        int testAccountId = 7;
        double testAmount = 2.5;

        SavingAccount testAccount = new SavingAccount(testAccountId, validTestClient, testAmount);

        assertThrows(IllegalAccountOwnerArgumentException.class, () -> invalidTestClient2.addAccount(testAccount));
    }


    @Test
    public void clientShouldNotAddAccountWhenAccountNull() {
        int testClientId = 5;
        String testClientName = "Barney Stinson";
        Client testClient = new Client(testClientId, testClientName);

        assertThrows(NullPointerException.class, () -> testClient.addAccount(null));
    }


//    @Test @Disabled("temporary disabled")
//    @DisplayName("Test case")
//    public void shouldStorePropertiesWhenCreated() {
//        //region given
//        final int clientId = 1;
//        final String clientName = "dummy client name";
//        //endregion
//
//        //region when
//        Client sut = new Client(clientId, clientName);
//        assumeTrue(sut != null);
//        //endregion
//
//        //region then
//        //Junit5:
//        assertAll("Client store its properties",
//                () -> assertEquals(clientId, sut.getId()),
//                () -> assertEquals(clientName, sut.getName())
//        );
//
//        //Hamcrest:
//        assertThat(sut,
//            allOf(
//                hasProperty("id", notNullValue()),
//                hasProperty("id", equalTo(clientId)),
//                hasProperty("name", is(clientName))
//        ));
//
//        //AssertJ:
//        org.assertj.core.api.Assertions.assertThat(sut)
//                .hasFieldOrPropertyWithValue("id", clientId)
//                .hasFieldOrPropertyWithValue("name", clientName);
//        //also take a look at `extracting()` https://stackoverflow.com/a/51812188
//        //endregion
//    }
}
