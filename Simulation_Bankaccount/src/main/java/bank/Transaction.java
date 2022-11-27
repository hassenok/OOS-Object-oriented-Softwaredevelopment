package bank;

/**
 * @author Hassen Trabelsi
 * @version 1.0
 * class Transaction is the superclass of the subclass {@link Transfer} and the subclass {@link Payment}
 * implements the Interface {@link CalculateBill}
 */
public abstract class Transaction implements CalculateBill{
    /**
     * @param  date contains the date of a transaction
     * @param  amount contains the amount of a transaction
     * @param  description contains the description of a transaction
     */
    protected String date;
    protected double amount;
    protected String description;

    /**
     * consturctor with the params.
     * @param date actual date of a Transaction.
     * @param amount actual amount of a Transaction.
     * @param description actual description of a Transaction.
     */
    public Transaction(String date, double amount, String description){
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    /**
     * setter to set date.
     * @param nDate new date that is going to be set.
     */
    public void setDate(String nDate){date = nDate;}

    /**
     * setter to set amount.
     * @param nAmount new amount which is going to be set.
     */
    public void setAmount(double nAmount){amount = nAmount;}

    /**
     * setter to set description
     * @param nDescription new description which is going to be set.
     */
    public void setDescription(String nDescription){description = nDescription;}

    /**
     * getter to get date.
     * @return returns the actual date of the Transaction.
     */
    public String getDate(){return date;}

    /**
     * getter to get amount.
     * @return returns the actual amount of the Transaction.
     */
    public double getAmount(){return amount;}

    /**
     * getter to get description.
     * @return returns the actual description of the Transaction.
     */
    public String getDescription(){return description;}




    /**
     * converts properties into a string.
     * @return returns the values of the properties as a string.
     */
    @Override
    public String toString(){
        return "Description: " + this.description + "\n" +
                "Date: " + this.date + "\n" +
                "Amount: " + this.calculate() + "\n";
    }

    /**
     * is comparing to Objects whether they're equal or not
     * @param obj objects that is going to be compared with the actual object.
     * @return returns true, when the Objects are equal.
     */
    @Override
    public boolean equals(Object obj){

        if (obj.getClass() != this.getClass()) {
            return false;
        }
        return (this.date == ((Transaction) obj).getDate() &&
                this.amount == ((Transaction) obj).amount &&
        this.description == ((Transaction) obj).description);

    }
}
