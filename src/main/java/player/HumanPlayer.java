package player;

import game.*;

public class HumanPlayer extends Player{

    public HumanPlayer(Game game, char player) {
        super(game, player);
    }

    @Override
    public int[] chooseTurn() {
        return new int[0];
    }

    @Override
    public void takeTurn(char action, int card, int space) {
        try {
            game.turnAction(player, action, card, space);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isBot() {
        return false;
    };
}