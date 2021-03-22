import game.Board;
import gui.Constants;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Random;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

public class BoardTest {

    private Board testBoard1;
//    private game.Board testBoard2;
    private Board testBoard3;

    @Before
    public void Setup() {
        testBoard1 = new Board();
//        testBoard2 = new game.Board();
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
//    { 73, 74, 75, 76, 77, 78, 79, 80, 81, 82 },
//    { 72, 71, 70, 69, 68, 67, 66, 65, 64, 83 },
//    { 43, 42, 13, 12, 11, 10, 25, 26, 63, 84 },
//    { 44, 41, 14,  7,  8,  9, 24, 27, 62, 85 },
//    { 45, 40, 15,  6,  3,  2, 23, 28, 61, 86 },
//    { 46, 39, 16,  5,  4,  1, 22, 29, 60, 87 },
//    { 47, 38, 17, 18, 19, 20, 21, 30, 59, 88 },
//    { 48, 37, 36, 35, 34, 33, 32, 31, 58, 89 },
//    { 49, 50, 51, 52, 53, 54, 55, 56, 57, 90 },
//    {  0, 99, 98, 97, 96, 95, 94, 93 ,92, 91 }}

    @Test
    public void winBugTest() {
        Board buggy = new Board();
        try {
            buggy.playPeg(Constants.PLAYER_ONE_PEG, 2);
            buggy.playPeg(Constants.PLAYER_ONE_PEG, 23);
            buggy.playPeg(Constants.PLAYER_ONE_PEG, 28);
            buggy.playPeg(Constants.PLAYER_ONE_PEG, 61);
            buggy.playPeg(Constants.PLAYER_ONE_PEG, 86);
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean test = buggy.didWin(Constants.PLAYER_ONE_PEG);
        assertTrue( "This is the bug" , test);
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
        assertFalse("Pass", spec.didWin(Constants.PLAYER_ONE_PEG));
    }
}
