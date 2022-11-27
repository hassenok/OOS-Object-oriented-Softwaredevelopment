package bank;

import bank.exceptions.AccountAlreadyExistsException;
import bank.exceptions.AccountDoesNotExistException;
import bank.exceptions.TransactionAlreadyExistException;
import bank.exceptions.TransactionDoesNotExistException;

import java.util.*;

/**
 * @author Hassen Trabelsi
 * @version 1.0
 * class PrivateBank, implements Interface {@link Bank}.
 */
public class PrivateBankAlt implements Bank {
    /**
     * @param name Name der privaten Bank
     * @param incomingInterest Zinsatz(positiver Wert in Prozent von 0-1) bei einer Einzahlung.
     * @param outgoingInterest Zinsatz(positiver Wert in Prozent von 0-1) bei einer Auszahlung.
     * @param accountsToTransactions Verknüpft Konten mit Transaktionen, so dass jedem gespeicherten
     *                               Konto 0 bis n Transaktionen zugeordnet werden können.
     *                               Beispiel: "Konto 1" -> [Transaktion1, Transaktion 2]
     *                                         "Konto Adam" -> [Transaktion 3]
     *                                         "Konto Eva" -> []
     *                                         ...
     */
    private String name;
    private double incomingInterest;
    private double outgoingInterest;
    private Map<String, List<Transaction>> accountsToTransactions = new HashMap<String, List<Transaction>>();

    /**
     * getter for property name.
     * @return property {@param name} : string.
     */
    public String getName(){return name;}

    /**
     * getter for property getIncomingInterest.
     * @return property {@param getIncomingInterest} : double
     */
    public double getIncomingInterest(){return incomingInterest;}

    /**
     * getter for property getOutgoingInterest.
     * @return property {@param getOutgoingInterest} : double
     */
    public double getOutgoingInterest(){return outgoingInterest;}

    /**
     * getter for property getAccountsToTransactions.
     * @return property {@param getAccountsToTransactions} : Map<String, List<Transaction>>
     */
    Map<String, List<Transaction>> getAccountsToTransactions(){return accountsToTransactions;}

    /**
     * setter for property {@param name}.
     * @param nName , new value for property {@param name}.
     */
    public void setName(String nName){name = nName;}

    /**
     * setter for property {@param incomingInterest}.
     * @param nIncomingInterest , new value for property {@param incomingInterest}.
     */
    public void setIncomingInterest(double nIncomingInterest){incomingInterest = nIncomingInterest;}

    /**
     * setter for property {@param outgoingInterest}.
     * @param nOutgoingInterest , new value for property {@param outgoingInterest}.
     */
    public void setOutgoingInterest(double nOutgoingInterest){outgoingInterest = nOutgoingInterest;}

    /**
     * setter for porperty {@param accountsToTransactions}.
     * @param nAccountsToTransaction , new value for property {@param accountsToTransactions}.
     */
    public void setAccountsToTransactions(Map<String, List<Transaction>> nAccountsToTransaction){accountsToTransactions = nAccountsToTransaction;}

    /**
     * constructor for the first three properties.
     * @param nName , value for the property {@param name}.
     * @param nIncomingInterest , value for the property {@param nIncomingInterest}.
     * @param nOutgoingInterest , value for the property {@param nOutgoingInterest}.
     */
    public PrivateBankAlt(String nName, double nIncomingInterest, double nOutgoingInterest){
        this.name = nName;
        this.incomingInterest = nIncomingInterest;
        this.outgoingInterest = nOutgoingInterest;
    }

    /**
     * copyConstructor to make a copy of an existing object.
     * @param privateBank object of type privateBank.
     */
    public PrivateBankAlt(PrivateBank privateBank){
        this.name = privateBank.getName();
        this.incomingInterest = privateBank.getIncomingInterest();
        this.outgoingInterest = privateBank.getOutgoingInterest();
    }


    /**
     * converts properties into a string.
     * @return returns the values of the properties as a string.
     */
    @Override
    public String toString(){
        return "Name: " + "\t\t" + this.name + "\n" +
                "Incoming Interest: " + "\t" + this.incomingInterest + "\n" +
                "Outgoing Interest: " + "\t" + this.outgoingInterest + "\n";
    }

    /**
     * is comparing to Objects whether they're equal or not
     * @param obj object thats going to be compared with actual object.
     * @return returns true, when the Objects are equal.
     */
    @Override
    public boolean equals(Object obj){

        if (obj.getClass() != this.getClass()) {
            return false;
        }
        return (this.name == ((PrivateBankAlt) obj).name
                && this.incomingInterest == ((PrivateBankAlt) obj).incomingInterest
                && this.outgoingInterest == ((PrivateBankAlt) obj).outgoingInterest
                && this.accountsToTransactions == ((PrivateBankAlt) obj).accountsToTransactions);

    }

