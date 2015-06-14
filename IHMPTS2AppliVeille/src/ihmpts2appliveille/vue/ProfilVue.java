/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.vue;

import ihmpts2appliveille.controleur.MainControleur;
import ihmpts2appliveille.modele.AppliColor;
import ihmpts2appliveille.modele.Statut;
import ihmpts2appliveille.modele.accesbd.entites.Theme;
import ihmpts2appliveille.modele.accesbd.entites.Utilisateur;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author x1QG1x
 */
public class ProfilVue extends JPanel{
    private final ImageIcon connected = new ImageIcon("connected.png");
    private final ImageIcon deconnected = new ImageIcon("deconnected.png");
    
    private MainControleur mctrl;
    
    private Utilisateur u;
    private Theme t;
    
    private SpringLayout sp;
    
    private Font fNumbers;
    private Font fTitles;
    private Font f;
    
    private JLabel nom;
    private JLabel statut;
    private JLabel typeProfil;
    private Etoile note;
    private JLabel nbConnections;
    private JLabel nbConnectionsTitle;
    private JLabel themeTitle;
    private JLabel themeIntitule;
    private JTextArea themeDescription;
    private QGButton changeDescription;
    private JLabel nbArticles;
    private JLabel nbArticlesTitle;
    private QGButton voirArticles;
    private JLabel nbCommentaires;
    private JLabel nbCommentairesTitle;
    private JLabel messageConfirmation;
    
    public ProfilVue(Utilisateur u, Theme t, MainControleur mctrl)
    {
        this.mctrl = mctrl;
        this.u = u;
        this.t = t;
        
        initComponents();
    }
    
