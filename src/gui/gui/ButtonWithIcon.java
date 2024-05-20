package gui;

import javax.swing.*;

public class ButtonWithIcon {
    private final JButton button;
    private final ImageIcon icon;

    public ButtonWithIcon(JButton button, ImageIcon icon) {
        this.button = button;
        this.icon = icon;
    }

    public JButton getButton() {
        return button;
    }

    public ImageIcon getIcon() {
        return icon;
    }
}
