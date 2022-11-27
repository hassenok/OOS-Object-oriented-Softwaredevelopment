package bank.exceptions;

/**
 * @author Hassen Trabelsi
 * @version 1.0
 * class TransactionDoesNotExistException, extends class {@link Exception}.
 */
public class TransactionDoesNotExistException extends Exception{
    public TransactionDoesNotExistException(String exception){
        super(exception);
    }
}
