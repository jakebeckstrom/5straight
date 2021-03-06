package player;

import game.*;
import gui.Constants;
import java.util.*;


/**
 * Randomly selects play. Meant to be easily beaten
 * @author Jacob Beckstrom
 */
public class EasyBot extends Player {

    public EasyBot(Game game, char player) {
        super(game, player);
    }

    @Override
    public int[] chooseTurn() {
        int action, card, space;
        ArrayList<Integer> hand = game.getHand(player);
        if (hand.size() < 3) {
            action = Constants.ACTION_DRAW;
            card = 100;
            space = 100;
        } else {
            action = Constants.ACTION_PLAY;
            Random rand = new Random();
            card = hand.get(rand.nextInt(hand.size()));
            space = rand.nextInt(100);
            while (space < card || !game.isValid(space)){
                space = rand.nextInt(100);
            }
        }
        return new int[] {action, card, space};
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
        return true;
    }
}
