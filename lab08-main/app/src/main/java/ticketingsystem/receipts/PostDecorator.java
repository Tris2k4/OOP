package ticketingsystem.receipts;

public class PostDecorator extends ReceiptSender {
    private ReceiptSender receiptSender;
    private String address;
    private static final double FEE = 0.5;

    public PostDecorator(ReceiptSender receiptSender, String address) {
        this.receiptSender = receiptSender;
        this.address = address;
    }

    @Override
    public String send() {
        String msg = receiptSender.send();
        msg += "Sending your mail to " + address + "\n";
        return msg;
    }

    @Override
    public double getCost() {
        // Return the combined cost
        return FEE + receiptSender.getCost();
    }
}
