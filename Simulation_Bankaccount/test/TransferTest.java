

import bank.IncomingTransfer;
import bank.OutgoingTransfer;
import bank.Payment;
import bank.Transfer;
import org.junit.jupiter.api.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class TransferTest {
    Transfer t1;
    Transfer t2;

    @BeforeEach
    void init() {
        t1 = new Transfer("01.01.2011", 100, "test");
        t2 = new Transfer("Jan", "Flora", "01.01.2011", 100, "test");
    }

    @Test
    @DisplayName("constructor : 3 params")
    void constructor_1_test() {
        assertAll("constructor",
                () -> assertEquals(t1.getDate(), "01.01.2011"),
                () -> assertEquals(t1.getAmount(), 100),
                () -> assertEquals(t1.getDescription(), "test"),
                () -> assertEquals(t1.getSender(), null),
                () -> assertEquals(t1.getRecipient(), null)
        );
    }

    @Test
    @DisplayName("constructor : 5 params")
    void constructor_2_test() {
        assertAll("constructor",
                () -> assertEquals(t2.getDate(), "01.01.2011"),
                () -> assertEquals(t2.getAmount(), 100),
                () -> assertEquals(t2.getDescription(), "test"),
                () -> assertEquals(t2.getSender(), "Jan"),
                () -> assertEquals(t2.getRecipient(), "Flora")
        );
    }

    @Test
    @DisplayName("CopyConstructor")
    void constructor_3_test() {
        Transfer t_copy = t2;
        assertAll("constructor",
                () -> assertEquals(t_copy.getDate(), t2.getDate()),
                () -> assertEquals(t_copy.getAmount(), t2.getAmount()),
                () -> assertEquals(t_copy.getDescription(), t2.getDescription()),
                () -> assertEquals(t_copy.getSender(), t2.getSender()),
                () -> assertEquals(t_copy.getRecipient(), t2.getRecipient())
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {50, 60, 3000, 44444, 9709932})
    @DisplayName("Calculate method with positiv value")
     void test_calculate(int amount) {
        IncomingTransfer ti = new IncomingTransfer("Jan", "Flora", "01.01.2011", amount, "test");
        OutgoingTransfer to = new OutgoingTransfer("Flora", "Jan", "02.02.2011", amount, "test");
        assertAll("Calculate",
                () -> assertEquals(ti.calculate(), amount),
                () -> assertEquals(to.calculate(), -amount)
        );
    }

    @Test
    @DisplayName("Equals Methode")
    void equals_test() {
        Transfer t3_copy = t1;
        Transfer t5_copy = t2;
        Payment p1 = new Payment(0.1, 0.1, "01.01.2011", 100, "test");
        Payment p_false = new Payment(1, 1, "02.02.2022", 230, "notequal");
        IncomingTransfer t1 = new IncomingTransfer("01.01.2011", 100, "test");
        OutgoingTransfer o1 = new OutgoingTransfer("01.01.2011", 100, "test");
        assertAll("Equals",
                () -> assertEquals(this.t1, t3_copy),
                () -> assumeTrue(t2.equals(t5_copy)),
                () -> assertNotEquals(this.t1, p_false),
                () -> assertNotEquals(t2, t1),
                () -> assertNotEquals(this.t1, o1),
                () -> assertNotEquals(null, t2)
        );
    }

    @Test
    @DisplayName("toString Methode")
    void test_toString() {

        t1 = new Transfer("01.01.2011", 100, "test");
        t2 = new Transfer("Jan", "Flora", "01.01.2011", 100, "test");
        assertAll("toString",
                () -> assertEquals(t1.toString(), "Description: " + "\t" + "test" + "\n" +
                        "Date: " + "\t\t\t" + "01.01.2011" + "\n" +
                        "Amount: " + "\t\t" + "100.0" + "\n" +
                        "Sender: " + "\t\t" + null + "\n" +
                        "Recipient: " + "\t\t" + null + "\n"),
                () -> assertEquals(t2.toString(), "Description: " + "\t" + "test" + "\n" +
                        "Date: " + "\t\t\t" + "01.01.2011" + "\n" +
                        "Amount: " + "\t\t" + "100.0" + "\n" +
                        "Sender: " + "\t\t" + "Jan" + "\n" +
                        "Recipient: " + "\t\t" + "Flora" + "\n")
        );

    }
}