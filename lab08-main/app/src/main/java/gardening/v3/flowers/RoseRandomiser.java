package gardening.v3.flowers;

import java.util.Random;

public class RoseRandomiser extends RoseMutation<Rose> {
    private int replacementIndex;
    private char replacementChar;
    private static final Random RNG = new Random();

    public RoseRandomiser(Rose flower) {
        super(flower);
        this.replacementIndex = RNG.nextInt(getFlower().revealMessage().length());
        this.replacementChar = (char) ('A' + RNG.nextInt(26));
    }

    @Override
    public String revealMessage() {
        String message = super.revealMessage();

        StringBuilder builder = new StringBuilder(message);
        builder.setCharAt(replacementIndex, replacementChar);
        return builder.toString();
    }

    public String revealOriginalMessage() {
        return getFlower().revealMessage();
    }

}
