import java.util.ArrayList;

public class Game {

    private final Deck gameDeck;
    private final Board gameBoard;

    private final ArrayList<Integer> player1Hand;
    private final ArrayList<Integer> player2Hand;

    private boolean turn;

    Game() {
        gameDeck = new Deck();
        gameBoard = new Board();
        player1Hand = new ArrayList<>();
        player2Hand = new ArrayList<>();
        turn = Constants.PLAYER_ONE_TURN;
        for (int i = 0; i < 4; i++) {
            player1Hand.add(gameDeck.draw());
            player2Hand.add(gameDeck.draw());
        }
    }

    public int[][] getCurrentBoard() {
        return gameBoard.getBoard();
    }

    public ArrayList<Integer> getHand(char player) {
        if (player == Constants.PLAYER_ONE_PEG) {
            return player1Hand;
        } else {
            return player2Hand;
        }
    }

    public boolean isHandFull(char player) {
        ArrayList<Integer> hand;
        if (player == Constants.PLAYER_ONE_PEG) {
            hand = player1Hand;
        } else {
            hand = player2Hand;
        }
        for(int card:hand ) {
        	if (card == Constants.OPEN_CARD) {
        		return false;
        	}
        }
        return true;
    }
   
    public boolean getTurn() {
    	return turn;
    }

    public boolean isDead(int card) {
        return gameBoard.isDead(card);
    }
    
    public boolean turnAction(char player, int action, int card, int space) throws Exception {
    	if (action == Constants.ACTION_DRAW) {
    		if (player == Constants.PLAYER_ONE_PEG) {
    			if (player1Hand.size() >= 4) {
    				throw new Exception("Two Many cards!");
    			} else {
    				player1Hand.add(gameDeck.draw());
    			}
    		} else {
    			if (player2Hand.size() >= 4) {
    				throw new Exception("Two Many cards!");
    			} else {
    				player2Hand.add(gameDeck.draw());
    			}
    		}
    	} else {
    	    if (space < card) {
    	        throw new Exception("Play larger number than card");
            }
    		if (player == Constants.PLAYER_ONE_PEG) {
    			try {
    				gameBoard.playPeg(player, space);
    			} catch (Exception e) {
    				System.out.println("playPeg failed");
    				System.out.println(e.getMessage());
    				throw new Exception("Invalid Card");
    			}
    			player1Hand.remove((Integer)card);
    		} else {
    			try {
    				gameBoard.playPeg(player, space);
    			} catch (Exception e) {
    				System.out.println("playPeg failed");
    				System.out.println(e.getMessage());
    				throw new Exception("Invalid Card");
    			}
    			player2Hand.remove((Integer)card);
    		}
    	}
    	turn = !turn;
    	return gameBoard.didWin(player);
    }
    
}
