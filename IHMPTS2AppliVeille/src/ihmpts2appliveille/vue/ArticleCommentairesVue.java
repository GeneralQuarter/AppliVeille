/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.vue;

import ihmpts2appliveille.controleur.MainControleur;
import ihmpts2appliveille.modele.AppliColor;
import ihmpts2appliveille.modele.Droits;
import ihmpts2appliveille.modele.accesbd.entites.Article;
import ihmpts2appliveille.modele.accesbd.entites.Commentaire;
import ihmpts2appliveille.modele.accesbd.entites.Theme;
import ihmpts2appliveille.modele.accesbd.entites.Utilisateur;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.text.html.HTMLDocument;

/**
 * Classe d'affichage de l'interface d'un article en détail et de ses commentaires
 * @author x1QG1x
 */
public class ArticleCommentairesVue extends JPanel{
    private MainControleur mctrl;
    
    private Article a;
    private Utilisateur u;
    private Theme t;
    private List<Commentaire> cs;
    private Map<Integer, Utilisateur> utilisateurs;
    
    private SpringLayout sp;
    private DateFormat df;
    
    private JPanel body;
    
    private JScrollPane mainScroller;
    private JScrollPane articleScroller;
    
    private Font fLarge;
    private Font fMedium;
    private Font fSmall;
    
    private JLabel articleTitre;
    private JLabel themeTitre;
    private JLabel auteur;
    private JPanel note;
    private JLabel publieLe;
    private JLabel modifieLe;
    private JEditorPane articleContent;
    private QGButton editArticle;
    private JLabel posterCommTitle;
    private JTextField posterCommTitleField;
    private JTextArea posterCommContentField;
    private JScrollPane posterCommScroller;
    private QGButton sendComm;
    private JTextField noteField;
    private QGButton noteButton;
    private JPanel commentairesHolder;
    
    public ArticleCommentairesVue(Article a, Utilisateur u, Theme t, List<Commentaire> cs, Map<Integer, Utilisateur> utilisateurs, MainControleur mctrl)
    {
        this.mctrl = mctrl;
        this.a = a;
        this.u = u;
        this.t = t;
        this.cs = cs;
        this.utilisateurs = utilisateurs;
        
        initComponents();
        initCommentaires();
    }
    
