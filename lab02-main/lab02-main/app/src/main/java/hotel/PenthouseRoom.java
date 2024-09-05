package hotel;

import java.time.LocalDate;

public class PenthouseRoom extends Room {

    public Booking book(LocalDate arrival, LocalDate departure) {
        return super.book(arrival, departure);
    }

    @Override
    public void printWelcomeMessage() {
        System.out.println(
                "Welcome to your penthouse apartment, complete with ensuite, lounge, kitchen and master bedroom.");
    }

}
