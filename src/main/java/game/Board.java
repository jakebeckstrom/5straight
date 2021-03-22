package game;

import gui.Constants;

import java.util.HashMap;

/**
 * Contains a representation of the 10x10 Game board and methods to interact with it.
 * @author Jacob Beckstrom
 */
public class Board {

    private final int[][] values = {{ 73, 74, 75, 76, 77, 78, 79, 80, 81, 82 },
            { 72, 71, 70, 69, 68, 67, 66, 65, 64, 83 },
            { 43, 42, 13, 12, 11, 10, 25, 26, 63, 84 },
            { 44, 41, 14,  7,  8,  9, 24, 27, 62, 85 },
            { 45, 40, 15,  6,  3,  2, 23, 28, 61, 86 },
            { 46, 39, 16,  5,  4,  1, 22, 29, 60, 87 },
            { 47, 38, 17, 18, 19, 20, 21, 30, 59, 88 },
            { 48, 37, 36, 35, 34, 33, 32, 31, 58, 89 },
            { 49, 50, 51, 52, 53, 54, 55, 56, 57, 90 },
            {  0, 99, 98, 97, 96, 95, 94, 93 ,92, 91 }};

    // 2-D Array used to store the players moves
    private final char[][] play;

     // Contains the dead number.
     // All cards equal to or greater than this number cannot be played.
    private int dead;

    private final HashMap<Integer, Integer> used;

    /**
     * Instantiates Player board, dead card, and used card Map.
     */
    public Board() {
        play = new char[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                play[i][j] = Constants.OPEN_SPACE;
            }
        }
        dead = 100;
        used = new HashMap<>();
        used.put(100, 100);
    }

    /**
     * Finds the coordinates of a given card.
     * @param card card to find, coordinates of the given card.
     * @throws Exception If the card is not on the Board.
     */
    private int[] find(int card) throws Exception {
    	if (card > 99) {
    		throw new Exception("Card out of bounds");
    	}	
        int i, j = 1;
        for (i = 0; i < 10; i++) {
            for (j = 0; j < 10; j++) {
                if (card == values[i][j]) return new int[]{i, j};
            }
        }
        return new int[]{i, j};
    }

    /**
     * Places peg on player board. Updates the used map and dead card.
     * @param player Current players turn.
     * @param card Space chosen to play.
     * @throws Exception If the card is invalid.
     */
    public void playPeg(char player, int card) throws Exception {
    	int[] coord = {-1, -1};
        if (isValid(card)) {
        	try {
        		coord = find(card);
        	} catch (Exception e) {
        		System.out.println(e.getMessage());
        	}
            play[coord[0]][coord[1]] = player;
        	used.put(card, card);
            while (used.containsKey(dead-1)) {
                dead -= 1;
            }
        } else {
        	throw new Exception("Invalid card");
        }
    }

    /**
     * Gets the current game board.
     * @return 2-D array representation of the board.
     */
    public int[][] getBoard() {
        int[][] ret = new int[10][10];
        for(int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (play[i][j] == Constants.OPEN_SPACE ) {
                    ret[i][j] = values[i][j];
                } else if (play[i][j] == Constants.PLAYER_ONE_PEG) {
                    ret[i][j] = Constants.PLAYER_ONE_INT;
                } else {
                    ret[i][j] = Constants.PLAYER_TWO_INT;
                }
            }
        }
        return ret;
    }

    /**
     * Checks to see if the specified player has 5 in a row (wins).
     * @param player Player to check.
     * @return true if the player won, false if not.
     */
    public boolean didWin(char player) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (play[i][j] == player) {
                    if (i <= 5 && play[i+1][j] == player) {
                        int inarow = 2;
                        while (play[i+inarow][j] == player) {
                            inarow++;
                            if (inarow == 5) return true;
                        }
                    }
                    if (i <= 5 && j < 5 && play[i+1][j+1] == player) {
                        int inarow = 2;
                        while (play[i+inarow][j+inarow] == player) {
                            inarow++;
                            if (inarow == 5) return true;
                        }
                    }
                    if (j <= 5 && play[i][j+1] == player) {
                        int inarow = 2;
                        while (play[i][j+inarow] == player) {
                            inarow++;
                            if (inarow == 5) return true;
                        }
                    }
                    if (i > 5 && j < 5 && play[i-1][j+1] == player) {
                        int inarow = 2;
                        while (play[i-inarow][j+inarow] == player) {
                            inarow++;
                            if (inarow == 5) return true;
                        }
                    }

                }
            }
        }
        return false;
    }

    /**
     * Checks to see if the given space is not taken and not a dead space.
     * @param card Space to check.
     * @return True if card is valid false if not.
     */
    public boolean isValid(int card) {
    	int[] coord = {-1, -1};
    	try {
    		coord = find(card);
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    	}

        return play[coord[0]][coord[1]] == Constants.OPEN_SPACE && card < dead;
    }

    /**
     * Checks if the given card is dead.
     * @param card Card to check.
     * @return True if dead, false if not.
     */
    public boolean isDead(int card) {
        return card >= dead;
    }
}