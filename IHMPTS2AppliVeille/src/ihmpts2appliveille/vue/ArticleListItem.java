/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.vue;

import ihmpts2appliveille.controleur.MainControleur;
import ihmpts2appliveille.modele.AppliColor;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

/**
 *
 * @author x1QG1x
 */
public class ArticleListItem extends JPanel{
    private MainControleur mctrl;
    
    private final ImageIcon edit = new ImageIcon("edit.png");
    private final ImageIcon trash = new ImageIcon("trash.png");
    private final ImageIcon visible = new ImageIcon("visible.png");
    
    private int idArticle;
    
    private JLabel theme;
    private JLabel title;
    private JEditorPane content;
    private JLabel auteur;
    private JLabel date;
    private JLabel nbComments;
    
    private Etoile etoiles;
    
    private SpringLayout sp;
    
    private JButton actionButton;
    
    private Font fTheme;
    private Font fTitle;
    private Font fContent;
    private Font fInfo;
    
    public ArticleListItem(String theme, String title, String content, String auteur, String date, int nbComments, int note, int idArticle, int idAuteur, MainControleur mctrl)
    {
        this.mctrl = mctrl;
        this.theme = new JLabel("[" + theme + "]");
        this.title = new JLabel(title);
        this.content = new JEditorPane();
        this.content.setContentType("text/html");
        this.content.setText(content);
        this.auteur = new JLabel(auteur);
        this.date = new JLabel(date);
        this.nbComments = new JLabel(nbComments + " Commentaires");
        this.etoiles = new Etoile(note);
        this.idArticle = idArticle;
        
        // -- Setup This --
        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(500, 200));
        this.setMaximumSize(new Dimension(4000, 200));
        
        // -- Setup Font --
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
        
        switch(this.mctrl.getUtilisateurConnecte().getTypeProfil())
        {
            case ETUDIANT:
                if(this.mctrl.getUtilisateurConnecte().getIdUtilisateur() == idAuteur){
                    actionButton = new JButton(edit);
                    actionButton.setPreferredSize(new Dimension(50,50));
                    actionButton.setBackground(AppliColor.BLUE.getColor());
                }else{
                    actionButton = new JButton();
                    actionButton.setPreferredSize(new Dimension(50,50));
                    actionButton.setVisible(false);
                }
                break;
            case PROFESSEUR:
                actionButton = new JButton(visible);
                actionButton.setPreferredSize(new Dimension(50,50));
                actionButton.setBackground(AppliColor.BLUE.getColor());
                break;
            case ADMINISTRATEUR:
                actionButton = new JButton(trash);
                actionButton.setPreferredSize(new Dimension(50,50));
                actionButton.setBackground(Color.red);
                break;            
        }
        
        this.add(this.title);
        this.add(this.content);
        this.add(this.auteur);
        this.add(this.date);
        this.add(actionButton);
        this.setLayout(sp);
        
        this.content.setEditable(false);
        
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
            this.add(this.etoiles);
            
            sp.putConstraint(SpringLayout.NORTH, this.theme, 20, SpringLayout.NORTH, this);
            sp.putConstraint(SpringLayout.WEST, this.theme, 5, SpringLayout.WEST, this);
            
            sp.putConstraint(SpringLayout.WEST, this.title, 10, SpringLayout.EAST, this.theme);
            
            sp.putConstraint(SpringLayout.WEST, this.nbComments, 20, SpringLayout.EAST, this.date);
            sp.putConstraint(SpringLayout.SOUTH, this.nbComments, -20, SpringLayout.SOUTH, this);
            
            sp.putConstraint(SpringLayout.EAST, this.etoiles, 5, SpringLayout.EAST, this);
            sp.putConstraint(SpringLayout.SOUTH, this.etoiles, -5, SpringLayout.SOUTH, this);
        }else{
            sp.putConstraint(SpringLayout.WEST, this.title, 10, SpringLayout.WEST, this);
        }
        
        sp.putConstraint(SpringLayout.NORTH, this.content, 10, SpringLayout.SOUTH, this.actionButton);
        sp.putConstraint(SpringLayout.WEST, this.content, 5, SpringLayout.WEST, this);
        sp.putConstraint(SpringLayout.SOUTH, this.content, -10, SpringLayout.NORTH, this.auteur);
        sp.putConstraint(SpringLayout.EAST, this.content, -5, SpringLayout.EAST, this);
        
        this.addMouseListener(new EcouteurHover());
        this.content.addMouseListener(new EcouteurHover());
        this.actionButton.addActionListener(new EcouteurActionButton());
        
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    
    private class EcouteurActionButton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            switch(mctrl.getUtilisateurConnecte().getTypeProfil())
            {
                case ADMINISTRATEUR:
                    mctrl.supprimerArticle(idArticle);
                    break;
                case ETUDIANT:
                    mctrl.allerVersModificationArticle(idArticle);
                    break;
            }
        }
        
    }
    
    public class EcouteurHover implements MouseListener
    {

        @Override
        public void mouseClicked(MouseEvent e) {
            mctrl.consulterArticle(idArticle);
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            ArticleListItem.this.setBackground(AppliColor.GRAY.getColor());
            ArticleListItem.this.content.setBackground(AppliColor.GRAY.getColor());
            ArticleListItem.this.etoiles.setBackground(AppliColor.GRAY.getColor());
        }

        @Override
        public void mouseExited(MouseEvent e) {
            ArticleListItem.this.setBackground(Color.WHITE);
            ArticleListItem.this.content.setBackground(Color.WHITE);
            ArticleListItem.this.etoiles.setBackground(Color.WHITE);
        }
    }
}
