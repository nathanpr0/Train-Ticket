/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package train.ticket;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;

/**
 *
 * @author user
 */

public class RoundedComboBox<E> extends JComboBox<E> {

    public RoundedComboBox() {
        super();
        initUI();
    }

    public RoundedComboBox(E[] items) {
        super(items);
        initUI();
    }

    private void initUI() {

        setOpaque(false);
        setFocusable(false);

        // Menghilangkan padding besar bawaan Nimbus/Windows LAF
        setBorder(BorderFactory.createEmptyBorder(2, 6, 2, 28)); 
        // left 6px, right 28px untuk area tombol panah
        
        setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton arrow = new JButton("▼");
                arrow.setBorder(BorderFactory.createEmptyBorder());
                arrow.setFocusable(false);
                arrow.setContentAreaFilled(false);
                arrow.setOpaque(false);
                arrow.setFont(new Font("Inter", Font.BOLD, 12));
                arrow.setPreferredSize(new Dimension(20, 20));
                return arrow;
            }
        });
    }

    @Override
    public Dimension getPreferredSize() {
        // Force tinggi combobox agar tidak membesar
        Dimension size = super.getPreferredSize();
        size.height = 32;   // Tinggi ideal (boleh kamu kecilkan)
        return size;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Background
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 14, 14);

        // Border
        g2.setColor(new Color(160, 160, 160));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 14, 14);

        g2.dispose();
        super.paintComponent(g);
    }
}