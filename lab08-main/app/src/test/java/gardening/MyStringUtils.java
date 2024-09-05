package gardening;

public abstract class MyStringUtils {
    public static int numDifferences(String string1, String string2) {
        int lengthDiff = Math.abs(string1.length() - string2.length());
        int smallerLength = Math.min(string1.length(), string2.length());

        int differences = 0;

        for (int i = 0; i < smallerLength; i++) {
            if (string1.charAt(i) != string2.charAt(i))
                differences++;
        }

        differences += lengthDiff;

        return differences;
    }
}
