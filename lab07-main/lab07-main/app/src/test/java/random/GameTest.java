package random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

// import org.junit.jupiter.api.Test;
// import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {
    @Test
    public void test1() {
        Game game = new Game(4);
        assertFalse(game.battle());
        assertFalse(game.battle());
        assertTrue(game.battle());
        assertFalse(game.battle());
        assertFalse(game.battle());
        assertTrue(game.battle());
        assertTrue(game.battle());
        assertTrue(game.battle());
    }

    @Test
    public void test2() {
        Game game = new Game(-4);
        assertTrue(game.battle());
        assertTrue(game.battle());
        assertFalse(game.battle());
        assertTrue(game.battle());
        assertTrue(game.battle());
        assertFalse(game.battle());
        assertTrue(game.battle());
        assertTrue(game.battle());
    }

    @Test
    public void test3() {
        Game game = new Game(0);
        assertFalse(game.battle());
        assertTrue(game.battle());
        assertTrue(game.battle());
        assertTrue(game.battle());
        assertTrue(game.battle());
        assertFalse(game.battle());
        assertFalse(game.battle());
        assertFalse(game.battle());
    }

    @Test
    public void testBattleDefaultConstructor() {
        Game game = new Game(); // Uses current time as seed
        int wins = 0;
        int losses = 0;
        int numTests = 1000;

        for (int i = 0; i < numTests; i++) {
            if (game.battle()) {
                wins++;
            } else {
                losses++;
            }
        }

        // Verify that the win/loss ratio is close to 50%
        assertTrue(Math.abs(wins - losses) < numTests * 0.1);
    }

}
