package bank;

import bank.exceptions.AccountAlreadyExistsException;
import bank.exceptions.AccountDoesNotExistException;
import bank.exceptions.TransactionAlreadyExistException;
import bank.exceptions.TransactionDoesNotExistException;
import bank.json.Deserialize;
import bank.json.Serialize;
import com.google.gson.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

/**
 * @author Hassen Trabelsi

 * class PrivateBank, implements Interface {@link Bank}.
 */
public class PrivateBank implements Bank {
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
        private String directoryName;

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
         * getter for property directoryName
         * @return property {@param directoryName} : string
         */
        public String getDirectoryName() {return directoryName;}

        /**
         *
         * @param dir
         */
        public void setDirectoryName(String dir){
                this.directoryName=dir;
        }

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
         * @param name , value for the property {@param name}.
         * @param IncomingInterest , value for the property {@param nIncomingInterest}.
         * @param OutgoingInterest , value for the property {@param nOutgoingInterest}.
         * @param directoryName , value for the property {@param ndirectoryName}.
         */
        public PrivateBank(String name, double IncomingInterest, double OutgoingInterest, String directoryName) throws IOException{
                this.name = name;
                this.incomingInterest = IncomingInterest;
                this.outgoingInterest = OutgoingInterest;
                this.directoryName = directoryName;
               String path = "C:\\Users\\Hassen\\IdeaProjects\\untitled\\Persistent\\" + directoryName;
                File file = new File(path);
                if (!file.exists()) {
                        file.mkdir(); // creates the directory named by this pathname
                }
                else {
                        try {
                                readAccounts();
                        }
                        catch (IOException e) {
                                System.out.println(e);
                        }
                }

        }

        /**
         * copyConstructor to make a copy of an existing object.
         * @param privateBank object of type privateBank.
         */
        public PrivateBank(PrivateBank privateBank) throws IOException{
                this.name = privateBank.getName();
                this.incomingInterest = privateBank.getIncomingInterest();
                this.outgoingInterest = privateBank.getOutgoingInterest();
                this.directoryName = privateBank.getDirectoryName();

                try {
                        readAccounts();
                }
                catch (IOException e) {
                        System.out.println(e);
                }
        }


        /**
         * converts properties into a string.
         * @return returns the values of the properties as a string.
         */
        @Override
        public String toString(){
                return "Transaktionen: " + accountsToTransactions.toString()+ "\n";
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
                return (this.name == ((PrivateBank) obj).name
                        && this.incomingInterest == ((PrivateBank) obj).incomingInterest
                        && this.outgoingInterest == ((PrivateBank) obj).outgoingInterest
                        && this.accountsToTransactions == ((PrivateBank) obj).accountsToTransactions);



        }

