package bank;

import java.util.List;

/**
 * @author Hassen Trabelsi
 * @version 1.0
 * class Payment is the subclass of {@link Transaction}
 */

public class Payment extends Transaction{
    /**
     * @param incomingInterest is the fee for incoming payments.
     * @param outgoingInterest is the fee for outgoing payments.
     */
    private double incomingInterest = 0.0;
    private double outgoingInterest = 0.0;


    /**
     * setter to set the incomingInterest.
     * @param nIncomingInterest fee for incoming Transactions.
     */
    void setIncomingInterest(double nIncomingInterest){incomingInterest = nIncomingInterest;}

    /**
     * setter to set the outgoingInterest.
     * @param nOutgoingInterest fee for outgoing Transactions
     */
    void setOutgoingInterest(double nOutgoingInterest){outgoingInterest = nOutgoingInterest;}

    /**
     * getter to get the value of the incomingInterest.
     * @return returns the value of the incomingInterest.
     */
    public double getIncomingInterest(){return incomingInterest;}

    /**
     * getter to geht the value of the outgoingInterest.
     * @return returns the value of the outgoingInterest.
     */
    public double getOutgoingInterest(){return outgoingInterest;}


    /**
     * constructor for only the parameters date, amount and description.
     * @param date date of actual Transaction
     * @param amount amount of actual Transaction
     * @param description description of actual Transaction
     */
    public Payment(String date, double amount, String description) {
        super(date, amount, description);
    }

    /**
     * constructor for all properties.
     * @param incomingInterest fee for incoming Transactions.
     * @param outgoingInterest fee for outgoing Transactions.
     * @param date date of actual Transaction.
     * @param amount amount of actual Transaction.
     * @param description description of actual Transaction.
     */
    public Payment(double incomingInterest, double outgoingInterest, String date, double amount, String description){
        super(date, amount, description);
        this.incomingInterest = incomingInterest;
        this.outgoingInterest = outgoingInterest;
    }

    /**
     * copyConstructor to make a copy of an existing object.
     * @param payment object of type payment.
     */
    public Payment(Payment payment){
        super(payment.date, payment.amount, payment.description);

        this.date = payment.date;
        this.amount = payment.amount;
        this.description = payment.description;
        this.incomingInterest = payment.incomingInterest;
        this.outgoingInterest = payment.outgoingInterest;

    }


    /**
     * calculates the fee of incoming and outgoing payments.
     * @return returns the value of the amount after calculating the fee.
     */
    @Override
    public double calculate(){
        if(amount > 0){
            return amount = amount - (amount * incomingInterest);
        }else{
            return amount = amount + (amount * incomingInterest);
        }
    }

    @Override
    public void deleteAcccount(String account) {

    }

    @Override
    public List<String> getAllAccounts() {
        return null;
    }

    /**
     * converts properties into a string.
     * @return returns the values of the properties as a string.
     */
    @Override
    public String toString(){
        return "Description: " + "\t\t" + this.description + "\n" +
                "Date: " + "\t\t\t\t" + this.date + "\n" +
                "Amount: " + "\t\t\t" + this.calculate() + "\n" +
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
        return (super.equals(obj)
                && this.incomingInterest == ((Payment) obj).incomingInterest
                && this.outgoingInterest == ((Payment) obj).outgoingInterest);

    }
}
