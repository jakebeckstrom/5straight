package game;

import gui.Constants;
import java.util.ArrayList;


/**
 * Combines Deck and Board classes to play full game.
 * @author Jacob Beckstrom
 */
public class Game {

    // Deck and Board objects
    private final Deck gameDeck;
    private final Board gameBoard;

    // Hand for each player
    private final ArrayList<Integer> player1Hand;
    private final ArrayList<Integer> player2Hand;

    /**
     * Toggles the current turn.
     */
    private boolean turn;
    /**
     * Toggles which player has won. Initially set to Constants.NO_WIN.
     */
    private char winner;

    /**
     * Instantiates game variables.
     * Fills Both of the player's hands.
     * Sets turn to Player 1.
     */
    public Game() {
        gameDeck = new Deck();
        gameBoard = new Board();
        player1Hand = new ArrayList<>();
        player2Hand = new ArrayList<>();
        turn = Constants.PLAYER_ONE_TURN;
        winner = Constants.NO_WIN;
        for (int i = 0; i < 4; i++) {
            player1Hand.add(gameDeck.draw());
            player2Hand.add(gameDeck.draw());
        }
    }

    /**
     * Gets current board.
     * @return 2-D array representation of game board.
     */
    public int[][] getCurrentBoard() {
        return gameBoard.getBoard();
    }

    /**
     * Gets the hand for specified player.
     * @param player Specified player.
     * @return Returns ArrayList of players hand.
     */
    public ArrayList<Integer> getHand(char player) {
        if (player == Constants.PLAYER_ONE_PEG) {
            return player1Hand;
        } else {
            return player2Hand;
        }
    }

    /**
     * Gets current turn
     * @return The char representation of the players turn.
     */
    public char getTurn() {
        return (turn ? Constants.PLAYER_ONE_PEG: Constants.PLAYER_TWO_PEG);
    }

    /**
     * Checks if the card is dead.
     * @param card Card to check.
     * @return True if dead, false if not.
     */
    public boolean isDead(int card) {
        return gameBoard.isDead(card);
    }

    private void drawCard(char player) throws Exception {
        if (player == Constants.PLAYER_ONE_PEG) {
            if (player1Hand.size() >=4) throw new Exception("Too many cards!");
            player1Hand.add(gameDeck.draw());
        } else {
            if (player2Hand.size() >= 4) throw new Exception("Too many cards!");
            player2Hand.add(gameDeck.draw());
        }
    }

    /**
     * Performs action of turn on the board and deck.
     * @param player Current player.
     * @param action Draw or Play action.
     * @param card Card chosen (Ignored if action draw).
     * @param space Space chosen (Ignored if action draw).
     * @throws Exception If the space is larger than the card, or if the draw fails.
     */
    public void turnAction(char player, int action, int card, int space) throws Exception {
        if (action == Constants.ACTION_DRAW) {
            try {
                drawCard(player);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw new Exception("Turn action failed");
            }
        } else {
            if (space < card) {
                throw new Exception("Play larger number than card");
            }
            try {
                gameBoard.playPeg(player, space);
            } catch (Exception e) {
                System.out.println("playPeg failed");
                System.out.println(e.getMessage());
                throw new Exception("Invalid Card");
            }
            if (player == Constants.PLAYER_ONE_PEG) {
                player1Hand.remove((Integer) card);
            } else {
                player2Hand.remove((Integer) card);
            }
        }
        turn = !turn;
        if (gameBoard.didWin(Constants.PLAYER_ONE_PEG)) {
            winner = Constants.PLAYER_ONE_WIN;
        } else if (gameBoard.didWin(Constants.PLAYER_TWO_PEG)) {
            winner = Constants.PLAYER_TWO_WIN;
        }
    }

    /**
     * Checks if there is a winner.
     * @return Returns 0 if no winner, and player number if winner.
     */
    public char getWinner() {
        return winner;
    }

    /**
     * Checks if a card is valid
     * @param card Card to check.
     * @return True if valid, false if not.
     */
    public boolean isValid(int card) {
        return gameBoard.isValid(card);
    }
}
