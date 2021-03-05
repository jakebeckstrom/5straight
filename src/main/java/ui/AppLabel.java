package ui;

import javax.swing.*;
import java.awt.*;

public class AppLabel extends JLabel {

    public AppLabel(String label) {
        this(label, 30);
    }

    public AppLabel(String label, int size) {
        this.setText(label);
        this.setFont(new Font("Arial", Font.PLAIN, size));
    }
}
