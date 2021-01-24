import java.util.HashMap;

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

    private final char[][] play;
    private int dead;
    private final HashMap<Integer, Integer> used;

    Board() {
        this.play = new char[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                play[i][j] = Constants.OPEN_SPACE;
            }
        }
        this.dead = 100;
        used = new HashMap<>();
        used.put(100, 100);
    }

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

    public boolean didWin(char player) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (play[i][j] == player) {
                    if (i < 5 && play[i+1][j] == player) {
                        int inarow = 2;
                        while (play[i+inarow][j] == player) {
                            inarow++;
                            if (inarow == 5) return true;
                        }
                    }
                    if (i < 5 && j < 5 && play[i+1][j+1] == player) {
                        int inarow = 2;
                        while (play[i+inarow][j+inarow] == player) {
                            inarow++;
                            if (inarow == 5) return true;
                        }
                    }
                    if (j < 5 && play[i][j+1] == player) {
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

    public boolean isValid(int card) {
    	int[] coord = {-1, -1};
    	try {
    		coord = find(card);
    	} catch (Exception e) {
    		System.out.println(e.getMessage());
    	}
        return play[coord[0]][coord[1]] == Constants.OPEN_SPACE && card < dead;
    }

    public boolean isDead(int card) {
        return card >= dead;
    }
}