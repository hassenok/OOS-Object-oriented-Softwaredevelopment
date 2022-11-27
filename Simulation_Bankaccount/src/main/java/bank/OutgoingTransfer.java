package bank;

/**
 * @author Hassen Trabelsi
 * @version 1.0
 * class OutgoingTransfer, extends from Transfer {@link Transfer}.
 */
public class OutgoingTransfer extends Transfer{
    /**
     *
     * @param date
     * @param amount
     * @param description
     * {@link Transfer()}
     */
    public OutgoingTransfer(String date, double amount, String description) {
        super(date, amount, description);
    }

    /**
     *
     * @param sender
     * @param recipient
     * @param date
     * @param amount
     * @param description
     * {@link Transfer}
     */
    public OutgoingTransfer(String sender, String recipient, String date, double amount, String description) {
        super(sender, recipient, date, amount, description);
    }

    /**
     *
     * @param transfer
     * {@link Transfer}
     */
    public OutgoingTransfer(Transfer transfer) {
        super(transfer);
    }

    @Override
    public double calculate() {
        return amount * (-1);
    }
}