        /**
         * Adds an account to the bank. If the account already exists, an exception is thrown.
         *
         * @param account the account to be added
         * @throws AccountAlreadyExistsException if the account already exists
         */
        @Override
        public void createAccount(String account) throws AccountAlreadyExistsException, IOException {


                        if(accountsToTransactions.containsKey(account)){
                                throw new AccountAlreadyExistsException("Account existiert bereits");
                        }
                        else{
                                accountsToTransactions.put(account, new ArrayList<Transaction>());
                                try {
                                        writeAccount(account);
                                }
                                catch (IOException e)
                                {
                                        System.out.println(e);
                                }
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
        public void createAccount(String account, List<Transaction> transactions) throws AccountAlreadyExistsException, IOException {
                try{
                        if(accountsToTransactions.containsKey(account)){
                                throw new AccountAlreadyExistsException("Account existiert bereits");
                        }
                        else{
                                accountsToTransactions.put(account, transactions);
                                try {
                                        writeAccount(account);
                                }
                                catch (IOException e)
                                {
                                        System.out.println(e);
                                }
                        }
                }catch(AccountAlreadyExistsException e){
                        System.out.println(e);
                }


        }

        /**
         *  removes an account if exists
         *  two Exeptions are thrown .
         * @param account the account to be added
         * @throws AccountDoesNotExistException Exeption falls der Account existiert nicht
         * @throws IOException Exeption während des Schreibens
         */
        public void deleteAcccount(String account) throws AccountDoesNotExistException,IOException{
                try{if(!accountsToTransactions.containsKey(account))
                        throw new AccountDoesNotExistException("Account existiert nicht");
                        else {
                        accountsToTransactions.remove(account);
                        try {
                                writeAccount(account);
                        } catch (IOException e){
                                System.out.println(e);
                        }
                }

                }catch (AccountDoesNotExistException e){
                        System.out.println(e);
                }

        }

        /**
         * Show all accounts
         * @return a list containing all the accounts
         */
        public List<String> getAllAccounts(){
                List<String> result=new ArrayList<>() ;
                for(String key:accountsToTransactions.keySet()){
                        result.add(key);
                }
             return result;
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
        public void addTransaction(String account, Transaction transaction) throws TransactionAlreadyExistException, AccountDoesNotExistException, IOException {
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
                        try {
                                writeAccount(account);
                        }
                        catch (IOException e)
                        {
                                System.out.println(e);
                        }

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
        public void removeTransaction(String account, Transaction transaction) throws TransactionDoesNotExistException, IOException {
                List<Transaction> transactionList = getTransactionsList(account);
                try{
                        if(!transactionList.contains(transaction)){
                                throw new TransactionDoesNotExistException("Transaction does not exist");
                        }else{
                                transactionList.remove(transaction);
                                try {
                                        writeAccount(account);
                                }
                                catch (IOException e)
                                {
                                        System.out.println(e);
                                }
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
                        amount += trans.calculate();
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

        /**
         * Creates a Presistent Json File in the PrivateBank -> Directory
         * with all Transactions from the given Account
         * @param account this Account is going to be written into the Json File
         * @throws IOException
         */
        private void writeAccount(String account) throws IOException {

                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(IncomingTransfer.class, new Serialize())
                        .registerTypeAdapter(Payment.class, new Serialize())
                        .registerTypeAdapter(OutgoingTransfer.class, new Serialize())
                        .setPrettyPrinting()
                        .create();

                List<Transaction> ListofTransactions = getTransactions(account);
                String komplett = gson.toJson(ListofTransactions);
                FileWriter fw = new FileWriter("C:\\Users\\Hassen\\IdeaProjects\\untitled\\Persistent\\" + this.directoryName + "\\" + account + ".json");
                fw.write(komplett); // serelized String im Datei einfügen
                fw.close(); // Datei schliessen
        }

        /**
         * Reads all Accounts from the PrivateBank -> Directory and saves them in
         * accountsToTransactions
         * @throws IOException
         */
        private void readAccounts() throws IOException {
                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(IncomingTransfer.class, new Deserialize())
                        .registerTypeAdapter(OutgoingTransfer.class, new Deserialize())
                        .registerTypeAdapter(Payment.class, new Deserialize())
                        .setPrettyPrinting()
                        .create();

                File file = new File("C:\\Users\\Hassen\\IdeaProjects\\untitled\\Persistent\\" + this.directoryName);
                File[] fileArray = file.listFiles();// Array of Files
                for (File f : fileArray) { // für jeden File ::
                        String content = Files.readString(f.toPath()); // wir speichern den Inhalt in einem String "content"
                        String filename = f.getName();
                        int ende = filename.length() - 5; // damit wir die .json ignorieren
                        String key = filename.substring(0, ende); //der richtige Name(Name von account)
                        List<Transaction> transaktionen = new ArrayList<Transaction>(); // die einzufügenden Transaktionen

                        JsonArray t_neu = gson.fromJson(content, JsonArray.class);
                        for (JsonElement JE : t_neu)
                        {
                                JsonObject JO = JE.getAsJsonObject();
                                String classname = JO.get("CLASSNAME").getAsString();
                                if(classname.equals("IncomingTransfer"))
                                {
                                        IncomingTransfer it = gson.fromJson(JO, IncomingTransfer.class);
                                        transaktionen.add(it); // fügen das Element die Transaktionen ein
                                }
                                else if(classname.equals("OutgoingTransfer"))
                                {
                                        OutgoingTransfer ot = gson.fromJson(JO, OutgoingTransfer.class);
                                        transaktionen.add(ot);
                                }
                                else if (classname.equals("Payment"))
                                {
                                        Payment p = gson.fromJson(JO, Payment.class);
                                        transaktionen.add(p);
                                }
                        }
                        this.accountsToTransactions.put(key, transaktionen);
                }
        }


}
