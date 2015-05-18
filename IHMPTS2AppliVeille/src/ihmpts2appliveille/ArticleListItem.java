/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

/**
 *
 * @author x1QG1x
 */
public class ArticleListItem extends JPanel implements MouseListener{
    private ActualiteArticleView aaw;
    
    private JLabel theme;
    private JLabel title;
    private JTextArea content;
    private JLabel auteur;
    private JLabel date;
    private JLabel nbComments;
    
    private SpringLayout sp;
    
    private JButton actionButton;
    
    private Font fTheme;
    private Font fTitle;
    private Font fContent;
    private Font fInfo;
    
    public ArticleListItem(String theme, String title, String content, String auteur, String date, int nbComments, ActualiteArticleView aaw)
    {
        this.aaw = aaw;
        this.theme = new JLabel("[" + theme + "]");
        this.title = new JLabel(title);
        this.content = new JTextArea(content);
        this.auteur = new JLabel(auteur);
        this.date = new JLabel("Publié le " + date);
        this.nbComments = new JLabel(nbComments + " Commentaires");
        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(500, 200));
        this.setMaximumSize(new Dimension(4000, 200));
        
        fTheme = new Font("Arial", 0, 18);
        fTitle = new Font("Arial", Font.BOLD, 22);
        fContent = new Font("Arial", 0, 16);
        fInfo = new Font("Arial", Font.ITALIC, 12);
        
        this.theme.setFont(fTheme);
        this.title.setFont(fTitle);
        this.content.setFont(fContent);
        this.auteur.setFont(fInfo);
        this.date.setFont(fInfo);
        this.nbComments.setFont(fInfo);
        
        sp = new SpringLayout();
        
        actionButton = new JButton("A");
        actionButton.setPreferredSize(new Dimension(50,50));
        
        this.add(this.title);
        this.add(this.content);
        this.add(this.auteur);
        this.add(this.date);
        this.add(actionButton);
        this.setLayout(sp);
        
        this.content.setLineWrap(true);
        this.content.setEditable(false);
        this.content.setWrapStyleWord(true);
        
        sp.putConstraint(SpringLayout.WEST, this.auteur, 5, SpringLayout.WEST, this);
        sp.putConstraint(SpringLayout.SOUTH, this.auteur, -20, SpringLayout.SOUTH, this);
        
        sp.putConstraint(SpringLayout.WEST, this.date, 20, SpringLayout.EAST, this.auteur);
        sp.putConstraint(SpringLayout.SOUTH, this.date, -20, SpringLayout.SOUTH, this);
        
        sp.putConstraint(SpringLayout.NORTH, this.title, 20, SpringLayout.NORTH, this);
        
        sp.putConstraint(SpringLayout.NORTH, actionButton, 5, SpringLayout.NORTH, this);
        sp.putConstraint(SpringLayout.EAST, actionButton, -5, SpringLayout.EAST, this);
        
        if(!theme.isEmpty()) // Si l'item est un article
        {
            this.add(this.theme);
            this.add(this.nbComments);
            
            sp.putConstraint(SpringLayout.NORTH, this.theme, 20, SpringLayout.NORTH, this);
            sp.putConstraint(SpringLayout.WEST, this.theme, 5, SpringLayout.WEST, this);
            
            sp.putConstraint(SpringLayout.WEST, this.title, 10, SpringLayout.EAST, this.theme);
            
            sp.putConstraint(SpringLayout.WEST, this.nbComments, 20, SpringLayout.EAST, this.date);
            sp.putConstraint(SpringLayout.SOUTH, this.nbComments, -20, SpringLayout.SOUTH, this);
        }else{
            sp.putConstraint(SpringLayout.WEST, this.title, 10, SpringLayout.WEST, this);
        }
        
        sp.putConstraint(SpringLayout.NORTH, this.content, 10, SpringLayout.SOUTH, this.actionButton);
        sp.putConstraint(SpringLayout.WEST, this.content, 5, SpringLayout.WEST, this);
        sp.putConstraint(SpringLayout.SOUTH, this.content, -10, SpringLayout.NORTH, this.auteur);
        sp.putConstraint(SpringLayout.EAST, this.content, -5, SpringLayout.EAST, this);
        
        this.addMouseListener(this);
        this.content.addMouseListener(this);
        this.actionButton.addMouseListener(this);
        
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Go to article
        // Go to theme
        // Go to profil
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //Useless
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //Useless
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setBackground(Color.GRAY);
        this.content.setBackground(Color.GRAY);
        this.actionButton.setBackground(Color.GRAY);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setBackground(Color.WHITE);
        this.content.setBackground(Color.WHITE);
        this.actionButton.setBackground(Color.WHITE);
    }
}
