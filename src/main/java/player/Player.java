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

    public abstract void takeTurn(char c, int i, int i1);

    public abstract int[] chooseTurn();

    public abstract boolean isBot();
}
