/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.vue;

import ihmpts2appliveille.controleur.MainControleur;
import ihmpts2appliveille.modele.AppliColor;
import ihmpts2appliveille.modele.accesbd.entites.Utilisateur;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 *
 * @author x1QG1x
 */
public class NouveauMessageVue extends JPanel{
    private MainControleur mctrl;
    
    private SpringLayout sp;
    
    private List<Utilisateur> utilisateurs;
    
    private Font fLarge;
    private Font fMedium;
    private Font fSmall;
    
    private JScrollPane scrollerListUtilisateur;
    private JScrollPane scrollerListDestinataires;
    private JScrollPane scrollerContent;
    
    private JLabel titreLabel;
    private JLabel utilisateursLabel;
    private JLabel destinatairesLabel;
    private JList utilisateursList;
    private JList destinatairesList;
    private QGButton addOneBouton;
    private QGButton removeOneBouton;
    private QGButton addManyBouton;
    private QGButton removeManyBouton;
    private JLabel objetLabel;
    private JTextField objetField;
    private JLabel contentLabel;
    private JTextArea contentField;
    
    public NouveauMessageVue(List<Utilisateur> utilisateurs, MainControleur mctrl)
    {
        this.utilisateurs = utilisateurs;
        this.mctrl = mctrl;
    }
    
    private void initComponents()
    {
        fLarge = new Font("Arial", 0, 32);
        fMedium = new Font("Arial", 0, 20);
        fSmall = new Font("Arial", 0, 16);
        
        sp = new SpringLayout();
        
        titreLabel = new JLabel("Nouveau Message");
        titreLabel.setFont(fLarge);
        utilisateursLabel = new JLabel("Utilisateurs");
        utilisateursLabel.setFont(fMedium);
        destinatairesLabel = new JLabel("Destinataires");
        destinatairesLabel.setFont(fMedium);
        addOneBouton = new QGButton("Ajouter >", AppliColor.BLUE.getColor(), AppliColor.LIGHT_BLUE.getColor(), Color.white, fSmall);
        addManyBouton = new QGButton("Ajouter\ntous >>", AppliColor.BLUE.getColor(), AppliColor.LIGHT_BLUE.getColor(), Color.white, fSmall);
        removeOneBouton = new QGButton("< Retirer", AppliColor.BLUE.getColor(), AppliColor.LIGHT_BLUE.getColor(), Color.white, fSmall);
        removeManyBouton = new QGButton("<< Retirer\ntous", AppliColor.BLUE.getColor(), AppliColor.LIGHT_BLUE.getColor(), Color.white, fSmall);
        utilisateursList = new JList();
        scrollerListUtilisateur = new JScrollPane(utilisateursList);
        destinatairesList = new JList();
        scrollerListDestinataires = new JScrollPane(destinatairesList);
        objetLabel = new JLabel("Objet");
        objetLabel.setFont(fMedium);
        objetField = new JTextField();
        objetField.setFont(fSmall);
        contentLabel = new JLabel("Message");
        contentLabel.setFont(fMedium);
        contentField = new JTextArea();
        contentField.setFont(fSmall);
        scrollerContent = new JScrollPane(contentField);
        
        this.setLayout(sp);
        
        this.add(titreLabel);
        this.add(utilisateursLabel);
        this.add(destinatairesLabel);
        this.add(addOneBouton);
        this.add(addManyBouton);
        this.add(removeOneBouton);
        this.add(removeManyBouton);
        this.add(objetLabel);
        this.add(objetField);
        this.add(contentLabel);
        this.add(scrollerContent);
        this.add(scrollerListDestinataires);
        this.add(scrollerListUtilisateur);
        
        sp.putConstraint(SpringLayout.NORTH, titreLabel, 10, SpringLayout.NORTH, this);
        sp.putConstraint(SpringLayout.WEST, titreLabel, 10, SpringLayout.WEST, this);
        
        sp.putConstraint(SpringLayout.NORTH, utilisateursLabel, 10, SpringLayout.NORTH, titreLabel);
        sp.putConstraint(SpringLayout.WEST, utilisateursLabel, 10, SpringLayout.WEST, this);
    }
}
