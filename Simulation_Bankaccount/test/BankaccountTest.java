import Ubung3.Bankaccount;
import  org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class BankaccountTest {
    Bankaccount b1=new Bankaccount("Nicole");
    Bankaccount b2=new Bankaccount("Amelie");

    @Test
    public void TestNotNull(){
        b1.deposit(100);
        b2.deposit(100);
        assertNotNull(b1);
        assertNotNull(b2);

    }
    @Test
    public void Testavailibility(){
        assertTrue(b1.getBalance()==100);
        assertEquals(100,b2.getBalance());

    }
}
