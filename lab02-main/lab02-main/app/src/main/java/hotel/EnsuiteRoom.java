package hotel;

import java.time.LocalDate;

public class EnsuiteRoom extends Room {

    public Booking book(LocalDate arrival, LocalDate departure) {
        return super.book(arrival, departure);
    }

    @Override
    public void printWelcomeMessage() {
        System.out
                .println("Welcome to your beautiful ensuite room which overlooks the Sydney harbour. Enjoy your stay");
    }

}
