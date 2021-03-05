package ui;

import javax.swing.*;
import java.awt.*;

public class AppButton extends JButton {

    public AppButton(String label) {
        this(label, 100, 50, 20);
    }

    public AppButton(String label, int width, int height) {
        this(label, width, height, 20);
    }

    public AppButton(String label, int width, int height, int size) {
        this.setText(label);
        this.setPreferredSize(new Dimension(width, height));
        this.setFont(new Font("Arial", Font.PLAIN, size));
    }
}