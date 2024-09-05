package ticketingsystem;

public class User extends Thread {
    private TicketingSystem ticketSystem;
    private int timeTakenToBuyTicket;
    private String email;
    private String phoneNumber;
    private String address;
    private Boolean emailReceipt;
    private Boolean smsReceipt;
    private Boolean postReceipt;

    private String message;

    public User(TicketingSystem ticketSystem, int timeTakenToBuyTicket, String email, String phoneNumber,
            String address) {
        this.ticketSystem = ticketSystem;
        this.timeTakenToBuyTicket = timeTakenToBuyTicket;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public void setPreferences(Boolean emailReceipt, Boolean smsReceipt, Boolean postReceipt) {
        this.emailReceipt = emailReceipt;
        this.smsReceipt = smsReceipt;
        this.postReceipt = postReceipt;

    }

    public void run() {
        try {
            Thread.sleep(timeTakenToBuyTicket);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        String emailSend = emailReceipt ? email : null;
        String smsSend = smsReceipt ? phoneNumber : null;
        String postSend = postReceipt ? address : null;

        message = ticketSystem.buyTicket(emailSend, smsSend, postSend);
    }

    public String getMessage() {
        return this.message;
    }
}
