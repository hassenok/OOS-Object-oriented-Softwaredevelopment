package bank;
/**
 * @author Hassen Trabelsi
 * @version 1.0
 * class IncomingTransfer, extends from Transfer {@link Transfer}.
 */
public class IncomingTransfer extends Transfer{

    /**
     *
     * @param date
     * @param amount
     * @param description
     * {@link Transfer}
     */
    public IncomingTransfer(String date, double amount, String description) {
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
    public IncomingTransfer(String sender, String recipient, String date, double amount, String description) {
        super(sender, recipient, date, amount, description);
    }

    /**
     *
     * @param transfer
     * {@link Transfer}
     */
    public IncomingTransfer(Transfer transfer) {
        super(transfer);
    }

    @Override
    public double calculate() {
        return super.calculate();
    }
}
