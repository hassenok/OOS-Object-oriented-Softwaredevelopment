package bank.exceptions;

/**
 * @author Hassen Trabelsi
 * @version 1.0
 * class TransactionAlreadyExistException, extends class {@link Exception}.
 */
public class TransactionAlreadyExistException extends Exception{
    public TransactionAlreadyExistException(String exception){
        super(exception);
    }
}
