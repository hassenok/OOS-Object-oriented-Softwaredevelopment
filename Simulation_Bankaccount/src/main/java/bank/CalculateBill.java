package bank;

import java.util.List;

/**
 * @author Hassen Trabelsi
 * @version 1.0
 * interface for calculating a bill.
 */
public interface CalculateBill {

   /**
    * calculates the bill of a Transaction.
    * @return returns the bill of a Transaction of type double.
    */
   double calculate();
   void deleteAcccount(String account);
   List<String> getAllAccounts();

}
