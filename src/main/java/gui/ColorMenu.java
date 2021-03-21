package gui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

import static java.awt.Color.blue;


/**
 * Creates a menu that allows the each user to select their color.
 * @author Jacob Beckstrom
 */
public class ColorMenu extends JPanel {

    // Stores the currently Selected color.
    private Color playerColor;


    // Maps the readable color name to the Color class field.
    private final HashMap<String, Color> colorMap;

    /**
     * Instantiates color menu and fills the color map with available colors.
     */
    public ColorMenu() {
        colorMap = new HashMap<>();
        colorMap.put("Red", Color.RED);
        colorMap.put("Blue", Color.BLUE);
        colorMap.put("Green", Color.GREEN);
        colorMap.put("Orange", Color.ORANGE);
        colorMap.put("Yellow", Color.YELLOW);
        colorMap.put("Pink", Color.PINK);
        colorMap.put("Gray", Color.GRAY);

        this.setLayout(new GridLayout(colorMap.size(),1));
        ButtonGroup colorSelector = new ButtonGroup();
        for (String color : colorMap.keySet()) {
            JRadioButton button = createMenuOption(color);
            colorSelector.add(button);
            add(button);
        }

        setBorder(BorderFactory.createLineBorder(blue, 2, true));
    }

    private JRadioButton createMenuOption(String color) {
        JRadioButton button = new JRadioButton(String.valueOf(color));
        button.setFont(new Font("Arial", Font.PLAIN, 30));
        button.addItemListener((i) -> {
            playerColor = colorMap.get(color);
        });
        return button;
    }

    /**
     * Gets the currently selected color.
     * @return Color object of selected color.
     */
    public Color getColor() {
        return playerColor;
    }
}
