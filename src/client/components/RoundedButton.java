package client.components;

import java.awt.*;
import javax.swing.*;

public class RoundedButton extends JButton {

    public RoundedButton() {
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        setFont(new Font("Inter", Font.BOLD, 14));
        setBackground(new Color(52, 152, 219)); // default
        setForeground(Color.WHITE);             // default
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // gunakan background bawaan Swing
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

        super.paintComponent(g);
        g2.dispose();
    }
}