    private void initComponents()
    {
        df = new SimpleDateFormat("dd/MM/yyyy à HH:mm");
        fLarge = new Font("Arial", Font.BOLD, 40);
        fMedium = new Font("Arial", 0, 20);
        fSmall = new Font("Arial", 0, 16);
        
        articleTitre = new JLabel(a.getIntitule());
        articleTitre.setFont(fLarge);
        themeTitre = new JLabel(t.getIntitule());
        themeTitre.setFont(fMedium);
        auteur = new JLabel(u.getNom());
        auteur.setFont(fSmall);
        if(a.getNote() >= 0.0f){
            note = new Etoile((int) a.getNote());
            note.setToolTipText("" + a.getNote());
            note.setBackground(this.getBackground());
        }else
            note = new JPanel();
        publieLe = new JLabel("publié le " + df.format(a.getDatePubli()));
        publieLe.setFont(fSmall);
        if(a.getDateModif() != null)
        {
            modifieLe = new JLabel("modifié le " + df.format(a.getDateModif()));
            modifieLe.setFont(fSmall);
        }else{
            modifieLe = null;
        }
        articleContent = new JEditorPane();
        articleContent.setContentType("text/html");
        String bodyRule = "body { font-family:\"Arial\"; " +
            "font-size:14pt;}";
        ((HTMLDocument)articleContent.getDocument()).getStyleSheet().addRule(bodyRule);
        articleContent.setEditable(false);
        articleContent.setText(a.getContenu());
        articleContent.setCaretPosition(0);
        
        articleScroller = new JScrollPane(articleContent);
        articleScroller.setPreferredSize(new Dimension(1000, 400));
        if(u.getIdUtilisateur() == mctrl.getUtilisateurConnecte().getIdUtilisateur()){
            editArticle = new QGButton("Editer", AppliColor.BLUE.getColor(), AppliColor.LIGHT_BLUE.getColor(), Color.white, fSmall);
            editArticle.addActionListener(new EcouteurBouton());
        }else
            editArticle = null;
        if(mctrl.getUtilisateurConnecte().getTypeProfil() == Droits.PROFESSEUR){
            noteField = new JTextField();
            noteField.setPreferredSize(new Dimension(100, 50));
            noteField.setFont(fSmall);
            noteButton = new QGButton("Noter", AppliColor.BLUE.getColor(), AppliColor.LIGHT_BLUE.getColor(), Color.white, fSmall);
            noteButton.addActionListener(new EcouteurBouton());
        }else{
            noteField = null;
            noteButton = null;
        }    
        posterCommTitle = new JLabel("Poster un commentaire");
        posterCommTitle.setFont(fMedium);
        posterCommTitleField = new JTextField();
        posterCommTitleField.setFont(fSmall);
        posterCommTitleField.setToolTipText("Intitulé");
        posterCommContentField = new JTextArea();
        posterCommScroller = new JScrollPane(posterCommContentField);
        posterCommScroller.setPreferredSize(new Dimension(100, 150));
        posterCommContentField.setFont(fSmall);
        posterCommContentField.setToolTipText("Contenu");
        posterCommScroller.setBorder(posterCommTitleField.getBorder());
        sendComm = new QGButton("Envoyer", AppliColor.BLUE.getColor(), AppliColor.LIGHT_BLUE.getColor(), Color.white, fSmall);
        sendComm.addActionListener(new EcouteurBouton());
        commentairesHolder = new JPanel();
        commentairesHolder.setLayout(new BoxLayout(commentairesHolder, BoxLayout.Y_AXIS));
        
        this.setLayout(new BorderLayout());
        
        body = new JPanel();
        sp = new SpringLayout();
        body.setLayout(sp);
        if(cs != null)
            body.setPreferredSize(new Dimension(100, 800+240*cs.size()));
        else
            body.setPreferredSize(new Dimension(100, 850));
        
        body.add(articleTitre);
        body.add(themeTitre);
        body.add(auteur);
        body.add(note);
        body.add(publieLe);
        if(modifieLe != null)
            body.add(modifieLe);
        body.add(articleScroller);
        if(editArticle != null)
            body.add(editArticle);
        if(noteField != null){
            body.add(noteField);
            body.add(noteButton);
        }
        body.add(posterCommTitle);
        body.add(posterCommTitleField);
        body.add(posterCommScroller);
        body.add(sendComm);
        body.add(commentairesHolder);
        
        sp.putConstraint(SpringLayout.NORTH, articleTitre, 0, SpringLayout.NORTH, body);
        sp.putConstraint(SpringLayout.WEST, articleTitre, 5, SpringLayout.WEST, body);
        
        sp.putConstraint(SpringLayout.NORTH, themeTitre, -8, SpringLayout.SOUTH, articleTitre);
        sp.putConstraint(SpringLayout.WEST, themeTitre, 5, SpringLayout.WEST, body);
        
        sp.putConstraint(SpringLayout.NORTH, auteur, 0, SpringLayout.SOUTH, themeTitre);
        sp.putConstraint(SpringLayout.WEST, auteur, 5, SpringLayout.WEST, body);
        
        sp.putConstraint(SpringLayout.NORTH, note, 10, SpringLayout.NORTH, body);
        sp.putConstraint(SpringLayout.EAST, note, -5, SpringLayout.EAST, body);
        
        sp.putConstraint(SpringLayout.NORTH, publieLe, 0, SpringLayout.SOUTH, note);
        sp.putConstraint(SpringLayout.EAST, publieLe, -5, SpringLayout.EAST, body);
        
        if(modifieLe != null)
        {
            sp.putConstraint(SpringLayout.NORTH, modifieLe, 15, SpringLayout.NORTH, publieLe);
            sp.putConstraint(SpringLayout.EAST, modifieLe, -5, SpringLayout.EAST, body);
        }
        
        sp.putConstraint(SpringLayout.NORTH, articleScroller, 20, SpringLayout.SOUTH, auteur);
        sp.putConstraint(SpringLayout.WEST, articleScroller, 5, SpringLayout.WEST, body);
        sp.putConstraint(SpringLayout.EAST, articleScroller, -5, SpringLayout.EAST, body);
        
        if(editArticle != null)
        {
            sp.putConstraint(SpringLayout.NORTH, editArticle, 5, SpringLayout.SOUTH, articleScroller);
            sp.putConstraint(SpringLayout.EAST, editArticle, -5, SpringLayout.EAST, body);
        }
        
        if(noteField != null)
        {
            sp.putConstraint(SpringLayout.NORTH, noteButton, 5, SpringLayout.SOUTH, articleScroller);
            sp.putConstraint(SpringLayout.EAST, noteButton, -5, SpringLayout.EAST, body);
            
            sp.putConstraint(SpringLayout.NORTH, noteField, 5, SpringLayout.SOUTH, articleScroller);
            sp.putConstraint(SpringLayout.EAST, noteField, -5, SpringLayout.WEST, noteButton);
            sp.putConstraint(SpringLayout.SOUTH, noteField, 0, SpringLayout.SOUTH, noteButton);
        }
        
        sp.putConstraint(SpringLayout.NORTH, posterCommTitle, 20, SpringLayout.SOUTH, articleScroller);
        sp.putConstraint(SpringLayout.WEST, posterCommTitle, 5, SpringLayout.WEST, body);
        
        sp.putConstraint(SpringLayout.NORTH, posterCommTitleField, 5, SpringLayout.SOUTH, posterCommTitle);
        sp.putConstraint(SpringLayout.WEST, posterCommTitleField, 5, SpringLayout.WEST, body);
        sp.putConstraint(SpringLayout.EAST, posterCommTitleField, -5, SpringLayout.EAST, body);
        
        sp.putConstraint(SpringLayout.NORTH, posterCommScroller, 5, SpringLayout.SOUTH, posterCommTitleField);
        sp.putConstraint(SpringLayout.WEST, posterCommScroller, 5, SpringLayout.WEST, body);
        sp.putConstraint(SpringLayout.EAST, posterCommScroller, -5, SpringLayout.EAST, body);
        
        sp.putConstraint(SpringLayout.NORTH, sendComm, 5, SpringLayout.SOUTH, posterCommScroller);
        sp.putConstraint(SpringLayout.EAST, sendComm, -5, SpringLayout.EAST, body);
        
        sp.putConstraint(SpringLayout.NORTH, commentairesHolder, 5, SpringLayout.SOUTH, sendComm);
        sp.putConstraint(SpringLayout.WEST, commentairesHolder, 5, SpringLayout.WEST, body);
        sp.putConstraint(SpringLayout.EAST, commentairesHolder, -5, SpringLayout.EAST, body);

        mainScroller = new JScrollPane(body);
        mainScroller.setBorder(null);
        mainScroller.getVerticalScrollBar().setUnitIncrement(16);
        this.add(mainScroller, BorderLayout.CENTER);
    }
    
