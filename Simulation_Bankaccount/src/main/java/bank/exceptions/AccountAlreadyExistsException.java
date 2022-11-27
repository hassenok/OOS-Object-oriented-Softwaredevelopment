package bank.exceptions;

import bank.Bank;

/**
 * @author Hassen Trabelsi
 * @version 1.0
 * class AccountAlreadyExistsException, extends class {@link Exception}.
 */
public class AccountAlreadyExistsException extends Exception{

    public AccountAlreadyExistsException(String exception){
        super(exception);
    }
}
