package com.lomeara;

import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * The Wordle class is a simple word-guessing game.
 */
final class Wordle {
    /**
     * Private member variable to create a word Bank.
     */
    private static ArrayList<String> wordBank;
    /*
     * Public method to access word bank
     */
    public static ArrayList<String> returnWordBank() {
        return Wordle.wordBank;
    }

    /**
     * The Wordle class represents a game where players guess a
     * word by trying different combinations of letters.
     * This class is a utility class and cannot be instantiated.
     */
    private Wordle() {
        throw new AssertionError();
    }

    /**
     * Allowed guess length.
     */
    public static final int GUESS_LENGTH = 5;

    /**
     * Maximum number of guesses a player gets.
     */
    public static final int MAX_ATTEMPTS = 6;

    /**
     * The main method that starts the Wordle game.
     *
     * @param args The command-line arguments.
     */
    public static void main(final String[] args) {
        boolean debugVar = false;
        if (args[0] == "debug") {
            debugVar = true;
        }
        playWordle(debugVar);
    }

    /**
     * Plays the Wordle game.
     *
     * @param debug Set to true to enable debug mode.
     */
    public static void playWordle(final boolean debug) {
        readWordsFromFile("wordlist.txt");
        // Randomly select the answer word
        Random random = new Random();
        String answer = wordBank.get(random.nextInt(wordBank.size()));
        if (debug) {
            answer = "debug";
        } else {
            answer = wordBank.get(random.nextInt(wordBank.size()));
        }

        // User input scanner
        Scanner scanner = new Scanner(System.in);

        // Guess result
        String feedback = "";

        // Game loop (6 attempts)
        for (int attempts = 1; attempts <= MAX_ATTEMPTS; attempts++) {
            System.out.println("Guess #" + attempts + ": ");
            String guess = scanner.nextLine().toLowerCase();

            // Check guess length
            if (guess.length() != GUESS_LENGTH) {
                System.out.println("Invalid guess: Must be 5 letters long.");
                continue;
            }

            // Check guess and provide feedback
            feedback = checkGuess(guess, answer);
            System.out.println(feedback);

            // Win condition
            if (feedback.equals("GGGGG")) {
                System.out.println("Congratulations! You guessed the word in "
                                    + attempts + " tries.");
                break;
            }
        }

        // Lose condition
        if (!feedback.equals("GGGGG")) {
            System.out.println("You ran out of guesses."
                                + "The word was: " + answer);
        }

        scanner.close();
    }

    /**
     * Checks the guess against the answer and provides feedback.
     *
     * @param guess  The user's guess.
     * @param answer The correct answer.
     * @return The feedback string.
     */
    public static String checkGuess(final String guess, final String answer) {

        StringBuilder feedback = new StringBuilder();

        for (int i = 0; i < GUESS_LENGTH; i++) {
            char guessLetter = guess.charAt(i);
            char answerLetter = answer.charAt(i);

            // Green ("G"): Correct letter in the correct spot
            if (guessLetter == answerLetter) {
                feedback.append("G");
            } else if (answer.indexOf(guessLetter) != -1) {
                // Yellow ("Y"): Correct letter in the wrong spot
                feedback.append("Y");
            } else {
                // Grey ("X"): Incorrect letter
                feedback.append("X");
            }
        }

        return feedback.toString();
    }
    public static void readWordsFromFile(final String filename) {
        wordBank = new ArrayList<>(); // Initialize the ArrayList

        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            // Read words from the file and add them to the word bank
            while (scanner.hasNextLine()) {
                wordBank.add(scanner.nextLine().toLowerCase());
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }
}
