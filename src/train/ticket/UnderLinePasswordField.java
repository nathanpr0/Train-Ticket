/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package train.ticket;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

/**
 *
 * @author user
 */

public class UnderLinePasswordField extends JPasswordField {

    public UnderLinePasswordField() {
        setBorder(new UnderlineBorder());
        setFont(new Font("Inter", Font.PLAIN, 20));
        setEchoChar('•');
    }

    private static class UnderlineBorder implements Border {

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(Color.GRAY);
            g.fillRect(x, y + height - 2, width, 2);
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(0, 0, 8, 0);
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }
    }
}
