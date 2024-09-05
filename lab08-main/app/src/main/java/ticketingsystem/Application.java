package ticketingsystem;

public class Application {
    // NOTE: You should not need to change this file
    public static void main(String[] args) {
        // 2 Ticketing systems are accidentally created, how can we stop this from
        // happening?
        TicketingSystem ticketingSystem = TicketingSystem.getInstance(5);
        TicketingSystem ticketingSystem1 = TicketingSystem.getInstance(5);

        // Users create profiles
        User user1 = new User(ticketingSystem, 1000, "user1@email.com", "1111111111", "UNSW");
        User user2 = new User(ticketingSystem, 2000, "user2@email.com", "2222222222", "The Moon");
        User user3 = new User(ticketingSystem, 2000, "user3@email.com", "3333333333", "The Pacific Ocean");
        User user4 = new User(ticketingSystem, 2000, "user4@email.com", "4444444444", "The North pole");
        User user5 = new User(ticketingSystem, 1000, "user5@email.com", "5555555555", "1945");
        User user6 = new User(ticketingSystem, 1000, "user6@email.com", "6666666666", "Apocalypse Peaks, Antarctica");
        User user7 = new User(ticketingSystem1, 3000, "user7@email.com", "7777777777", "No Name, Colorado");

        // Users click their desired receipt options
        user1.setPreferences(true, false, true);
        user2.setPreferences(false, true, true);
        user3.setPreferences(true, true, false);
        user4.setPreferences(false, false, false);
        user5.setPreferences(true, false, false);
        user6.setPreferences(true, true, true);
        user7.setPreferences(false, false, true);

        // Users click Buy
        user1.start();
        user2.start();
        user3.start();
        user4.start();
        user5.start();
        user6.start();
        user7.start();
    }
}
