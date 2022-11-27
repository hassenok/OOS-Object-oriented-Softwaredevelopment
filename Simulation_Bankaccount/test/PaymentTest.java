

import bank.IncomingTransfer;
import bank.OutgoingTransfer;
import bank.Payment;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

class PaymentTest {
    Payment pObj1;
    Payment pObj2;

    @BeforeEach
    void init() {
        pObj1 = new Payment("01.02.2012", 100, "testing_1");
        pObj2 = new Payment(0.1, 0.1, "01.02.2013", 200,"testing_2");
    }

    @Test
    @DisplayName("Constructor : 3 params")
    void constructor_1_test() {
        assertAll("Constructor",
                () -> assertEquals(pObj1.getDate(), "01.02.2012"),
                () -> assertEquals(pObj1.getAmount(), 100),
                () -> assertEquals(pObj1.getDescription(), "testing_1"),
                () -> assertEquals(pObj1.getIncomingInterest(), 0.0),
                () -> assertEquals(pObj1.getOutgoingInterest(), 0.0));
    }

    @Test
    @DisplayName("Constructor : 5 params")
    void constructor_2_test() {
        assertAll("Constructor",
                () -> assertEquals(pObj2.getDate(), "01.02.2013"),
                () -> assertEquals(pObj2.getAmount(), 200),
                () -> assertEquals(pObj2.getDescription(), "testing_2"),
                () -> assertEquals(pObj2.getIncomingInterest(), 0.1),
                () -> assertEquals(pObj2.getOutgoingInterest(), 0.1));
    }

    @Test
    @DisplayName("CopyConstructor")
    void copyConstructorTest() {
        Payment p_copy = pObj2;
        assertAll("Constructor",
                () -> assertEquals(p_copy.getDate(), pObj2.getDate()),
                () -> assertEquals(p_copy.getAmount(), pObj2.getAmount()),
                () -> assertEquals(p_copy.getDescription(), pObj2.getDescription()),
                () -> assertEquals(p_copy.getIncomingInterest(), pObj2.getIncomingInterest()),
                () -> assertEquals(p_copy.getOutgoingInterest(), pObj2.getOutgoingInterest())
        );
    }

    @ParameterizedTest
    @ValueSource(ints = {50, 60, 3000, 44444, 9709932})
    @DisplayName("Calculate Methode : pos_value")
    void test_calculate_pos(int amount) {
        pObj2.setAmount(amount);
        double zw = amount * pObj2.getIncomingInterest();
        assertEquals(pObj2.calculate(), amount - zw);
    }

    @ParameterizedTest
    @ValueSource(ints = {-50, -60, -3000, -44444, -9709932})
    @DisplayName("Calculate Methode : neg_value")
    void test_calculate_neg(int amount) {
        pObj2.setAmount(amount);
        double zw = amount * pObj2.getOutgoingInterest();
        assertEquals(pObj2.calculate(), amount + zw);
    }

    @Test
    @DisplayName("Equals Methode")
    void equalsTest() {
        Payment p_copy = pObj2;
        Payment p1 = new Payment(0.1, 0.1, "01.02.2013", 200,"testing_2");
        Payment p_false = new Payment(1, 1, "01.01.2011", 240, "isNotEqual");
        IncomingTransfer t1 = new IncomingTransfer("01.02.2013", 100, "testing_1");
        OutgoingTransfer o1 = new OutgoingTransfer("01.02.2013", 100, "testing_1");
        assertAll("Equals",
                () -> assertEquals(pObj2, p_copy),
                () -> assumeTrue(pObj2.equals(p1)),
                () -> assertNotEquals(pObj2, p_false),
                () -> assertNotEquals(pObj2, t1),
                () -> assertNotEquals(pObj2, o1),
                () -> assertNotEquals(null, pObj2));
    }


    @Test
    @DisplayName("toString Methode")
    void test_toString() {
        assertEquals(pObj2.toString(), "Description: " + "\t\t" + "testing_2" + "\n" +
                "Date: " + "\t\t\t\t" + "01.02.2013" + "\n" +
                "Amount: " + "\t\t\t" + "180.0" + "\n" +
                "Incoming Interest: " + "\t" + "0.1" + "\n" +
                "Outgoing Interest: " + "\t" + "0.1" + "\n");
    }
}