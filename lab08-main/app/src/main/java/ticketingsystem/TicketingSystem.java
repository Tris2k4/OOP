package ticketingsystem;

import ticketingsystem.receipts.EmailDecorator;
import ticketingsystem.receipts.PostDecorator;
// import ticketingsystem.receipts.EmailReceipt;
// import ticketingsystem.receipts.PostReceipt;
// import ticketingsystem.receipts.PostReceiptDecorator;
import ticketingsystem.receipts.Receipt;
import ticketingsystem.receipts.ReceiptSender;
import ticketingsystem.receipts.SMSDecorator;
// import ticketingsystem.receipts.SMSReceipt;

public class TicketingSystem {
    private int ticketsLeft;
    private static TicketingSystem ticketingSystem = null;

    private TicketingSystem(int numTickets) {
        this.ticketsLeft = numTickets;
    }

    public static synchronized TicketingSystem getInstance(int numTickets) {
        if (ticketingSystem == null) {
            ticketingSystem = new TicketingSystem(numTickets);
        }
        return ticketingSystem;
    }

    public int getTicketsLeft() {
        return ticketsLeft;
    }

    private void setTicketsLeft(int ticketsLeft) {
        this.ticketsLeft = ticketsLeft;
    }

    public synchronized String buyTicket(String email, String sms, String address) {
        String msg = "";
        if (getTicketsLeft() > 0) {
            setTicketsLeft(getTicketsLeft() - 1);
            msg += "There is now " + getTicketsLeft() + " left!" + "\n";

            // Start with the base receipt
            ReceiptSender receipt = new Receipt();

            // Wrap with decorators as needed
            if (email != null) {
                receipt = new EmailDecorator(receipt, email);
            }
            if (sms != null) {
                receipt = new SMSDecorator(receipt, sms);
            }
            if (address != null) {
                receipt = new PostDecorator(receipt, address);
            }

            double cost = receipt.getCost();
            msg += receipt.send();

            msg += "The total cost is: " + cost + "\n";
            msg += "------------------------------------------";
        } else {
            msg += "Unfortunately there are no tickets left :(";
        }
        System.out.println(msg);
        return msg;
    }

}
