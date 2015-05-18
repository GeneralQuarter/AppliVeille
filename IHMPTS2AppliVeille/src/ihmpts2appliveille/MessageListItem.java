/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author x1QG1x
 */
public class MessageListItem extends JPanel implements MouseListener{
    private Messagerie m;
    
    private JLabel objet;
    private JTextArea message;
    private JLabel bottom;
    
    private Font fMessage;
    private Font fObjet;
    private Font fBottom;
    
    public MessageListItem(String objet, String message, String auteur, String date, Messagerie m)
    {
        this.objet = new JLabel(objet);
        this.message = new JTextArea(message);
        this.bottom = new JLabel(auteur + " à " + date);
        this.m = m;
        
        fMessage = new Font("Arial", 0, 10);
        fObjet = new Font("Arial", 0, 18);
        fBottom = new Font("Arial", Font.ITALIC, 10);
        
        this.setBackground(Color.white);
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setPreferredSize(new Dimension(350,100));
        this.setMaximumSize(this.getPreferredSize());
        
        this.message.setPreferredSize(new Dimension(320,40));
        this.message.setColumns(1);
        this.message.setLineWrap(true);
        this.message.setWrapStyleWord(false);
        this.message.setFont(fMessage);
        this.message.setEditable(false);
        this.message.addMouseListener(this);
        
        this.objet.setFont(fObjet);
        
        this.bottom.setFont(fBottom);
        
        this.add(this.objet);
        this.add(this.message);
        this.add(this.bottom);
        
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //Go to Message
        System.out.println(objet.getText());
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
        this.setBackground(new Color(33,150,243));
        this.message.setBackground(new Color(33,150,243));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setBackground(Color.white);
        this.message.setBackground(Color.white);
    }
}
