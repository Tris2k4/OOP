package ticketingsystem.receipts;

public class PostReceipt extends ReceiptSender {
    private String address;
    private static final double FEE = 0.5;

    public PostReceipt(String address) {
        this.address = address;
    }

    public String send() {
        String msg = "Sending your mail to " + address + "\n";
        return msg;
    }

    public double getCost() {
        return FEE;
    }

}
