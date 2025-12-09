package client.components;

import java.awt.*;
import javax.swing.*;

public class RoundedPanel extends JPanel {

    private int radius = 18;

    public RoundedPanel() {
        setOpaque(false);
    }

    public RoundedPanel(int radius) {
        this.radius = radius;
        setOpaque(false);
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
        revalidate();
        repaint();
    }

    @Override
    public Insets getInsets() {
        // Menambah padding agar layout tidak terdorong saat runtime
        int pad = Math.max(10, radius / 4);
        return new Insets(6, 6, 6, 6);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());

        int arc = radius;

        // memberi margin sedikit agar garis tidak terpotong
        g2.fillRoundRect(1, 1, getWidth() - 2, getHeight() - 2, arc, arc);

        g2.dispose();
        super.paintComponent(g);
    }
}
