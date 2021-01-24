
import java.awt.*;

import javax.swing.*;

public class GUI extends javax.swing.JFrame{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    
    public GUI(){
        Game game = new Game();
        GameBoard gameBoard = new GameBoard(game);
        Controller controller = new Controller(game, gameBoard);
        
        add(gameBoard);
        add(controller, BorderLayout.PAGE_END);
        
        
        setTitle("5-Straight");
        setSize(1500, 1200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new GUI().setVisible(true));
    }
}