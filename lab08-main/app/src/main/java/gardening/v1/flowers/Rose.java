package gardening.v1.flowers;

import java.util.Random;

public class Rose extends Flower {
    public static final String[] MESSAGES = {
            "Abscence makes the heart grow fonder", "A rose by any other name would smell as sweet",
            "One rose says more than the dozen",
            "If every tiny flower wanted to be a rose, spring would lose its loveliness"
    };

    // part of public contract
    public static final int REVEAL_DURATION = 3;

    private static final Random RNG = new Random(); // needs to be static so all rose messages are chosen from the same
                                                    // distribution

    private String chosenMessage;
    private int revealTime = REVEAL_DURATION;

    public Rose() {
        this.chosenMessage = MESSAGES[RNG.nextInt(MESSAGES.length)];
    }

    @Override
    public void grow() {
        this.revealTime = Math.max(0, this.revealTime - 1);
    }

    @Override
    public boolean canHarvest() {
        return this.revealTime == 0;
    }

    public String revealMessage() {
        if (this.revealTime > 0)
            return "No Message Written";
        return chosenMessage;
    }

    @Override
    public int getPrice() {
        return 25 + 3 * chosenMessage.length();
    }

}
