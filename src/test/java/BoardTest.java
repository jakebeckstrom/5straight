import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Random;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Array;
import java.util.Arrays;

public class BoardTest {

    private Board testBoard1;
//    private Board testBoard2;
    private Board testBoard3;

    @Before
    public void Setup() {
        testBoard1 = new Board();
//        testBoard2 = new Board();
        testBoard3 = new Board();
    }

    @After
    public void teardown() {
        testBoard1 = null;
//        testBoard2 = null;
        testBoard3 = null;
    }

    @Test
    public void printBoard() {
        int[][] printTest = testBoard1.getBoard();
        for (int i = 0; i < 10; i++) {
            System.out.println(Arrays.toString(printTest[i]));
        }
    }

    @Test
    public void playTest () {
        Board testBoard2 = new Board();
        try {
            testBoard2.playPeg(Constants.PLAYER_ONE_PEG, 5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            testBoard2.playPeg(Constants.PLAYER_TWO_PEG, 6);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int[][] printTest = testBoard2.getBoard();
        for (int i = 0; i < 10; i++) {
            System.out.println(Arrays.toString(printTest[i]));
        }
    }

    @Test
    public void validTest () {
        try {
            testBoard3.playPeg(Constants.PLAYER_ONE_PEG, 5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertFalse("Valid not working for false", testBoard3.isValid(5));
        assertTrue("Valid not working for true", testBoard3.isValid(6));
    }

    @Test
    public void winTest () {
        Board win = null;
        boolean end = true;
        Random rand = new Random();
        int i = 0;
        while (end) {
            win = new Board();
            for (int k = 0; k < 5; k++) {
                int play = rand.nextInt(100);
                while (!win.isValid(play)) {
                    play = rand.nextInt(100);
                }
                try {
                    win.playPeg(Constants.PLAYER_ONE_PEG, play);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (win.didWin(Constants.PLAYER_ONE_PEG)) {
                end = false;
            }
            i++;
        }
        System.out.println(i);
        for(int j = 0 ; j < 10; j++) {
            int[][] pr = win.getBoard();
            System.out.println(Arrays.toString(pr[j]));
        }
    }

    @Test
    public void SpecTest () {
        Board spec = new Board();
        try {
            spec.playPeg(Constants.PLAYER_ONE_PEG, 46);
            spec.playPeg(Constants.PLAYER_ONE_PEG, 45);
            spec.playPeg(Constants.PLAYER_ONE_PEG, 47);
            spec.playPeg(Constants.PLAYER_ONE_PEG, 39);
        }catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue("Pass", spec.didWin(Constants.PLAYER_ONE_PEG) == false);
    }
}
