import game.Game;
import gui.Constants;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class GameTest {

    private Game testgame;

    @Before
    public void setUp() {
        testgame = new Game();
    }

    @After
    public void tearDown() {
        testgame = null;
    }

    @Test
    public void testCards() {
        for (int i = 0; i < 10; i++) {
            testgame = new Game();
            ArrayList<Integer> player1 = testgame.getHand(Constants.PLAYER_ONE_PEG);
            ArrayList<Integer> player2 = testgame.getHand(Constants.PLAYER_TWO_PEG);

            for (int j = 0; j < 4; j++) {
                int card1 = player1.get(j);
                int card2 = player2.get(j);

                assertTrue(" is out of range: " + card1, 0 <= card1 && card1 <= 99);
                assertTrue(" is out of range: " + card2, 0 <= card2 && card2 <= 99);
            }
        }
    }

    @Test
    public void testBoard() {
        testgame = new Game();
        int[][] testBoard = testgame.getCurrentBoard();
        Integer[] noDup = new Integer[100];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int val = testBoard[i][j];
                assertNull(noDup[val]);
                noDup[val] = 1;
            }
        }
    }

    @Test
    public void testGamePlay() {

    }

    @Test
    public void testWin() {

    }
}
