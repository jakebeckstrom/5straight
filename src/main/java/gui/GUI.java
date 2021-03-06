package gui;

import game.Game;
import player.*;
import ui.*;
import java.awt.*;
import javax.swing.*;

/**
 * Sets up the Gui pages, toggles between menu and game
 * @author Jacob Beckstrom
 */
public class GUI extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;

	// The two color menu components
    ColorMenu colorMenuP1;
    ColorMenu colorMenuP2;

    // The two player menu components
    PlayerMenu playerMenuP1;
    PlayerMenu playerMenuP2;

    /**
     * Sets up window properties, then renders the menu.
     */
    public GUI(){

        setUpMenu();

        setTitle("5-Straight");
        setSize(1500, 1200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void setUpMenu() {
        this.getContentPane().removeAll();

        JPanel startMenuP1 = new JPanel();
        JPanel startMenuP2 = new JPanel();
        startMenuP1.setLayout(new BorderLayout());
        startMenuP2.setLayout(new BorderLayout());

        colorMenuP1 = new ColorMenu();
        colorMenuP2 = new ColorMenu();

        playerMenuP1 = new PlayerMenu();
        playerMenuP2 = new PlayerMenu();

        startMenuP1.add(new AppLabel("Player 1"), BorderLayout.PAGE_START);
        startMenuP2.add(new AppLabel("Player 2"), BorderLayout.PAGE_START);

        startMenuP1.add(playerMenuP1, BorderLayout.CENTER);
        startMenuP2.add(playerMenuP2, BorderLayout.CENTER);
        startMenuP1.add(colorMenuP1, BorderLayout.PAGE_END);
        startMenuP2.add(colorMenuP2, BorderLayout.PAGE_END);

        startMenuP1.setBorder(BorderFactory.createEmptyBorder(100,200,300,200));
        startMenuP2.setBorder(BorderFactory.createEmptyBorder(100,200,300,200));

        JButton start = new AppButton("Start", 0, 100, 50);
        start.addActionListener(e -> startGame());

        add(new AppLabel("Start Menu", 50), BorderLayout.PAGE_START);
        add(startMenuP1, BorderLayout.LINE_START);
        add(startMenuP2, BorderLayout.LINE_END);
        add(start, BorderLayout.PAGE_END);
        this.revalidate();
        this.repaint();
    }

    private void startGame() {
        this.getContentPane().removeAll();

        JPanel status = new JPanel();
        JLabel stat = new AppLabel("Test", 40);

        JButton exit = new AppButton("Exit");
        exit.addActionListener(e -> setUpMenu());

        status.add(exit, BorderLayout.PAGE_START);
        status.add(stat, BorderLayout.CENTER);

        Game game = new Game();

        Player p1 = playerMenuP1.getType(game, Constants.PLAYER_ONE_PEG);
        p1.setPlayerColor(colorMenuP1.getColor());

        Player p2 = playerMenuP2.getType(game, Constants.PLAYER_TWO_PEG);
        p2.setPlayerColor(colorMenuP2.getColor());

        GameBoard gameBoard = new GameBoard(game, stat, p1, p2);
        Controller controller = new Controller(game, gameBoard, p1, p2);
        gameBoard.setController(controller);

        add(status, BorderLayout.PAGE_START);
        add(gameBoard);
        add(controller, BorderLayout.PAGE_END);

        this.revalidate();
        this.repaint();
    }

    /**
     * Entry point
     * @param args No args
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new GUI().setVisible(true));
    }

}