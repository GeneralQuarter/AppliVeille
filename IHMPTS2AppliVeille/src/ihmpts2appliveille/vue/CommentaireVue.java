/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.vue;

import ihmpts2appliveille.controleur.MainControleur;
import ihmpts2appliveille.modele.AppliColor;
import ihmpts2appliveille.modele.Droits;
import ihmpts2appliveille.modele.accesbd.entites.Commentaire;
import ihmpts2appliveille.modele.accesbd.entites.Utilisateur;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 * Classe d'affichage d'un commentaire 
 * @author x1QG1x
 */
public class CommentaireVue extends JPanel{
    private MainControleur mctrl;
    
    private Font fTitre;
    private Font fSmall;
    
    private Commentaire c;
    private Utilisateur u;
    
    private JLabel titre;
    private JLabel date;
    private JLabel auteur;
    private JTextArea contenu;
    private JScrollPane contenuScroller;
    private QGButton actionBouton;
    
    private JTextField border;
    
    private SpringLayout sp;
    
    public CommentaireVue(Commentaire c, Utilisateur u, MainControleur mctrl)
    {
        this.mctrl = mctrl;
        this.c = c;
        this.u = u;

        initComponents();
    }
    
    private void initComponents()
    {
        fTitre = new Font("Arial",0,24);
        fSmall = new Font("Arial",0,16);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy à HH:mm");
        sp = new SpringLayout();
        border = new JTextField();
        
        this.setPreferredSize(new Dimension(1000,200));
        this.setBackground(Color.white);
        
        titre = new JLabel(c.getIntitule());
        titre.setFont(fTitre);
        if(c.getDateModif() != null)
            date = new JLabel("modifié le " + df.format(c.getDateModif()));
        else
            date = new JLabel("publié le " + df.format(c.getDatePubli()));
        date.setFont(fSmall);
        auteur = new JLabel(u.getNom());
        auteur.setFont(fSmall);
        contenu = new JTextArea(c.getContenu());
        contenuScroller = new JScrollPane(contenu);
        contenu.setFont(fSmall);
        contenu.setEditable(false);
        contenuScroller.setBorder(null);
        if(u.getIdUtilisateur() != mctrl.getUtilisateurConnecte().getIdUtilisateur())
        {
            if(mctrl.getUtilisateurConnecte().getTypeProfil() == Droits.PROFESSEUR)
            {
                actionBouton = new QGButton("Cacher", AppliColor.BLUE.getColor(), AppliColor.LIGHT_BLUE.getColor(), Color.white, fSmall);
                actionBouton.addActionListener(new EcouteurBouton());
            }else if(mctrl.getUtilisateurConnecte().getTypeProfil() == Droits.ADMINISTRATEUR){
                actionBouton = new QGButton("Supprimer", AppliColor.BLUE.getColor(), AppliColor.LIGHT_BLUE.getColor(), Color.white, fSmall);
                actionBouton.addActionListener(new EcouteurBouton());
            }else{
                actionBouton = null;
            }
        }else{
            actionBouton = new QGButton("Editer", AppliColor.BLUE.getColor(), AppliColor.LIGHT_BLUE.getColor(), Color.white, fSmall);
            actionBouton.addActionListener(new EcouteurBouton());
            contenuScroller.setBorder(border.getBorder());
            contenu.setEditable(true);
        }
        
        this.setLayout(sp);
        
        this.add(titre);
        this.add(date);
        this.add(auteur);
        this.add(contenuScroller);
        if(actionBouton != null)
            this.add(actionBouton);
        
        sp.putConstraint(SpringLayout.NORTH, titre, 5, SpringLayout.NORTH, this);
        sp.putConstraint(SpringLayout.WEST, titre, 5, SpringLayout.WEST, this);
        
        sp.putConstraint(SpringLayout.NORTH, date, 10, SpringLayout.NORTH, this);
        sp.putConstraint(SpringLayout.EAST, date, -5, SpringLayout.EAST, this);
        
        sp.putConstraint(SpringLayout.SOUTH, auteur, -5, SpringLayout.SOUTH, this);
        sp.putConstraint(SpringLayout.WEST, auteur, 5, SpringLayout.WEST, this);
        
        sp.putConstraint(SpringLayout.NORTH, contenuScroller, 5, SpringLayout.SOUTH, titre);
        sp.putConstraint(SpringLayout.WEST, contenuScroller, 5, SpringLayout.WEST, this);
        sp.putConstraint(SpringLayout.EAST, contenuScroller, -5, SpringLayout.EAST, this);
        sp.putConstraint(SpringLayout.SOUTH, contenuScroller, -13, SpringLayout.NORTH, auteur);
        
        if(actionBouton != null)
        {
            sp.putConstraint(SpringLayout.NORTH, actionBouton, 5, SpringLayout.SOUTH, contenuScroller);
            sp.putConstraint(SpringLayout.EAST, actionBouton, -5, SpringLayout.EAST, this);
        }
    }
    
    private class EcouteurBouton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            switch(e.getActionCommand())
            {
                case "Editer":
                    mctrl.modifierCommentaire(CommentaireVue.this.c.getIdCommentaire(), CommentaireVue.this.c.getIdArticle(), contenu.getText());
                    break;
                case "Supprimer":
                    mctrl.supprimerCommentaire(CommentaireVue.this.c.getIdCommentaire(), CommentaireVue.this.c.getIdArticle(), CommentaireVue.this.c.getIdAuteur());
                    break;
            }
        }
        
    }
}
