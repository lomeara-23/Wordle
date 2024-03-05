import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WordleTest {
    
    @Test
    public void testCorrectGuess() {
        String guess = "house";
        String answer = "house";
        String expectedFeedback = "GGGGG";

        String feedback = Wordle.checkGuess(guess, answer);

        assertEquals(expectedFeedback, feedback, "Guess should be all G for correct placement");
    }

    @Test
    public void testIncorrectPlacement() {
        String guess = "shave";
        String answer = "heads";
        String expectedFeedback = "YYGXY";

        String feedback = Wordle.checkGuess(guess, answer);

        assertEquals(expectedFeedback, feedback, "Guess should have Y for misplaced letters");
    }

    @Test
    public void testIncorrectLetters() {
        String guess = "trout";
        String answer = "beach";
        String expectedFeedback = "XXXXX";

        String feedback = Wordle.checkGuess(guess, answer);

        assertEquals(expectedFeedback, feedback, "Guess should have X for incorrect letters");
    }
}