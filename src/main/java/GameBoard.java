import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class GameBoard extends javax.swing.JPanel {
	
	private Game game;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	GameBoard(Game game) {
		
		this.game = game;
		
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.setLayout(new GridLayout(10, 10, 5, 5));
        refreshBoard('0');
	}

	public void resetGame(Game game) {
		this.game = null;
		this.game = game;
	}
	
	public void refreshBoard(char winner) {
		this.removeAll();
		if (winner == '0') {
	    	int[][] board = game.getCurrentBoard();
	        for (int i = 0; i < 10 ; i++) {
	        	for(int j = 0; j < 10; j++) {
					int space = board[i][j];
					if (space == 200) {
						JLabel square = new JLabel();
						square.setOpaque(true);
						square.setBackground(new Color(10,10,255));
						this.add(square);
					} else if (space == 300) {
						JLabel square = new JLabel();
						square.setOpaque(true);
						square.setBackground(new Color(255,10,10));
						this.add(square);
					} else {
						JLabel square = new JLabel(String.valueOf(space), SwingConstants.CENTER);
						Border border = BorderFactory.createLineBorder(Color.blue);
						square.setBorder(border);
						square.setFont(new Font("Arial", Font.PLAIN, 40));
						this.add(square);
					}
	        	}
	        }
		} else if (winner == '1') {
			this.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 25));
			JLabel winText = new JLabel("Player 1 Wins!!");
			add(winText);
			
		} else if (winner == '2') {
			this.setLayout(new FlowLayout(FlowLayout.CENTER, 300, 25));
			JLabel winText = new JLabel("Player 2 wins!!");
			add(winText);
		}
		this.revalidate();
        this.repaint();
    }
}
