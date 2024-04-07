package com.lomeara;
import org.junit.Test;

import static org.junit.Assert.*;

public class WordleTest {
    
    @Test
    public void testCorrectGuess() {
        String guess = "house";
        String answer = "house";
        String expectedFeedback = "GGGGG";

        String feedback = Wordle.checkGuess(guess, answer);

        assertEquals("Guess should be all G for correct placement", expectedFeedback, feedback);
    }

    @Test
    public void testIncorrectPlacement() {
        String guess = "shave";
        String answer = "heads";
        String expectedFeedback = "YYGXY";

        String feedback = Wordle.checkGuess(guess, answer);

        assertEquals("Guess should have Y for misplaced letters", expectedFeedback, feedback);
    }

    @Test
    public void testIncorrectLetters() {
        String guess = "trout";
        String answer = "beach";
        String expectedFeedback = "XX123XXX";

        String feedback = Wordle.checkGuess(guess, answer);

        assertEquals("Guess should have X for incorrect letters", expectedFeedback, feedback);
    }
}