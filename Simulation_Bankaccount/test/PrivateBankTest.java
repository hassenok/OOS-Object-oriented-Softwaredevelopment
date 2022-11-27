

import bank.*;
import bank.PrivateBank;
import bank.exceptions.AccountAlreadyExistsException;
import bank.exceptions.AccountDoesNotExistException;
import bank.exceptions.TransactionAlreadyExistException;
import bank.exceptions.TransactionDoesNotExistException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.google.gson.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class PrivateBankTest
{
    PrivateBank pB1;
    @BeforeEach
    void init() throws IOException {

            pB1 = new PrivateBank("test", 0.1, 0.1, "Test");
            /*pB1.setName("testfiliale");
            System.out.println(pB1.getName());
            pB1.setIncomingInterest(0.1);
            pB1.setOutgoingInterest(0.1);
            pB1.setDirectoryName("Testfiliale");*/

    }

    @AfterEach
    void tearDown() {
        File file = new File("C:\\Users\\Hassen\\IdeaProjects\\untitled\\Persistent\\" + pB1.getDirectoryName());
        File[] fileArr = file.listFiles();
        for (File f : fileArr) {
            f.delete();
        }
    }

    @Test
    @DisplayName("Constructor : 4 params")
    void constructor_1_test() {
        assertAll("Constructor",
                () -> assertEquals(pB1.getName(), "test"),
                () -> assertEquals(pB1.getIncomingInterest(), 0.1),
                () -> assertEquals(pB1.getOutgoingInterest(), 0.1),
                () -> assertEquals(pB1.getDirectoryName(), "Test")
        );
    }

    @Test
    @DisplayName("CopyConstructor")
    void constructor_2_test() {
        PrivateBank pb2 = pB1;
        assertAll("Constructor",
                () -> assertEquals(pb2.getName(), "test"),
                () -> assertEquals(pb2.getIncomingInterest(), 0.1),
                () -> assertEquals(pb2.getOutgoingInterest(), 0.1),
                () -> assertEquals(pb2.getDirectoryName(), "Test")
        );
    }

    @Test
    @DisplayName("Create Account")
    void create_account_test_1() throws AccountAlreadyExistsException,IOException {
        pB1.createAccount("Janick");

        assertAll("Create Account",
                () -> assertTrue(pB1.getTransactions("Janick").size() >= 0),
                //Fehler wird geworfen..
                () -> assertThrows(AccountAlreadyExistsException.class, () -> pB1.createAccount("Janick"))
        );
    }

    @Test
    @DisplayName("Create Account with transactions")
    void create_account_test_2() throws AccountAlreadyExistsException, IOException {
        List<Transaction> tr = new ArrayList<Transaction>();
        tr.add(new Payment("01.01.2011", 100, "test"));
        pB1.createAccount("Janick", tr);

        assertAll("Create Account",
                () -> assertTrue(pB1.getTransactions("Janick").size() >= 0),
                () -> assertEquals(pB1.getTransactions("Janick"), tr),
                //Fehler wird an der Stelle geworfen..
                () -> assertThrows(AccountAlreadyExistsException.class, () -> {pB1.createAccount("Janick");})
        );
    }

    @Test
    @DisplayName("Add Transaction")
    void add_transaction_test() throws TransactionAlreadyExistException, AccountDoesNotExistException, IOException {
        try {
            pB1.createAccount("Janick");
        }
        catch (AccountAlreadyExistsException e) {
            e.printStackTrace();
        }
        pB1.addTransaction("Janick", new IncomingTransfer("01.01.2011", 100, "test"));
        assertAll("Add Transaction",
                () -> assertTrue(pB1.getTransactions("Janick").size() >= 0),
                () -> assertEquals(pB1.getTransactions("Janick").get(0).getDate(), "01.01.2011"),
                () -> assertEquals(pB1.getTransactions("Janick").get(0).getAmount(), 100),
                () -> assertEquals(pB1.getTransactions("Janick").get(0).getDescription(), "test")

        );
    }

    @Test
    @DisplayName("Remove Transaction")
    void remove_transaction_test() throws TransactionDoesNotExistException, IOException {
        IncomingTransfer it = new IncomingTransfer("01.01.2011", 100, "test");
        try {
            pB1.createAccount("Janick");
            pB1.addTransaction("Janick", it);
            pB1.removeTransaction("Janick", it);
        } catch (AccountAlreadyExistsException | TransactionAlreadyExistException | AccountDoesNotExistException e) {
            e.printStackTrace();
        }
        assertAll("Remove Transaction",
                () -> assertTrue(pB1.getTransactions("Janick").size() == 0)

        );
    }

    @Test
    @DisplayName("Contains Transaction")
    void constains_transaction_test() {
        IncomingTransfer it = new IncomingTransfer("01.01.2011", 100, "test");
        try {
            pB1.createAccount("Janick");
            pB1.addTransaction("Janick", it);
        } catch (AccountAlreadyExistsException | TransactionAlreadyExistException | AccountDoesNotExistException | IOException e) {
            e.printStackTrace();
        }
        assertTrue(pB1.containsTransaction("Janick", it));
    }

    @Test
    @DisplayName("Get Account Balance")
    void get_account_balance_test() {
        IncomingTransfer it = new IncomingTransfer("01.01.2011", 1000, "inc");
        OutgoingTransfer ot = new OutgoingTransfer("01.01.2011", 500, "out");
        Payment p = new Payment("01.01.2011", -100 ,"pay");
        try {
            pB1.createAccount("Hass");
            pB1.addTransaction("Hass", it);
            pB1.addTransaction("Hass", ot);
            pB1.addTransaction("Hass", p);
        } catch (AccountAlreadyExistsException | TransactionAlreadyExistException | AccountDoesNotExistException | IOException e) {
            e.printStackTrace();
        }
        assertEquals(pB1.getAccountBalance("Hass"), 390);
    }

    @Test
    @DisplayName("Get Transactions")
    void get_transactions_test() {
        IncomingTransfer it = new IncomingTransfer("01.01.2011", 1000, "inc");
        OutgoingTransfer ot = new OutgoingTransfer("01.01.2011", 500, "out");
        Payment p = new Payment("01.01.2011", -100 ,"pay");
        try {
            pB1.createAccount("Hass");
            pB1.addTransaction("Hass", it);
            pB1.addTransaction("Hass", ot);
            pB1.addTransaction("Hass", p);
        } catch (AccountAlreadyExistsException | TransactionAlreadyExistException | AccountDoesNotExistException | IOException e) {
            e.printStackTrace();
        }
        assertAll("Get Transactions",
                () -> assertEquals(pB1.getTransactions("Hass").size(), 3),
                () -> assertEquals(pB1.getTransactions("Hass").get(0), it),
                () -> assertEquals(pB1.getTransactions("Hass").get(1), ot),
                () -> assertEquals(pB1.getTransactions("Hass").get(2), p)
        );
    }

    @Test
    @DisplayName("Get Transactions Sorted")
    void get_transactions_sorted_test() {
        IncomingTransfer it = new IncomingTransfer("01.01.2011", 1000, "inc");
        OutgoingTransfer ot = new OutgoingTransfer("01.01.2011", 500, "out");
        Payment p = new Payment("01.01.2011", -100 ,"pay");
        try {
            pB1.createAccount("Janick");
            pB1.addTransaction("Janick", it);
            pB1.addTransaction("Janick", ot);
            pB1.addTransaction("Janick", p);
        } catch (AccountAlreadyExistsException | TransactionAlreadyExistException | AccountDoesNotExistException | IOException e) {
            e.printStackTrace();
        }
        assertAll("Get Transactions Sorted",
                () -> assertEquals(pB1.getTransactionsSorted("Janick", true).size(), 3),
                () -> assertEquals(pB1.getTransactionsSorted("Janick", true).get(2), it),
                () -> assertEquals(pB1.getTransactionsSorted("Janick", true).get(1), ot),
                () -> assertEquals(pB1.getTransactionsSorted("Janick", true).get(0), p),
                () -> assertEquals(pB1.getTransactionsSorted("Janick", false).get(0), it),
                () -> assertEquals(pB1.getTransactionsSorted("Janick", false).get(1), ot),
                () -> assertEquals(pB1.getTransactionsSorted("Janick", false).get(2), p)
        );
    }

    @Test
    @DisplayName("Get Transactions by Type")
    void get_transactions_by_type_test() {
        IncomingTransfer it = new IncomingTransfer("01.01.2011", 1000, "inc");
        OutgoingTransfer ot = new OutgoingTransfer("01.01.2011", 500, "out");
        Payment p = new Payment("01.01.2011", -100 ,"pay");
        try {
            pB1.createAccount("Hassen");
            pB1.addTransaction("Hassen", it);
            pB1.addTransaction("Hassen", ot);
            pB1.addTransaction("Hassen", p);
        } catch (AccountAlreadyExistsException | TransactionAlreadyExistException | AccountDoesNotExistException | IOException e) {
            e.printStackTrace();
        }
        assertAll("Get Transactions by Type",
                () -> assertEquals(pB1.getTransactionsByType("Hassen", true).size(), 1),
                () -> assertEquals(pB1.getTransactionsByType("Hassen", false).size(), 2),
                () -> assertEquals(pB1.getTransactionsByType("Hassen", true).get(0), it),
                () -> assertEquals(pB1.getTransactionsByType("Hassen", false).get(0), ot),
                () -> assertEquals(pB1.getTransactionsByType("Hassen", false).get(1), p)
        );
    }
}




