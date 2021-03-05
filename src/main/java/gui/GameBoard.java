package gui;

import game.Game;
import player.Player;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.Border;

public class GameBoard extends javax.swing.JPanel {
	
	private Game game;
	private Controller controller;
	private final JLabel status;
	private final Player p1, p2;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	GameBoard(Game game, JLabel status, Player p1, Player p2) {
		
		this.game = game;
		this.status = status;
		this.p1 = p1;
		this.p2 = p2;
		
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.setLayout(new GridLayout(10, 10, 5, 5));
        refreshBoard('0',0);
	}

	private JButton renderSquare(int space, int selectedCard) {
		JButton square = new JButton();
		if (space > 100) {
			square.setOpaque(true);
			square.setEnabled(false);
			if (space == Constants.PLAYER_ONE_INT) {
				square.setBackground(p1.getPlayerColor());
			} else {
				square.setBackground(p2.getPlayerColor());
			}
		} else {
			square.setText(String.valueOf(space));
			square.addActionListener(actionEvent -> {
				String num = actionEvent.getActionCommand();
				char player = game.getTurn();
				try {
					game.turnAction(player, Constants.ACTION_PLAY, selectedCard, Integer.parseInt(num));
				} catch (Exception e1) {
					System.out.println("turn action failed.");
					System.out.println(e1.getMessage());
				}
				controller.updateCardPanel();
				status.setText((game.getTurn() == Constants.PLAYER_ONE_PEG ? "Player 1's turn" : "Player 2's turn"));
				status.revalidate();
				status.repaint();
				refreshBoard(game.getWinner(), 100);
			});
			if (space < selectedCard) {
				square.setEnabled(false);
			}
		}
		Border border = BorderFactory.createLineBorder(Color.blue);
		square.setBorder(border);
		square.setFont(new Font("Arial", Font.PLAIN, 40));
		return square;
	}

	private void checkWinner(char winner) {
		if (winner == Constants.NO_WIN) {
			return;
		}
		if (winner == Constants.PLAYER_ONE_WIN) {
			status.setText("Player 1 wins!");
		} else {
			status.setText("Player 2 wins!");
		}
		status.revalidate();
		status.repaint();
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
	
	public void refreshBoard(char winner, int selectedCard) {
		this.removeAll();
		if (winner == '0') {
	    	int[][] board = game.getCurrentBoard();
	        for (int i = 0; i < 10 ; i++) {
	        	for(int j = 0; j < 10; j++) {
					int space = board[i][j];
					JButton square = renderSquare(space, selectedCard);
					this.add(square);
	        	}
	        }
		} else {
			checkWinner(winner);
		}
		this.revalidate();
        this.repaint();
    }
}
