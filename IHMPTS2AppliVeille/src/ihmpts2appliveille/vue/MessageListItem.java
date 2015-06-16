/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.vue;

import ihmpts2appliveille.controleur.MainControleur;
import ihmpts2appliveille.modele.AppliColor;
import ihmpts2appliveille.modele.accesbd.entites.Message;
import ihmpts2appliveille.modele.accesbd.entites.Utilisateur;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

/**
 *
 * @author x1QG1x
 */
public class MessageListItem extends JPanel{
    private MainControleur mctrl;
    
    private JLabel objet;
    private JTextArea message;
    private JLabel auteur;
    private JLabel date;
    
    private Font fMessage;
    private Font fObjet;
    private Font fBottom;
    
    private SpringLayout sp;
    
    private Message m;
    private Utilisateur u;
    
    public MessageListItem(Message m, Utilisateur u, MainControleur mctrl)
    {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy à HH:mm");
        this.mctrl = mctrl;
        this.m = m;
        this.u = u;
        this.objet = new JLabel(m.getIntitule());
        this.auteur = new JLabel(u.getNom());
        this.date = new JLabel(df.format(m.getDateEnvoi()));
        fMessage = new Font("Arial", 0, 10);
        fObjet = new Font("Arial", 0, 18);
        fBottom = new Font("Arial", Font.ITALIC, 10);
        this.setBackground(Color.white);
        sp = new SpringLayout();
        this.setLayout(sp);
        this.message = new JTextArea();
        this.message.setText(m.getContenu());
        this.message.setLineWrap(true);
        this.message.addMouseListener(new EcouteurHover());
        this.message.setEditable(false);
        this.message.setFont(fMessage);
        this.objet.setFont(fObjet);
        this.auteur.setFont(fBottom);
        this.date.setFont(fBottom);
        this.add(this.objet);
        this.add(this.message);
        this.add(this.auteur);
        this.add(this.date);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.addMouseListener(new EcouteurHover());
        
        sp.putConstraint(SpringLayout.NORTH, this.objet, 5, SpringLayout.NORTH, this);
        sp.putConstraint(SpringLayout.WEST, this.objet, 5, SpringLayout.WEST, this);
        
        sp.putConstraint(SpringLayout.NORTH, this.message, 5, SpringLayout.SOUTH, this.objet);
        sp.putConstraint(SpringLayout.WEST, this.message, 5, SpringLayout.WEST, this);
        sp.putConstraint(SpringLayout.EAST, this.message, -5, SpringLayout.EAST, this);
        sp.putConstraint(SpringLayout.SOUTH, this.message, -5, SpringLayout.NORTH, this.auteur);
        
        sp.putConstraint(SpringLayout.WEST, this.auteur, 5, SpringLayout.WEST, this);
        sp.putConstraint(SpringLayout.SOUTH, this.auteur, -5, SpringLayout.SOUTH, this);
        
        sp.putConstraint(SpringLayout.EAST, this.date, -5, SpringLayout.EAST, this);
        sp.putConstraint(SpringLayout.SOUTH, this.date, -5, SpringLayout.SOUTH, this);
        
        this.setPreferredSize(new Dimension(100, 100));
        this.setMaximumSize(new Dimension(4000, 100));
    }
    
    public class EcouteurHover implements MouseListener
    {

        @Override
        public void mouseClicked(MouseEvent e) {
            mctrl.consulterMessage(m.getIdMessage());
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            MessageListItem.this.setBackground(AppliColor.LIGHT_BLUE.getColor());
            MessageListItem.this.message.setBackground(AppliColor.LIGHT_BLUE.getColor());
        }

        @Override
        public void mouseExited(MouseEvent e) {
            MessageListItem.this.setBackground(Color.white);
            MessageListItem.this.message.setBackground(Color.white);
        }
        
    }
}
