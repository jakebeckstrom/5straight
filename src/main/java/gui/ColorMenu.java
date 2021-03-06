package gui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

import static java.awt.Color.blue;


/**
 * Creates a menu that allows the each user to select their color
 * @author Jacob Beckstrom
 */
public class ColorMenu extends JPanel {

    private Color playerColor;

    HashMap<String, Color> colorMap;

    public ColorMenu() {
        colorMap = new HashMap<>();
        colorMap.put("Red", Color.RED);
        colorMap.put("Blue", Color.BLUE);
        colorMap.put("Green", Color.GREEN);

        this.setLayout(new GridLayout(4,1));
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

    public Color getColor() {
        return playerColor;
    }
}
