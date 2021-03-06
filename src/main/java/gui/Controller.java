package gui;

import game.*;
import player.Player;
import ui.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Contains the Buttons used to control the game
 * @author Jacob Beckstrom
 */
public class Controller extends javax.swing.JPanel implements ActionListener {

	// Components
	private JPanel menu;
	private final JPanel player1, player2;
	
	// Buttons
	private JButton showP1, showP2;
  
	//Toggle
	private boolean showCards;
	
	//Game Object
	private Game game;
	private final GameBoard gameBoard;

	//Players
	private final Player p1, p2;

	private static final long serialVersionUID = 1L;

	Controller(Game game, GameBoard gameBoard, Player p1, Player p2) {
		
		this.game = game;
		this.gameBoard = gameBoard;
		this.p1 = p1;
		this.p2 = p2;
		this.setLayout(new BorderLayout());
		
		initializeMenu();
		add(menu, BorderLayout.PAGE_START);
        
        player1 = new JPanel();
        player2 = new JPanel();

		updateBoard();
        updateCardPanel();
    }

    private void initializeMenu() {
		menu = new JPanel();
		menu.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 25));
		JButton reset = new AppButton("Reset", 200, 50);
		reset.addActionListener(this);

		showP1 = new AppButton("Show Cards", 200, 50);
		showP1.addActionListener(this);
		showP1.setEnabled(game.getTurn() == Constants.PLAYER_ONE_PEG);

		showP2 = new AppButton("Show Cards", 200, 50);
		showP2.addActionListener(this);
		showP2.setEnabled(game.getTurn() == Constants.PLAYER_TWO_PEG);

		menu.add(showP1);
		menu.add(reset);
		menu.add(showP2);
	}
	
	public void updateCardPanel() {
		showCards = false;

		showP1.setEnabled(game.getTurn() == Constants.PLAYER_ONE_PEG);
		showP2.setEnabled(game.getTurn() == Constants.PLAYER_TWO_PEG);
		
		refreshHand(player1, p1);
		refreshHand(player2, p2);

		add(player1, BorderLayout.LINE_START);
        add(player2, BorderLayout.LINE_END);
		if (game.getTurn() == Constants.PLAYER_ONE_PEG) {
			if (p1.isBot()) {
				botTurn(p1);
			}
		} else if (game.getTurn() == Constants.PLAYER_TWO_PEG){
			if (p2.isBot()) {
				botTurn(p2);
			}
		}
	}

	private void refreshHand(JPanel cardPanel, Player player) {
		cardPanel.removeAll();
        cardPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20,20));
        if (player.getPlayer() == Constants.PLAYER_ONE_PEG && !showCards) {
			JLabel hide = new AppLabel("Hidden");
			cardPanel.add(hide);
		} else if (player.getPlayer() == Constants.PLAYER_TWO_PEG && !showCards) {
			JLabel hide = new AppLabel("Hidden");
			cardPanel.add(hide);
		} else {
	    	ArrayList<Integer> hand = game.getHand(player.getPlayer());
			for (Integer integer : hand) {
				int c = integer;
				JButton card = new AppButton(String.valueOf(c), 100, 50, 30);
				card.setEnabled(!game.isDead(c));
				card.addActionListener(this);
				cardPanel.add(card);
			}
	    	JButton draw = new AppButton("Draw", 100, 50);
	    	draw.addActionListener(this);
	    	cardPanel.add(draw);
		}
		cardPanel.revalidate();
		cardPanel.repaint();
	}
     
	public void updateBoard() {
		gameBoard.refreshBoard(game.getWinner(), 100);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		String command = actionEvent.getActionCommand();

		switch (command) {
			case "Reset":
				game = null;
				game = new Game();
				p1.setGame(game);
				p2.setGame(game);
				gameBoard.setGame(game);
				showCards = false;
				updateCardPanel();
				updateBoard();
				break;
			case "Show Cards":
				showCards = !showCards;
				JPanel hand = (game.getTurn() == Constants.PLAYER_ONE_PEG ? player1 : player2);
				Player p = (game.getTurn() == Constants.PLAYER_ONE_PEG ? p1 : p2);
				refreshHand(hand, p);
				break;
			case "Draw":
				try {
					game.turnAction(game.getTurn(), Constants.ACTION_DRAW, 0, 0);
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}
				updateCardPanel();
				updateBoard();
				break;
			default:
				int n = Integer.parseInt(command);
				gameBoard.refreshBoard(game.getWinner(), n);
		}
	}

	private void botTurn(Player player) {
		int[] turn = player.chooseTurn();
		for (int i : turn) {
			System.out.println(i);
		}
		player.takeTurn((char)turn[0], turn[1], turn[2]);
		updateCardPanel();
	}
}
