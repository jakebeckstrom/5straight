package gui;

import game.Game;
import player.*;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

/**
 * Creates menu that allows the user to choose their type of player (Human vs. Bot).
 * @author Jacob Beckstrom
 */
public class PlayerMenu extends JPanel {

    /**
     * Stores currently selected type.
     */
    private char type;

    /**
     * Maps readable player name to Player type.
     */
    HashMap<String, Character> playerMap;

    /**
     * Instantiates Map of player options, renders list of selector buttons.
     */
    public PlayerMenu() {
        playerMap = new HashMap<>();
        playerMap.put("Player", Constants.HUMAN);
        playerMap.put("Easy Bot", Constants.EASY_BOT);

        ButtonGroup playerSelector = new ButtonGroup();
        setLayout(new GridLayout(playerMap.size(),1));

        for (String player : playerMap.keySet()) {
            JRadioButton button = createMenuOption(player);
            playerSelector.add(button);
            add(button);
        }
    }

    private JRadioButton createMenuOption(String player) {
        JRadioButton button = new JRadioButton(player);
        button.addItemListener((i) -> {
            type = playerMap.get(player);
        });
        button.setFont(new Font("Arial", Font.PLAIN, 30));
        return button;
    }

    /**
     * Gets the Player object of the selected player type.
     * @param game Shared game object - to pass to Player
     * @param player Player 1 or Player 2.
     * @return Player object of selected type.
     */
    public Player getType(Game game, char player) {
        // To add more player types
        switch (type) {
            case Constants.EASY_BOT:
                return new EasyBot(game, player);
            default:
                return new HumanPlayer(game, player);
        }
    }

}
