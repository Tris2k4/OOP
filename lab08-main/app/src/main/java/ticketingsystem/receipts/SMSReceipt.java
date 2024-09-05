package ticketingsystem.receipts;

public class SMSReceipt extends ReceiptSender {
    private String phoneNumber;
    private static final double FEE = 0.1;

    public SMSReceipt(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String send() {
        String msg = "Sending an sms to " + phoneNumber + "\n";
        return msg;
    }

    public double getCost() {
        return FEE;
    }

}