    private void initComponents()
    {
        sp = new SpringLayout();
        
        fNumbers = new Font("Arial", 0, 40);
        fTitles = new Font("Arial", 0, 18);
        f = new Font("Arial", Font.BOLD, 32);
        
        nom = new JLabel(u.getNom());
        nom.setFont(f);
        if(u.getEtat() == Statut.CONNECTE)
        {
            statut = new JLabel(connected);
            statut.setToolTipText("Connecté");
        }else{
            statut = new JLabel(deconnected);
            statut.setToolTipText("Déconnecté");
        }
        typeProfil = new JLabel(u.getTypeProfil().toString());
        typeProfil.setFont(fTitles);
        note = new Etoile((int) u.getNote());
        note.setToolTipText("" + u.getNote());
        nbConnections = new JLabel("" + u.getNbConn());
        nbConnections.setFont(fNumbers);
        nbConnectionsTitle = new JLabel("Connections");
        nbConnectionsTitle.setFont(fTitles);
        themeTitle = new JLabel("Thème");
        themeTitle.setFont(fTitles);
        if(t != null)
        {
            themeIntitule = new JLabel(t.getIntitule());
            themeIntitule.setFont(fNumbers);
            themeDescription = new JTextArea(t.getDescritpion());
            f = new Font("Arial", 0, 16);
            themeDescription.setFont(f);
            themeDescription.setLineWrap(true);
            themeDescription.setPreferredSize(new Dimension(1000, 200));
            if(u.getIdUtilisateur() == mctrl.getUtilisateurConnecte().getIdUtilisateur())
            {
                changeDescription = new QGButton("Changer", AppliColor.BLUE.getColor(), AppliColor.LIGHT_BLUE.getColor(), Color.white, f);
                changeDescription.addActionListener(new EcouteurBouton());
                themeDescription.setEditable(true);
                themeDescription.setBorder(BorderFactory.createLineBorder(AppliColor.BLUE.getColor()));
                themeDescription.getDocument().addDocumentListener(new EcouteurDocument());
                messageConfirmation = new JLabel("Changements enregistrés");
                messageConfirmation.setForeground(new Color(76,175,80));
                messageConfirmation.setVisible(false);
                messageConfirmation.setFont(f);
            }else{
                changeDescription = null;
                themeDescription.setEditable(false);
                themeDescription.setBorder(null);
                messageConfirmation = null;
            }
        }else{
            themeIntitule = new JLabel("Aucun");
            themeIntitule.setFont(fNumbers);
            themeDescription = null;
        }
        nbArticles = new JLabel("" + u.getNbArticle());
        nbArticles.setFont(fNumbers);
        nbArticlesTitle = new JLabel("Articles");
        nbArticlesTitle.setFont(fTitles);
        if(u.getNbArticle() > 0)
        {
            f = new Font("Arial", 0, 16);
            voirArticles = new QGButton("Voir", AppliColor.BLUE.getColor(), AppliColor.LIGHT_BLUE.getColor(), Color.white, f); 
            voirArticles.addActionListener(new EcouteurBouton());
        }else{
            voirArticles = null;
        }
        nbCommentaires = new JLabel("" + u.getNbComm());
        nbCommentaires.setFont(fNumbers);
        nbCommentairesTitle = new JLabel("Commentaires");
        nbCommentairesTitle.setFont(fTitles);
        
        
        this.setLayout(sp);
        this.setBackground(Color.white);
        
        this.add(nom);
        this.add(statut);
        this.add(typeProfil);
        this.add(note);
        this.add(nbConnections);
        this.add(nbConnectionsTitle);
        this.add(themeTitle);
        this.add(themeIntitule);
        if(themeDescription != null)
            this.add(themeDescription);
        if(changeDescription != null)
            this.add(changeDescription);
        this.add(nbArticles);
        this.add(nbArticlesTitle);
        if(voirArticles != null)
            this.add(voirArticles);
        this.add(nbCommentaires);
        this.add(nbCommentairesTitle); 
        if(messageConfirmation != null)
            this.add(messageConfirmation);
        
        sp.putConstraint(SpringLayout.NORTH, nom, 5, SpringLayout.NORTH, this);
        sp.putConstraint(SpringLayout.WEST, nom, 5, SpringLayout.WEST, this);
        
        sp.putConstraint(SpringLayout.SOUTH, statut, -5, SpringLayout.SOUTH, nom);
        sp.putConstraint(SpringLayout.WEST, statut, 10, SpringLayout.EAST, nom);
        
        sp.putConstraint(SpringLayout.NORTH, typeProfil, 5, SpringLayout.SOUTH, nom);
        sp.putConstraint(SpringLayout.WEST, typeProfil, 10, SpringLayout.WEST, this);
        
        sp.putConstraint(SpringLayout.NORTH, note, 0, SpringLayout.SOUTH, typeProfil);
        sp.putConstraint(SpringLayout.WEST, note, 5, SpringLayout.WEST, this);
        
        sp.putConstraint(SpringLayout.NORTH, nbConnections, 20, SpringLayout.SOUTH, note);
        sp.putConstraint(SpringLayout.WEST, nbConnections, 20, SpringLayout.WEST, this);
        
        sp.putConstraint(SpringLayout.NORTH, nbConnectionsTitle, -8, SpringLayout.SOUTH, nbConnections);
        sp.putConstraint(SpringLayout.WEST, nbConnectionsTitle, 20, SpringLayout.WEST, this);
        
        sp.putConstraint(SpringLayout.NORTH, nbCommentaires, 20, SpringLayout.SOUTH, note);
        sp.putConstraint(SpringLayout.WEST, nbCommentaires, 40, SpringLayout.EAST, nbConnectionsTitle);
        
        sp.putConstraint(SpringLayout.NORTH, nbCommentairesTitle, -8, SpringLayout.SOUTH, nbCommentaires);
        sp.putConstraint(SpringLayout.WEST, nbCommentairesTitle, 40, SpringLayout.EAST, nbConnectionsTitle);
        
        sp.putConstraint(SpringLayout.NORTH, nbArticles, 20, SpringLayout.SOUTH, note);
        sp.putConstraint(SpringLayout.WEST, nbArticles, 40, SpringLayout.EAST, nbCommentairesTitle);
        
        sp.putConstraint(SpringLayout.NORTH, nbArticlesTitle, -8, SpringLayout.SOUTH, nbArticles);
        sp.putConstraint(SpringLayout.WEST, nbArticlesTitle, 40, SpringLayout.EAST, nbCommentairesTitle);
        
        if(voirArticles != null)
        {
            sp.putConstraint(SpringLayout.NORTH, voirArticles, 5, SpringLayout.SOUTH, nbArticlesTitle);
            sp.putConstraint(SpringLayout.WEST, voirArticles, 0, SpringLayout.WEST, nbArticlesTitle);
        }
        
        sp.putConstraint(SpringLayout.NORTH, themeTitle, 40, SpringLayout.SOUTH, nbConnectionsTitle);
        sp.putConstraint(SpringLayout.WEST, themeTitle, 20, SpringLayout.WEST, this);
        
        sp.putConstraint(SpringLayout.NORTH, themeIntitule, -5, SpringLayout.SOUTH, themeTitle);
        sp.putConstraint(SpringLayout.WEST, themeIntitule, 20, SpringLayout.WEST, this);
        
        if(themeDescription != null)
        {
            sp.putConstraint(SpringLayout.NORTH, themeDescription, 5, SpringLayout.SOUTH, themeIntitule);
            sp.putConstraint(SpringLayout.WEST, themeDescription, 20, SpringLayout.WEST, this);
            sp.putConstraint(SpringLayout.EAST, themeDescription, -20, SpringLayout.EAST, this);
            
            if(changeDescription != null)
            {
                sp.putConstraint(SpringLayout.NORTH, changeDescription, 5, SpringLayout.SOUTH, themeDescription);
                sp.putConstraint(SpringLayout.WEST, changeDescription, 20, SpringLayout.WEST, this);
                
                sp.putConstraint(SpringLayout.NORTH, messageConfirmation, 10, SpringLayout.SOUTH, themeDescription);
                sp.putConstraint(SpringLayout.WEST, messageConfirmation, 10, SpringLayout.EAST, changeDescription);
            }
        }
    }
    
    public class EcouteurBouton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            switch(e.getActionCommand())
            {
                case "Voir":
                    mctrl.consulterArticleUtilisateur(u.getIdUtilisateur());
                    break;
                case "Changer":
                    mctrl.modifierDescriptionTheme(t.getIdTheme(), themeDescription.getText());
                    messageConfirmation.setVisible(true);
                    break;
            }
        }
    }
    
    public class EcouteurDocument implements DocumentListener{

        @Override
        public void insertUpdate(DocumentEvent e) {
            messageConfirmation.setVisible(false);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            messageConfirmation.setVisible(false);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            //JTextArea ne lance pas cet evenement.
        }
    }
}
