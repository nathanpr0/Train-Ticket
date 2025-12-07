/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package train.ticket.components;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author user
 */

public class RoundedTextField extends JTextField {

    private int radius = 15;
    private Color borderColor = new Color(180, 180, 180);
    private int borderThickness = 1;

    public RoundedTextField() {
        setOpaque(false);
        setBorder(new EmptyBorder(8, 12, 8, 12)); // padding dalam
        setFont(new Font("Inter", Font.PLAIN, 14));
    }

    public int getRadius() { return radius; }
    public void setRadius(int radius) {
        this.radius = radius;
        repaint();
    }

    public Color getBorderColor() { return borderColor; }
    public void setBorderColor(Color color) {
        this.borderColor = color;
        repaint();
    }

    public int getBorderThickness() { return borderThickness; }
    public void setBorderThickness(int t) {
        borderThickness = t;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Background
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

        super.paintComponent(g);
        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setStroke(new BasicStroke(borderThickness));
        g2.setColor(borderColor);

        g2.drawRoundRect(
            borderThickness / 2,
            borderThickness / 2,
            getWidth() - borderThickness,
            getHeight() - borderThickness,
            radius,
            radius
        );

        g2.dispose();
    }
}

