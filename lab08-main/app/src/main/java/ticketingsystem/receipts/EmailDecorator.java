package ticketingsystem.receipts;

public class EmailDecorator extends ReceiptSender {
    private ReceiptSender receiptSender;
    private String email;
    private static final double FEE = 0; // Assuming no additional cost for email

    public EmailDecorator(ReceiptSender receiptSender, String email) {
        this.receiptSender = receiptSender;
        this.email = email;
    }

    @Override
    public String send() {
        String msg = receiptSender.send();
        msg += "Sending an email to " + email + "\n";
        return msg;
    }

    @Override
    public double getCost() {
        // Return the combined cost
        return FEE + receiptSender.getCost();
    }
}