    /**
     * Adds an account to the bank. If the account already exists, an exception is thrown.
     *
     * @param account the account to be added
     * @throws AccountAlreadyExistsException if the account already exists
     */
    @Override
    public void createAccount(String account) throws AccountAlreadyExistsException {

        try{
            if(accountsToTransactions.containsKey(account)){
                throw new AccountAlreadyExistsException("Account existiert bereits");
            }
            else{
                accountsToTransactions.put(account, new ArrayList<Transaction>());
            }
        }catch(AccountAlreadyExistsException e){
            System.out.println(e);
        }


    }
    /**
     * Adds an account (with all specified transactions) to the bank. If the account already exists,
     * an exception is thrown.
     *
     * @param account the account to be added
     * @throws AccountAlreadyExistsException if the account already exists
     */
    @Override
    public void createAccount(String account, List<Transaction> transactions) throws AccountAlreadyExistsException {
        try{
            if(accountsToTransactions.containsKey(account)){
                throw new AccountAlreadyExistsException("Account existiert bereits");
            }
            else{
                accountsToTransactions.put(account, transactions);
            }
        }catch(AccountAlreadyExistsException e){
            System.out.println(e);
        }


    }
    /**
     * Adds a transaction to an account. If the specified account does not exist, an exception is
     * thrown. If the transaction already exists, an exception is thrown.
     *
     * @param account     the account to which the transaction is added
     * @param transaction the transaction which is added to the account
     * @throws TransactionAlreadyExistException if the transaction already exists
     */
    @Override
    public void addTransaction(String account, Transaction transaction) throws TransactionAlreadyExistException, AccountDoesNotExistException {
        List<Transaction> transactionsList = getTransactionsList(account);
        try{
            if(!accountsToTransactions.containsKey(account)){
                throw new AccountDoesNotExistException("Account does not exist!");

            }else if(transactionsList.contains(transaction)){
                throw new TransactionAlreadyExistException("Transaction already exist!");
            }
            if (transaction instanceof Payment){
                ((Payment) transaction).setIncomingInterest(this.incomingInterest);
                ((Payment) transaction).setOutgoingInterest(this.outgoingInterest);
            }
            accountsToTransactions.get(account).add(transaction);

        }catch(TransactionAlreadyExistException | AccountDoesNotExistException e){
            System.out.println(e);
        }

    }
    /**
     * Removes a transaction from an account. If the transaction does not exist, an exception is
     * thrown.
     *
     * @param account     the account from which the transaction is removed
     * @param transaction the transaction which is added to the account
     * @throws TransactionDoesNotExistException if the transaction cannot be found
     */
    @Override
    public void removeTransaction(String account, Transaction transaction) throws TransactionDoesNotExistException {
        List<Transaction> transactionList = getTransactionsList(account);
        try{
            if(!transactionList.contains(transaction)){
                throw new TransactionDoesNotExistException("Transaction does not exist");
            }else{
                transactionList.remove(transaction);
            }

        }catch(TransactionDoesNotExistException e){
            System.out.println(e);
        }

    }

    private List<Transaction> getTransactionsList(String account) {
        return accountsToTransactions.get(account);
    }

    /**
     * Checks whether the specified transaction for a given account exists.
     *
     * @param account     the account from which the transaction is checked
     * @param transaction the transaction which is added to the account
     */
    @Override
    public boolean containsTransaction(String account, Transaction transaction) {
        List<Transaction> transactionsList = getTransactionsList(account);
        return transactionsList.contains(transaction);
    }

    /**
     * Calculates and returns the current account balance.
     *
     * @param account the selected account
     * @return the current account balance
     */
    @Override
    public double getAccountBalance(String account) {
        List<Transaction> list = accountsToTransactions.get(account);
        double amount = 0.0;
        for(Transaction trans : list){
            if(trans instanceof Transfer){
                //((Transfer) trans).getSender() == account
                if(((Transfer) trans).getSender().equals(account)){
                    amount -= trans.calculate();
                }
                else{
                    amount+= trans.calculate();
                }
            }
            else {
                //Payment
                amount += trans.calculate();
            }
        }
        return amount;
    }
    /**
     * Returns a list of transactions for an account.
     *
     * @param account the selected account
     * @return the list of transactions
     */
    @Override
    public List<Transaction> getTransactions(String account) {
        return getTransactionsList(account);
    }
    /**
     * Returns a sorted list (-> calculated amounts) of transactions for a specific account . Sorts the list either in ascending or descending order
     * (or empty).
     *
     * @param account the selected account
     * @param asc     selects if the transaction list is sorted ascending or descending
     * @return the list of transactions
     */
    @Override
    public List<Transaction> getTransactionsSorted(String account, boolean asc) {
        List<Transaction> transactionList = getTransactionsList(account);
        transactionList.sort(Comparator.comparing(Transaction::getAmount));
        if(!asc){
            Collections.reverse(transactionList);
        }
        return transactionList;
    }

    /**
     * Returns a list of either positive or negative transactions (-> calculated amounts).
     *
     * @param account  the selected account
     * @param positive selects if positive  or negative transactions are listed
     * @return the list of transactions
     */
    @Override
    public List<Transaction> getTransactionsByType(String account, boolean positive) {
        List<Transaction> transactionList = getTransactionsList(account);
        List<Transaction> sortedList = new ArrayList<Transaction>();
        if(positive){
            for(Transaction trans: transactionList){
                if(trans.calculate() >= 0)
                    sortedList.add(trans);
            }
        }else{
            for(Transaction trans: transactionList){
                if(trans.calculate() < 0)
                    sortedList.add(trans);
            }
        }
        return sortedList;
    }

}
