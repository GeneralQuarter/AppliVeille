/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.vue;

import ihmpts2appliveille.controleur.MainControleur;
import ihmpts2appliveille.modele.accesbd.entites.Article;
import ihmpts2appliveille.modele.accesbd.entites.Theme;
import ihmpts2appliveille.modele.accesbd.entites.Utilisateur;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author x1QG1x
 */
public class ActualiteArticleVue extends JPanel{
    private MainControleur mctrl;
    
    private JLabel title;
    private JPanel articleHolder;
    private JScrollPane articleScroller;
    
    private Font fTitle;
    
    public ActualiteArticleVue(String title, MainControleur mctrl)
    {
        // -- Setup Controleur --
        this.mctrl = mctrl;
        this.mctrl.setActualiteArticleVue(this);
        
        this.setLayout(new BorderLayout(10, 10)); 
        
        // -- Setup Title --
        this.title = new JLabel(title);
        fTitle = new Font("Arial", Font.BOLD, 40);
        this.title.setFont(fTitle);
        this.title.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0)); // See Editor for changes...
        
        // -- Setup articleHolder --
        this.articleHolder = new JPanel();
        this.articleHolder.setLayout(new BoxLayout(this.articleHolder, BoxLayout.Y_AXIS));
        this.articleHolder.add(Box.createRigidArea(new Dimension(0,10)));
        
        // -- Setup articleScroller --
        this.articleScroller = new JScrollPane(this.articleHolder);
        this.articleScroller.setBorder(null);
        this.articleScroller.getVerticalScrollBar().setUnitIncrement(16);
        this.articleScroller.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, new Color(33,33,33))); // need to extend to side see gap up
        
        this.add(this.title, BorderLayout.NORTH);
        this.add(this.articleScroller, BorderLayout.CENTER);
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // WTF !! remove that ! ASAP 
    }
    
    public void ajouterArticle(ArticleListItem ali)
    {
        this.articleHolder.add(ali);
        this.articleHolder.add(Box.createRigidArea(new Dimension(0,10)));
    }
    
    public void setTitle(String title)
    {
        this.title.setText(title);
    }
    
    private void removeArticles()
    {
        this.articleHolder.removeAll();
        this.validate();
    }
    
    public void setArticles(Map<Integer, Article> articles, Map<Integer, Theme> themes, Map<Integer, Utilisateur> utilisateurs)
    {
        removeArticles();
        for(Article a : articles.values())
        {
            DateFormat df = new SimpleDateFormat("dd/MM/yyy à HH:mm");
            String date = null;
            if(a.getDateModif() == null)
                date = "Publié le " + df.format(a.getDatePubli());
            else
                date = "Modifié le " + df.format(a.getDateModif());
            float note = a.getNote();
            this.ajouterArticle(new ArticleListItem(themes.get(a.getIdTheme()).getIntitule(), a.getIntitule(), a.getContenu(), utilisateurs.get(a.getIdAuteur()).getNom(), date, a.getNbCommArt(), (int) note, a.getIdArticle(), mctrl));
        }
        this.validate();
    }
    
    public void setArticleUtilisateur(Map<Integer, Article> articles, Theme theme, Utilisateur utilisateur)
    {
        removeArticles();
        for(Article a : articles.values())
        {
            DateFormat df = new SimpleDateFormat("dd/MM/yyy à HH:mm");
            String date = null;
            if(a.getDateModif() == null)
                date = "Publié le " + df.format(a.getDatePubli());
            else
                date = "Modifié le " + df.format(a.getDateModif());
            float note = a.getNote();
            this.ajouterArticle(new ArticleListItem(theme.getIntitule(), a.getIntitule(), a.getContenu(), utilisateur.getNom(), date, a.getNbCommArt(), (int) note, a.getIdArticle(), mctrl));
        }
        this.validate();
    }
}
