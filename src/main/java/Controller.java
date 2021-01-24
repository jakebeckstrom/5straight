import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Controller extends javax.swing.JPanel {

	// Components
	private JPanel menu;
	private final JPanel player1;
	private final JPanel player2;
	
	// Buttons
	private JButton showP1;
	private JButton showP2;
	private JTextField choiceP1;
	private JTextField choiceP2;
	private JButton playP1;
	private JButton playP2;
  
	//Toggles 
	private boolean showP1Cards;
    private boolean showP2Cards;
	private char winner;
	
	//Game Object
	private Game game;
	private final GameBoard gameBoard;

	private int currentCard;
	private int currentSpace;

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Controller(Game game, GameBoard gameBoard) {
		
		this.game = game;
		this.gameBoard = gameBoard;
		this.winner = '0';
		this.currentCard = 100;
		this.currentSpace = 100;
		this.setLayout(new BorderLayout());
		
		initializeMenu();
		add(menu, BorderLayout.PAGE_START);
        
        player1 = new JPanel();
        player2 = new JPanel();
        
        updateCardPanel();
    }

    private void initializeMenu() {
		menu = new JPanel();
		menu.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 25));
		JButton reset = new JButton("Reset");
		reset.setPreferredSize(new Dimension(200,50));
		reset.setFont(new Font("Arial", Font.PLAIN, 20));
		reset.addActionListener(actionEvent -> {
			game = null;
			game = new Game();
			gameBoard.resetGame(game);
			showP1Cards = false;
			showP2Cards = false;
			winner = '0';
			updateCardPanel();
			updateBoard();
		});

		showP1 = new JButton("Show Cards");
		showP1.setPreferredSize(new Dimension(200,50));
		showP1.setFont(new Font("Arial", Font.PLAIN, 20));
		showP1.addActionListener(e -> {
			showP1Cards = !showP1Cards;
			refreshHand(player1, Constants.PLAYER_ONE_PEG);
		});
		showP1.setEnabled(game.getTurn());

		showP2 = new JButton("Show Cards");
		showP2.setPreferredSize(new Dimension(200,50));
		showP2.setFont(new Font("Arial", Font.PLAIN, 20));
		showP2.addActionListener(e -> {
			showP2Cards = !showP2Cards;
			refreshHand(player2, Constants.PLAYER_TWO_PEG);
		});
		showP2.setEnabled(!game.getTurn());

		choiceP1 = new JTextField(2);
		choiceP1.setFont(new Font("Arial", Font.PLAIN, 40));
		choiceP1.setEnabled(false);
		playP1 = new JButton("Play");
		playP1.setPreferredSize(new Dimension(100,50));
		playP1.setFont(new Font("Arial", Font.PLAIN, 20));
		playP1.setEnabled(false);
		playP1.addActionListener(actionEvent -> {
			char player = (game.getTurn()? Constants.PLAYER_ONE_PEG: Constants.PLAYER_TWO_PEG);
			try {
				if (player == Constants.PLAYER_ONE_PEG) {
					currentSpace = Integer.parseInt(choiceP1.getText());
				} else {
					currentSpace = Integer.parseInt(choiceP2.getText());
				}
			} catch (Exception e) {
				e.printStackTrace();
				currentSpace = currentCard;
			}
			try {
				boolean win = game.turnAction(player,Constants.ACTION_PLAY, currentCard, currentSpace);
				if (player == Constants.PLAYER_ONE_PEG && win) {
					winner = '1';
				} else if (player == Constants.PLAYER_TWO_PEG && win) {
					winner = '2';
				}
			} catch (Exception e1) {
				System.out.println("turn action failed.");
				System.out.println(e1.getMessage());
			}
			choiceP1.setText("");
			choiceP2.setText("");
			currentSpace = 100;
			currentCard = 100;
			updateCardPanel();
			updateBoard();
		});

		choiceP2 = new JTextField(2);
		choiceP2.setFont(new Font("Arial", Font.PLAIN, 40));
		choiceP2.setEnabled(false);
		playP2 = new JButton("Play");
		playP2.setPreferredSize(new Dimension(100,50));
		playP2.setFont(new Font("Arial", Font.PLAIN, 20));
		playP2.setEnabled(false);
		playP2.addActionListener(actionEvent -> {
			char player = (game.getTurn()? Constants.PLAYER_ONE_PEG: Constants.PLAYER_TWO_PEG);
			try {
				if (player == Constants.PLAYER_ONE_PEG) {
					currentSpace = Integer.parseInt(choiceP1.getText());
				} else {
					currentSpace = Integer.parseInt(choiceP2.getText());
				}
			} catch (Exception e) {
				e.printStackTrace();
				currentSpace = currentCard;
			}
			try {
				boolean win = game.turnAction(player,Constants.ACTION_PLAY, currentCard, currentSpace);
				if (player == Constants.PLAYER_ONE_PEG && win) {
					winner = '1';
				} else if (player == Constants.PLAYER_TWO_PEG && win) {
					winner = '2';
				}
			} catch (Exception e1) {
				System.out.println("turn action failed.");
				System.out.println(e1.getMessage());
			}
			choiceP1.setText("");
			choiceP2.setText("");
			currentSpace = 100;
			currentCard = 100;
			updateCardPanel();
			updateBoard();
		});

		menu.add(showP1);
		menu.add(choiceP1);
		menu.add(playP1);
		menu.add(reset);
		menu.add(showP2);
		menu.add(choiceP2);
		menu.add(playP2);
	}
	
	public void updateCardPanel() {
		showP1Cards = false;
		showP2Cards = false;
		
		showP1.setEnabled(game.getTurn());
		showP2.setEnabled(!game.getTurn());
		
		refreshHand(player1, Constants.PLAYER_ONE_PEG);
		refreshHand(player2, Constants.PLAYER_TWO_PEG);
		
		add(player1, BorderLayout.LINE_START);
        add(player2, BorderLayout.LINE_END);
	}

	private void refreshHand(JPanel cardPanel, char player) {
		cardPanel.removeAll();
        cardPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20,20));
		if (player == Constants.PLAYER_ONE_PEG && !showP1Cards) {
			JLabel hide = new JLabel("Hidden");
			hide.setFont(new Font("Arial", Font.PLAIN, 30));
			cardPanel.add(hide);
		} else if (player == Constants.PLAYER_TWO_PEG && !showP2Cards) {
			JLabel hide = new JLabel("Hidden");
			hide.setFont(new Font("Arial", Font.PLAIN, 30));
			cardPanel.add(hide);
		} else {
			System.out.println("Show");
	    	ArrayList<Integer> hand = game.getHand(player);
			for (Integer integer : hand) {
				int c = integer;
				JButton card = new JButton(String.valueOf(c));
				card.setEnabled(!game.isDead(c));
				card.putClientProperty("number", c);
				card.putClientProperty("player", player);
				card.addActionListener(e -> {
					int n = (int) ((JButton) e.getSource()).getClientProperty("number");
					char p = (char) ((JButton) e.getSource()).getClientProperty("player");
					currentCard = n;
					if (p == Constants.PLAYER_ONE_PEG) {
						choiceP1.setEnabled(true);
						playP1.setEnabled(true);
					} else {
						choiceP2.setEnabled(true);
						playP2.setEnabled(true);
					}
					card.setEnabled(false);
				});
				card.setPreferredSize(new Dimension(100, 50));
				card.setFont(new Font("Arial", Font.PLAIN, 30));
				cardPanel.add(card);
			}
	    	JButton draw = new JButton("Draw");
	    	draw.putClientProperty("player", player);
	    	draw.addActionListener(e -> {
				char p = (char)((JButton)e.getSource()).getClientProperty("player");
				try {
					game.turnAction(p, Constants.ACTION_DRAW, 0, 0);
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}
				updateCardPanel();
				updateBoard();
			});
	    	cardPanel.add(draw);
		}
		cardPanel.revalidate();
		cardPanel.repaint();
	}
     
	public void updateBoard() {
		gameBoard.refreshBoard(winner);
	}
}
