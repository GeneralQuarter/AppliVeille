/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.modele.accesbd;

import ihmpts2appliveille.modele.accesbd.entites.Article;
import ihmpts2appliveille.modele.accesbd.entites.Commentaire;
import ihmpts2appliveille.modele.accesbd.entites.Correspondance;
import ihmpts2appliveille.modele.accesbd.entites.Message;
import ihmpts2appliveille.modele.accesbd.entites.Theme;
import ihmpts2appliveille.modele.accesbd.entites.Utilisateur;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author x1QG1x
 */
public class Donnees {
    private Map<Integer, Utilisateur> utilisateurs;
    private Map<Integer, Article> articles;
    private Map<Integer, Theme> themes;
    private Map<Integer, Commentaire> commentaires;
    private Map<Integer, Message> messages;
    private List<Correspondance> correspondances;
    
    public Donnees()
    {
        utilisateurs = new HashMap<>();
        articles = new HashMap<>();
        themes = new HashMap<>();
        commentaires = new HashMap<>();
        messages = new HashMap<>();
        correspondances = new ArrayList<>();
    }
    
    public void ajouterUtilisateur(Utilisateur u)
    {
        if(!utilisateurs.containsKey(u.getIdUtilisateur()))
        {
            utilisateurs.put(u.getIdUtilisateur(), u);
        }
    }
    
    public void ajouterArticle(Article a)
    {
        if(!articles.containsKey(a.getIdArticle()))
        {
            articles.put(a.getIdArticle(), a);
        }
    }
    
    public void ajouterTheme(Theme t)
    {
        if(!themes.containsKey(t.getIdTheme()))
        {
            themes.put(t.getIdTheme(), t);
        }
    }
    
    public void ajouterCommentaires(Commentaire c)
    {
        if(!commentaires.containsKey(c.getIdCommentaire()))
        {
            commentaires.put(c.getIdCommentaire(), c);
        }
    }
    
    public void ajouterMessage(Message m)
    {
        if(!messages.containsKey(m.getIdMessage()))
        {
            messages.put(m.getIdMessage(), m);
        }
    }
    
    public void ajouterCorresponde(Correspondance c)
    {
        if(!correspondances.contains(c))
        {
            correspondances.add(c);
        }
    }
}
