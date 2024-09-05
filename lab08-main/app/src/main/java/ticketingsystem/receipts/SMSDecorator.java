package ticketingsystem.receipts;

public class SMSDecorator extends ReceiptSender {
    private ReceiptSender receiptSender;
    private String sms;
    private static final double FEE = 0.1;

    public SMSDecorator(ReceiptSender receiptSender, String sms) {
        this.receiptSender = receiptSender;
        this.sms = sms;
    }

    @Override
    public String send() {
        String msg = receiptSender.send();
        msg += "Sending an sms to " + sms + "\n";
        return msg;
    }

    @Override
    public double getCost() {
        // Return the combined cost
        return FEE + receiptSender.getCost();
    }
}
