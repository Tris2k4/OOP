package hotel;

import java.time.LocalDate;

public class StandardRoom extends Room {

    @Override
    public Booking book(LocalDate arrival, LocalDate departure) {
        return super.book(arrival, departure);
    }

    @Override
    public void printWelcomeMessage() {
        System.out.println("Welcome to your standard room. Enjoy your stay :)");
    }

}
