package bank.exceptions;

/**
 * @author Hassen Trabelsi
 * @version 1.0
 * class AccountDoesNotExistException, extends class {@link Exception}.
 */
public class AccountDoesNotExistException extends Exception{
    public AccountDoesNotExistException(String exception){
        super(exception);
    }
}
