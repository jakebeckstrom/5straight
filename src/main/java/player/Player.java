package player;

import game.Game;
import java.awt.*;

public abstract class Player {
    private Color color;
    protected Game game;
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
