/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.vue;

import ihmpts2appliveille.controleur.MainControleur;
import ihmpts2appliveille.modele.AppliColor;
import ihmpts2appliveille.modele.ListCellRendererUtilisateur;
import ihmpts2appliveille.modele.ModelListeUtilisateurMessage;
import ihmpts2appliveille.modele.accesbd.entites.Message;
import ihmpts2appliveille.modele.accesbd.entites.Utilisateur;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
    private List<Utilisateur> destinataires;
    
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
    private QGButton envoyerMessage;
    
    public NouveauMessageVue(List<Utilisateur> utilisateurs, MainControleur mctrl)
    {
        this.utilisateurs = utilisateurs;
        this.destinataires = new ArrayList<>();
        this.mctrl = mctrl;
        
        initComponents();
        
        majListes(utilisateurs, null);
    }
    
    private void initComponents()
    {
        fLarge = new Font("Arial", 0, 32);
        fMedium = new Font("Arial", 0, 20);
        fSmall = new Font("Arial", 0, 16);
        
        sp = new SpringLayout();
        
        objetField = new JTextField();
        objetField.setFont(fSmall);
        contentLabel = new JLabel("Message");
        titreLabel = new JLabel("Nouveau Message");
        titreLabel.setFont(fLarge);
        utilisateursLabel = new JLabel("Utilisateurs");
        utilisateursLabel.setFont(fMedium);
        destinatairesLabel = new JLabel("Destinataires");
        destinatairesLabel.setFont(fMedium);
        addOneBouton = new QGButton("Ajouter >", AppliColor.BLUE.getColor(), AppliColor.LIGHT_BLUE.getColor(), Color.white, fSmall);
        addOneBouton.addActionListener(new EcouteurBouton());
        addOneBouton.setPreferredSize(new Dimension(150, 40));
        addManyBouton = new QGButton("Ajouter tous >>", AppliColor.BLUE.getColor(), AppliColor.LIGHT_BLUE.getColor(), Color.white, fSmall);
        addManyBouton.addActionListener(new EcouteurBouton());
        addManyBouton.setPreferredSize(new Dimension(150, 40));
        removeOneBouton = new QGButton("< Retirer", AppliColor.BLUE.getColor(), AppliColor.LIGHT_BLUE.getColor(), Color.white, fSmall);
        removeOneBouton.setPreferredSize(new Dimension(150, 40));
        removeOneBouton.addActionListener(new EcouteurBouton());
        removeManyBouton = new QGButton("<< Retirer tous", AppliColor.BLUE.getColor(), AppliColor.LIGHT_BLUE.getColor(), Color.white, fSmall);
        removeManyBouton.addActionListener(new EcouteurBouton());
        removeManyBouton.setPreferredSize(new Dimension(150, 40));
        utilisateursList = new JList();
        utilisateursList.setFont(fSmall);
        utilisateursList.setCellRenderer(new ListCellRendererUtilisateur());
        scrollerListUtilisateur = new JScrollPane(utilisateursList);
        scrollerListUtilisateur.setBorder(objetField.getBorder());
        scrollerListUtilisateur.setPreferredSize(new Dimension(350, 200));
        destinatairesList = new JList();
        destinatairesList.setFont(fSmall);
        destinatairesList.setCellRenderer(new ListCellRendererUtilisateur());
        scrollerListDestinataires = new JScrollPane(destinatairesList);
        scrollerListDestinataires.setBorder(objetField.getBorder());
        objetLabel = new JLabel("Objet");
        objetLabel.setFont(fMedium);
        contentLabel.setFont(fMedium);
        contentField = new JTextArea();
        contentField.setFont(fSmall);
        contentField.setBorder(null);
        scrollerContent = new JScrollPane(contentField);
        scrollerContent.setBorder(objetField.getBorder());
        envoyerMessage = new QGButton("Envoyer", AppliColor.BLUE.getColor(), AppliColor.LIGHT_BLUE.getColor(), Color.white, fSmall);
        envoyerMessage.addActionListener(new EcouteurBouton());
        
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
        this.add(envoyerMessage);
        
        sp.putConstraint(SpringLayout.NORTH, titreLabel, 10, SpringLayout.NORTH, this);
        sp.putConstraint(SpringLayout.WEST, titreLabel, 10, SpringLayout.WEST, this);
        
        sp.putConstraint(SpringLayout.NORTH, utilisateursLabel, 10, SpringLayout.SOUTH, titreLabel);
        sp.putConstraint(SpringLayout.WEST, utilisateursLabel, 10, SpringLayout.WEST, this);
        
        sp.putConstraint(SpringLayout.NORTH, destinatairesLabel, 10, SpringLayout.SOUTH, titreLabel);
        sp.putConstraint(SpringLayout.WEST, destinatairesLabel, 10, SpringLayout.EAST, addOneBouton);
        
        sp.putConstraint(SpringLayout.NORTH, addOneBouton, 5, SpringLayout.SOUTH, utilisateursLabel);
        sp.putConstraint(SpringLayout.WEST, addOneBouton, 10, SpringLayout.EAST, scrollerListUtilisateur);

        sp.putConstraint(SpringLayout.NORTH, scrollerListUtilisateur, 5, SpringLayout.SOUTH, utilisateursLabel);
        sp.putConstraint(SpringLayout.WEST, scrollerListUtilisateur, 10, SpringLayout.WEST, this);
        sp.putConstraint(SpringLayout.SOUTH, scrollerListUtilisateur, 0, SpringLayout.SOUTH, removeManyBouton);
        
        sp.putConstraint(SpringLayout.NORTH, scrollerListDestinataires, 5, SpringLayout.SOUTH, destinatairesLabel);
        sp.putConstraint(SpringLayout.WEST, scrollerListDestinataires, 10, SpringLayout.EAST, addOneBouton);
        sp.putConstraint(SpringLayout.EAST, scrollerListDestinataires, -10, SpringLayout.EAST, this);
        sp.putConstraint(SpringLayout.SOUTH, scrollerListDestinataires, 0, SpringLayout.SOUTH, removeManyBouton);
        
        sp.putConstraint(SpringLayout.NORTH, addManyBouton, 10, SpringLayout.SOUTH, addOneBouton);
        sp.putConstraint(SpringLayout.WEST, addManyBouton, 10, SpringLayout.EAST, scrollerListUtilisateur);
        
        sp.putConstraint(SpringLayout.NORTH, removeOneBouton, 10, SpringLayout.SOUTH, addManyBouton);
        sp.putConstraint(SpringLayout.WEST, removeOneBouton, 10, SpringLayout.EAST, scrollerListUtilisateur);
        
        sp.putConstraint(SpringLayout.NORTH, removeManyBouton, 10, SpringLayout.SOUTH, removeOneBouton);
        sp.putConstraint(SpringLayout.WEST, removeManyBouton, 10, SpringLayout.EAST, scrollerListUtilisateur);
        
        sp.putConstraint(SpringLayout.NORTH, objetLabel, 10, SpringLayout.SOUTH, scrollerListUtilisateur);
        sp.putConstraint(SpringLayout.WEST, objetLabel, 10, SpringLayout.WEST, this);
        
        sp.putConstraint(SpringLayout.NORTH, objetField, 5, SpringLayout.SOUTH, objetLabel);
        sp.putConstraint(SpringLayout.WEST, objetField, 10, SpringLayout.WEST, this);
        sp.putConstraint(SpringLayout.EAST, objetField, -10, SpringLayout.EAST, this);
        
        sp.putConstraint(SpringLayout.NORTH, contentLabel, 10, SpringLayout.SOUTH, objetField);
        sp.putConstraint(SpringLayout.WEST, contentLabel, 10, SpringLayout.WEST, this);
        
        sp.putConstraint(SpringLayout.NORTH, scrollerContent, 5, SpringLayout.SOUTH, contentLabel);
        sp.putConstraint(SpringLayout.WEST, scrollerContent, 10, SpringLayout.WEST, this);
        sp.putConstraint(SpringLayout.EAST, scrollerContent, -10, SpringLayout.EAST, this);
        sp.putConstraint(SpringLayout.SOUTH, scrollerContent, -10, SpringLayout.NORTH, envoyerMessage);
               
        sp.putConstraint(SpringLayout.EAST, envoyerMessage, -10, SpringLayout.EAST, this);
        sp.putConstraint(SpringLayout.SOUTH, envoyerMessage, -10, SpringLayout.SOUTH, this);
    }
    
    public void majListes(List<Utilisateur> utilisateurs, List<Utilisateur> destinataires)
    {
        if(utilisateurs != null)
            utilisateursList.setModel(new ModelListeUtilisateurMessage(utilisateurs));
        if(destinataires != null)
            destinatairesList.setModel(new ModelListeUtilisateurMessage(destinataires));
    }
    
    public void renvoiMessage(Message m)
    {
        objetField.setText("Re : " + m.getIntitule());
        for(Utilisateur u : utilisateurs)
        {
            if(m.getIdAuteur() == u.getIdUtilisateur())
            {
                utilisateurs.remove(u);
                destinataires.add(u);
                break;
            }
        }
        majListes(utilisateurs, destinataires);
    }
    
    private class EcouteurBouton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            switch(e.getActionCommand())
            {
                case "Ajouter >":
                    Utilisateur u;
                    for(Object o : utilisateursList.getSelectedValuesList())
                    {
                        u = (Utilisateur) o;
                        utilisateurs.remove(u);
                        destinataires.add(u);
                    }
                    majListes(utilisateurs, destinataires);
                    break;
                case "< Retirer":
                    for(Object o : destinatairesList.getSelectedValuesList())
                    {
                        u = (Utilisateur) o;
                        destinataires.remove(u);
                        utilisateurs.add(u);
                    }
                    majListes(utilisateurs, destinataires);
                    break;
                case "Ajouter tous >>":
                    for(Utilisateur o : utilisateurs)
                    {
                        destinataires.add(o);
                    }
                    utilisateurs.clear();
                    majListes(utilisateurs, destinataires);
                    break;
                case "<< Retirer tous":
                    for(Utilisateur o : destinataires)
                    {
                        utilisateurs.add(o);
                    }
                    destinataires.clear();
                    majListes(utilisateurs, destinataires);
                    break;
                case "Envoyer":
                    mctrl.envoyerMessage(objetField.getText(), contentField.getText(), destinataires);
                    break;
            }
        }
        
    }
}
