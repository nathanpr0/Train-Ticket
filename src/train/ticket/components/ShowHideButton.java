/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package train.ticket.components;

import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author user
 */

public class ShowHideButton extends JButton {

    private boolean visible = false;
    private JPasswordField target;

    private Icon eye;
    private Icon eyeOff;

    public ShowHideButton() {

        eye = new ImageIcon(getClass().getResource("/img/show-eye-30.png"));
        eyeOff = new ImageIcon(getClass().getResource("/img/hide-eye-30.png"));

        setIcon(eyeOff);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggle();
            }
        });
    }

    public void setTargetField(JPasswordField field) {
        this.target = field;
    }

    private void toggle() {
        visible = !visible;

        if (visible) {
            setIcon(eye);
            target.setEchoChar((char) 0);
        } else {
            setIcon(eyeOff);
            target.setEchoChar('•');
        }
    }
}