    private void initCommentaires()
    {
        if(cs != null)
        {
            for(Commentaire c : cs)
            {
                ajouterCommentaire(c, utilisateurs.get(c.getIdAuteur()));
            }
        }
    }
    
    /**
     * Insertion d'un nouveau commentaire dans l'interface
     * @param c
     * @param u 
     */
    public void ajouterCommentaire(Commentaire c, Utilisateur u)
    {
        CommentaireVue cv = new CommentaireVue(c, u, mctrl);
        commentairesHolder.add(cv);
        commentairesHolder.add(Box.createRigidArea(new Dimension(0, 5)));
    }
    
    private class EcouteurBouton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            switch(e.getActionCommand())
            {
                case "Editer":
                    mctrl.allerVersModificationArticle(ArticleCommentairesVue.this.a.getIdArticle());
                    break;
                case "Noter":
                        try {
                            mctrl.miseAjourNoteArticle(ArticleCommentairesVue.this.a.getIdArticle(), Float.parseFloat(noteField.getText()));
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "La note " + noteField.getText() + " est invalide (ex : 4.5)", "Note invalide", JOptionPane.ERROR_MESSAGE);
                        }
                    break;
                case "Envoyer":
                    mctrl.posterCommentaire(ArticleCommentairesVue.this.a.getIdArticle(), posterCommTitleField.getText(), posterCommContentField.getText());
                    break;
            }
        }
    }
}
