import java.util.*;

public class Driver {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Wordle w = new Wordle();
        System.out.print("\nEnter a 5-character string: ");

        int attempts = 0;
        while (attempts < 6) {
            String guess = scan.nextLine();
            int result = w.processAttempt(guess);

            if (result == 1) {
                System.out.println("\nCongratulations! You solved the Wordle in " + (attempts + 1) + " attempts.");
                break;
            } else if (result == -1 && attempts != 5) {
                System.out.print("Not quite! " + (6 - (attempts + 1)) + " attempts left. Guess again: ");
            }
            attempts++;
        }
        if (attempts == 6) {
            System.out.println("You used all your attempts.");
        }

        System.out.println("The word was " + "\u001B[42m" + w.getDestination() + "\u001B[0m" + ".");

        System.out.println("Play again? Y/N: ");
        if (scan.nextLine().equals("Y")) {
            main(args);
        }

        scan.close();
    }
}
