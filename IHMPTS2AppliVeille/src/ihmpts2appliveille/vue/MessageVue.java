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
import java.awt.Font;
import java.awt.Insets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;

/**
 *
 * @author x1QG1x
 */
public class MessageVue extends JPanel{
    private final ImageIcon trash = new ImageIcon("trash.png");
    private MainControleur mctrl;
    
    private SpringLayout sp;
    
    private Font fTitre;
    private Font fMedium;
    private Font fSmall;
    
    private JLabel objet;
    private JLabel auteur;
    private DateFormat df;
    private JLabel date;
    private JButton supprimerMessage;
    private JTextArea contenu;
    private JScrollPane contenuScoller;
    private QGButton repondreMessage;
    
    private Message m;
    private Utilisateur a;
    
    public MessageVue(Message m, Utilisateur a, MainControleur mctrl)
    {
        this.mctrl = mctrl;
        this.m = m;
        this.a = a;
        
        initComponents();
    }
    
    private void initComponents()
    {
        fTitre = new Font("Arial", 0, 32);
        fMedium = new Font("Arial", 0, 20);
        fSmall = new Font("Arial", 0, 16);
        df = new SimpleDateFormat("dd/MM/yyyy à HH:mm");
        sp = new SpringLayout();
        
        objet = new JLabel(m.getIntitule());
        objet.setFont(fTitre);
        auteur = new JLabel("De " + a.getNom());
        auteur.setFont(fMedium);
        date = new JLabel("Envoyé le " + df.format(m.getDateEnvoi()));
        date.setFont(fMedium);
        supprimerMessage = new JButton(trash);
        supprimerMessage.setSize(40, 40);
        supprimerMessage.setBackground(Color.red);
        contenu = new JTextArea(m.getContenu());
        contenu.setFont(fSmall);
        contenu.setEditable(false);
        contenu.setLineWrap(true);
        contenu.setMargin(new Insets(10,10,10,10));
        contenuScoller = new JScrollPane(contenu);
        contenuScoller.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, AppliColor.BLUE.getColor()));
        repondreMessage = new QGButton("Répondre", AppliColor.BLUE.getColor(), AppliColor.LIGHT_BLUE.getColor(), Color.white, fSmall);
        
        this.setLayout(sp);
        this.setBackground(Color.white);
        
        this.add(objet);
        this.add(supprimerMessage);
        this.add(auteur);
        this.add(date);
        this.add(contenuScoller);
        this.add(repondreMessage);
        
        sp.putConstraint(SpringLayout.NORTH, objet, 10, SpringLayout.NORTH, this);
        sp.putConstraint(SpringLayout.WEST, objet, 10, SpringLayout.WEST, this);
        
        sp.putConstraint(SpringLayout.NORTH, auteur, 2, SpringLayout.SOUTH, objet);
        sp.putConstraint(SpringLayout.WEST, auteur, 10, SpringLayout.WEST, this);
        
        sp.putConstraint(SpringLayout.NORTH, date, 2, SpringLayout.SOUTH, auteur);
        sp.putConstraint(SpringLayout.WEST, date, 10, SpringLayout.WEST, this);
        
        sp.putConstraint(SpringLayout.NORTH, supprimerMessage, 10, SpringLayout.NORTH, this);
        sp.putConstraint(SpringLayout.EAST, supprimerMessage, -10, SpringLayout.EAST, this);
        
        sp.putConstraint(SpringLayout.NORTH, contenuScoller, 10, SpringLayout.SOUTH, date);
        sp.putConstraint(SpringLayout.EAST, contenuScoller, -10, SpringLayout.EAST, this);
        sp.putConstraint(SpringLayout.WEST, contenuScoller, 10, SpringLayout.WEST, this);
        sp.putConstraint(SpringLayout.SOUTH, contenuScoller, -10, SpringLayout.NORTH, repondreMessage);
        
        sp.putConstraint(SpringLayout.SOUTH, repondreMessage, -10, SpringLayout.SOUTH, this);
        sp.putConstraint(SpringLayout.EAST, repondreMessage, -10, SpringLayout.EAST, this);
    }
}
