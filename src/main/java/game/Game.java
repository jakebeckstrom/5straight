package game;

import gui.Constants;
import game.Deck;

import java.util.ArrayList;

public class Game {

    private final Deck gameDeck;
    private final Board gameBoard;

    private final ArrayList<Integer> player1Hand;
    private final ArrayList<Integer> player2Hand;

    private boolean turn;
    private char winner;

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
   
    public char getTurn() {
        return (turn ? Constants.PLAYER_ONE_PEG: Constants.PLAYER_TWO_PEG);
    }

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

    public char getWinner() {
        return winner;
    }

    public boolean isValid(int card) {
        return gameBoard.isValid(card);
    }
}
