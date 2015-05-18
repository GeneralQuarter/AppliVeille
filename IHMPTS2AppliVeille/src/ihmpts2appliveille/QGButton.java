/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

/**
 *
 * @author x1QG1x
 */
public class QGButton extends JButton implements MouseListener{
    private Color primaryColor;
    private Color hoverColor;
    private Color textColor;
    private Font f;
    
    public QGButton(String text, Color primary, Color hover, Color textColor, Font f)
    {
        super(text);
        this.primaryColor = primary;
        this.hoverColor = hover;
        this.textColor = textColor;
        this.f = f;
        this.setContentAreaFilled(false);
        this.setOpaque(true);
        this.setBackground(primaryColor);
        this.setForeground(textColor);
        this.setFont(this.f);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.addMouseListener(this);
    }
    

    @Override
    public void mouseClicked(MouseEvent e) {
        super.actionListener.actionPerformed(new ActionEvent(this, 0, "CONNEXION"));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Useless
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Useless
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setBackground(hoverColor);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setBackground(primaryColor);
    }   
}
