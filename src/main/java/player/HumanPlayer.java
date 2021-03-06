package player;

import game.*;

/**
 * Does not choose the play. Delegates selection to a button in the UI.
 * @author Jacob Beckstrom
 */
public class HumanPlayer extends Player{

    public HumanPlayer(Game game, char player) {
        super(game, player);
    }

    @Override
    public int[] chooseTurn() {
        return new int[0];
    }

    @Override
    public boolean isBot() {
        return false;
    };
}
