package splitter;
import java.util.Scanner;
public class Splitter {
    public static void main(String[] args) {
        System.out.println("Enter a sentence specified by spaces only:");
        // Add your code
        Scanner in = new Scanner(System.in);
        String sentenceInput = in.nextLine();
        String[] sentence = sentenceInput.split(" ");
        for (String word : sentence) {
            System.out.println(word);
        }

        in.close();
    }
}
