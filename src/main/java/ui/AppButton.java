package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Provides simplified, universal implementation of JButton
 * @author Jacob Beckstrom
 */
public class AppButton extends JButton {

    public AppButton(String label) {
        this(label, 100, 50);
    }

    public AppButton(String label, int width, int height) {
        this(label, width, height, 20);
    }

    public AppButton(String label, int width, int height, ActionListener listener) {
        this(label, width, height, 20, listener);
    }

    public AppButton(String label, int width, int height, int size) {
        this(label, width, height, size, (i)->{});
    }

    public AppButton(String label, int width, int height, int size, ActionListener listener) {
        this.setText(label);
        this.setPreferredSize(new Dimension(width, height));
        this.setFont(new Font("Arial", Font.PLAIN, size));
        this.addActionListener(listener);
    }
}