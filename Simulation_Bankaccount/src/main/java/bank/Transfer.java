package bank;

import java.util.List;

/**
 * @author Hassen Trabelsi
 * @version 1.0
 * class Transfer is the subclass of {@link Transaction}
 */
public class Transfer extends Transaction{

    /**
     * @param sender sender of a transaction.
     * @param recipient recipient of a transaction.
     */
    private String sender;
    private String recipient;

    /**
     * setter to set the sender of a Transaction.
     * @param nSender new sender.
     */
    public void setSender(String nSender){sender = nSender;}

    /**
     * setter to set the recipient of a Transaction.
     * @param nRecipient new recipient.
     */
    public void setRecipient(String nRecipient){recipient = nRecipient;}

    /**
     * getter to get the sender of a Transaction.
     * @return returns the sender of a Transaction.
     */
    public String getSender(){return sender;}

    /**
     * getter to get the recipient of a Transaction.
     * @return returns the recipient of a Transaction.
     */
    public String getRecipient(){return recipient;}


    /**
     * constructor for only the parameters date, amount and description.
     * @param date date of a Transaction
     * @param amount amount of a Transaction
     * @param description description of a Transaction
     */
    public Transfer(String date, double amount, String description) {
        super(date, amount, description);
    }

    /**
     * counstructor for all properties.
     * @param sender sender of a Transaction
     * @param recipient recipient of a Transaction
     * @param date date of a Transaction
     * @param amount amount of a Transaction
     * @param description description of a Transaction
     */
    public Transfer(String sender, String recipient, String date, double amount, String description){
        super(date, amount, description);
        this.sender = sender;
        this.recipient = recipient;
    }

    /**
     * copyConstructor to make a copy of an existing object.
     * @param transfer object of type transfer.
     */
    public Transfer(Transfer transfer){

        super(transfer.date, transfer.amount, transfer.description);
        this.date = transfer.date;
        this.amount = transfer.amount;
        this.description = transfer.description;
        this.sender = transfer.sender;
        this.recipient = transfer.recipient;

    }

    /**
     * converts properties into a string.
     * @return returns the values of the properties as a string.
     */
    @Override
    public String toString(){
        return "Description: " + "\t" + this.description + "\n" +
                "Date: " + "\t\t\t" + this.date + "\n" +
                "Amount: " + "\t\t" + this.calculate() + "\n" +
                "Sender: " + "\t\t" + this.sender + "\n" +
                "Recipient: " + "\t\t" + this.recipient + "\n";
    }

    /**
     * is not calculating anything.
     * @return returns just the amount.
     */
    @Override
    public double calculate() {
        return amount;
    }

    @Override
    public void deleteAcccount(String account) {

    }

    @Override
    public List<String> getAllAccounts() {
        return null;
    }

    /**
     * is comparing to Objects whether they're equal or not
     * @param obj object that is going to be compared with actual object.
     * @return returns true, when the Objects are equal.
     */
    @Override
    public boolean equals(Object obj){

        if (obj.getClass() != this.getClass()) {
            return false;
        }
        return (super.equals(obj)
                && this.sender == ((Transfer) obj).sender
                && this.recipient == ((Transfer) obj).recipient);

    }


}
