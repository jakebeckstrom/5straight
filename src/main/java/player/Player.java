package player;

import game.Game;
import java.awt.*;

/**
 * Parent class to all player types. Implements all methods besides chooseTurn() and takeTurn()
 * @author Jacob Beckstrom
 */
public abstract class Player {

    /**
     * The players selected color.
     */
    private Color color;

    /**
     * Shared Game object.
     */
    protected Game game;

    /**
     * Specifies between Player 1 and Player2.
     */
    protected char player;

    /**
     * Sets the game variables.
     * @param game Shared game object.
     * @param player Player 1 or player2
     */
    public Player(Game game, char player) {
        this.player = player;
        this.game = game;
    }

    public void setPlayer(char player) {
        this.player = player;
    }

    public char getPlayer() {
        return player;
    };

    public void setGame(Game game) {
        this.game = game;
    }

    public void setPlayerColor(Color color) {
        this.color = color;
    }

    public Color getPlayerColor() {
        return color;
    }

    /**
     * Performs action on game.
     * @param action Selected action
     * @param card selected card (Ignored if action draw)
     * @param space Selected space (Ignored if action draw)
     */
    public void takeTurn(char action, int card, int space) {
        try {
            game.turnAction(player, action, card, space);
        } catch (Exception e) {
            e.printStackTrace();
        }
    };

    /**
     * Logic to decide which action to take
     * @return array containing [action, card, space]
     */
    public abstract int[] chooseTurn();

    /**
     * Checks if player is a bot or human.
     * @return true if bot
     */
    public abstract boolean isBot();
}
