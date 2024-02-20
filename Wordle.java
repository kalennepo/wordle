import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.nio.charset.*;

public class Wordle {
    private String[] wordleGrid;
    public String destination;
    private int currentTry;

    public Wordle() {
        currentTry = 0;
        destination = getWord();
        wordleGrid = new String[6];
    }

    public String getWord() {
        Path file = Paths.get("WORDS.txt");
        Charset charset = StandardCharsets.UTF_8;
        Random random = new Random();
        try {
            List<String> list = Files.readAllLines(file, charset);
            String word = list.get(random.nextInt(list.size()));
            return word;
        } catch (IOException e) {
            System.out.format("I/O error: %s%n", e);
            return null;
        }
    }

    public int processAttempt(String guess) {
        if (guess.equals(destination)) {
            return 1;
        } else if (currentTry >= 6) {
            return 0;
        } else {
            guess = colorResult(guess);
            addCurrentAttemptToGrid(guess);
            displayGrid();
            return -1;
        }
    }

    public String colorResult(String guess) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < 5; i++) {
            char guessChar = guess.charAt(i);
            char destChar = destination.charAt(i);

            if (guessChar == destChar) {
                result.append("\u001B[42m" + guessChar + "\u001B[0m"); // GREEN
            } else if (destination.contains(String.valueOf(guessChar))) {
                result.append("\u001B[43m" + guessChar + "\u001B[0m"); // YELLOW
            } else {
                result.append("\u001B[47m" + guessChar + "\u001B[0m"); // WHITE
            }
        }

        return result.toString();
    }

    public void addCurrentAttemptToGrid(String guess) {
        wordleGrid[currentTry] = guess;
        currentTry++;
    }

    public void displayGrid() {
        for (int i = 0; i < currentTry; i++) {
            System.out.print(wordleGrid[i]);
            // for (char c : wordleGrid[i]) {
            // System.out.print(c);
            // }
            System.out.println("");
        }

        // for (char c : wordleGrid[currentTry - 1]) {
        // System.out.print(c);
        // }
        // System.out.print("\n");
    }

    public String getDestination() {
        return destination;
    }

}

// ANSI CODES

// GREEN = "\u001B[42m" + TEXT + "\u001B[0m"
// YELLOW = "\u001B[43m" + TEXT + "\u001B[0m"
// WHITE = "\u001B[47m" + TEXT + "\u001B[0m"