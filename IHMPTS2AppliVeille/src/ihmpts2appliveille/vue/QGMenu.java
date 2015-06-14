/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.vue;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JMenu;

/**
 * Classe héritant de JMenu : Affichage d'une barre horizontale et des menus déroulants personnalisés.
 * @author x1QG1x
 */
public class QGMenu extends JMenu implements MouseListener{
    private Color primaryColor;
    private Color hoverColor;
    private Color textColor;
    private Font f;
    
    /**
     * Constructeur du menu
     * @param text texte s'affichant dans le menu
     * @param primaryColor couleur principale du menu
     * @param hoverColor couleur secondaire au passage du curseur
     * @param textColor couleur du texte affiché dans le menu
     * @param f police d'écriture du texte s'affichant dans le menu
     */
    public QGMenu(String text, Color primaryColor, Color hoverColor, Color textColor, Font f)
    {
        super(text);
        this.primaryColor = primaryColor;
        this.hoverColor = hoverColor;
        this.textColor = textColor;
        this.setOpaque(true);
        this.setMargin(new Insets(0,10,0,10));
        this.setBackground(this.primaryColor);
        this.setForeground(this.textColor);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.setFont(f);
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * Applique le changement de couleur du menu lors du passage du curseur sur celui-ci
     * @param e 
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        this.setBackground(this.hoverColor);
    }

    /**
     * Applique le changement de couleur du menu (retour à la couleur initiale) lorsque le curseur ne survole plus celui-ci
     * @param e 
     */
    @Override
    public void mouseExited(MouseEvent e) {
        this.setBackground(this.primaryColor);
        this.setSelected(false);
    }
}
