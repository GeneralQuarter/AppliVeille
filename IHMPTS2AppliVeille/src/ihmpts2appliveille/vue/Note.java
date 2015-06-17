/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.vue;

import ihmpts2appliveille.modele.AppliColor;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

/**
 * Classe d'affichage de la barre d'aide à la notation
 * @author x1QG1x
 */
public class Note extends JPanel{
    private JLabel noteLabel;
    
    private SpringLayout sp;
    
    private double note;
    
    public Note(double note){
        this.note = note;
        
        initComponents();
    }
    
    private void initComponents(){
        this.setPreferredSize(new Dimension(360, 40));
        this.setBackground(AppliColor.GRAY_BG.getColor());
        sp = new SpringLayout();
        noteLabel = new JLabel();
        noteLabel.setForeground(Color.white);
        noteLabel.setOpaque(true);
        noteLabel.setFont(new Font("Arial", Font.BOLD, 16));
        if(note >= 10)
            noteLabel.setBackground(new Color(76, 175, 80));
        else if(note >= 6)
            noteLabel.setBackground(new Color(255, 152, 0));
        else
            noteLabel.setBackground(new Color(244, 67, 54));
        noteLabel.setPreferredSize(new Dimension((int)((note * this.getPreferredSize().getWidth())/15), 40));
        this.setLayout(sp);
        this.add(noteLabel);
        
        sp.putConstraint(SpringLayout.NORTH, noteLabel, 0, SpringLayout.NORTH, this);
        sp.putConstraint(SpringLayout.WEST, noteLabel, 0, SpringLayout.WEST, this);
    }
}
