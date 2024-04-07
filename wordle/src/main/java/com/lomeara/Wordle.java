package com.lomeara;
import java.util.Random;
import java.util.Scanner;

public class Wordle {

    public static void main(String[] args) {
        playWordle(false);
    }

    public static void playWordle(boolean debug) {
        // Word bank (replace with a real word list if desired)
        String[] wordBank = {"house", "train", "apple", "beach", "funny"};

        // Randomly select the answer word
        Random random = new Random();
        String answer = wordBank[random.nextInt(wordBank.length)];
        if(debug == true){
            answer = "debug";
        } else {
            answer = wordBank[random.nextInt(wordBank.length)];
        }

        // User input scanner
        Scanner scanner = new Scanner(System.in);
        
        // Guess result
        String feedback = "";

        // Game loop (6 attempts)
        for (int attempts = 1; attempts <= 6; attempts++) {
            System.out.println("Guess #" + attempts + ": ");
            String guess = scanner.nextLine().toLowerCase();

            // Check guess length
            if (guess.length() != 5) {
                System.out.println("Invalid guess: Must be 5 letters long.");
                continue;
            }

            // Check guess and provide feedback
            feedback = checkGuess(guess, answer);
            System.out.println(feedback);

            // Win condition
            if (feedback.equals("GGGGG")) {
                System.out.println("Congratulations! You guessed the word in " + attempts + " tries.");
                break;
            }
        }

        // Lose condition
        if (!feedback.equals("GGGGG")) {
            System.out.println("You ran out of guesses. The word was: " + answer);
        }

        scanner.close();
    }

    public static String checkGuess(String guess, String answer) {
        StringBuilder feedback = new StringBuilder();

        for (int i = 0; i < 5; i++) {
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
}
