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
    
    public void updateProprietaireTheme(int idTheme, int idUtilisateur)
    {
        Theme t = themes.get(idTheme);
        t.setIdProp(idUtilisateur);
        themes.remove(idTheme);
        themes.put(idTheme, t);
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
    
    public void ajouterCommentaire(Commentaire c)
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
    
    public void supprimerUtilisateur(int idUtilisateur)
    {
        if(utilisateurs.containsKey(idUtilisateur))
        {
            utilisateurs.remove(idUtilisateur);
        }
    }
    
    public void supprimerArticle(int idArticle)
    {
        if(articles.containsKey(idArticle))
        {
            articles.remove(idArticle);
        }
    }
    
    public void supprimerTheme(int idTheme)
    {
        if(themes.containsKey(idTheme))
        {
            themes.remove(idTheme);
        }
    }
    
    public void supprimerCommentaire(int idCommentaire)
    {
        if(commentaires.containsKey(idCommentaire))
        {
            commentaires.remove(idCommentaire);
        }
    }
    
    public void supprimerMessage(int idMessage)
    {
        if(messages.containsKey(idMessage))
        {
            messages.remove(idMessage);
        }
    }
    
    public void supprimerCorrespondance(Correspondance c)
    {
        if(correspondances.contains(c))
        {
            correspondances.remove(c);
        }
    }
    
    public boolean containsUtilisateur(int idUtilisateur)
    {
        return utilisateurs.containsKey(idUtilisateur);
    }
    
    public boolean containsArticle(int idArticle)
    {
        return articles.containsKey(idArticle);
    }
    
    public boolean containsTheme(int idTheme)
    {
        return themes.containsKey(idTheme);
    }
    
    public boolean containsCommentaire(int idCommentaire)
    {
        return commentaires.containsKey(idCommentaire);
    }
    
    public boolean containsMessage(int idMessage)
    {
        return messages.containsKey(idMessage);
    }
    
    public boolean containsCorrespondance(Correspondance c)
    {
        return correspondances.contains(c);
    }
    
    public Utilisateur getUtilisateur(int idUtilisateur)
    {
        if(containsUtilisateur(idUtilisateur))
            return utilisateurs.get(idUtilisateur);
        else
            return null;
    }
    
    public Article getArticle(int idArticle)
    {
        if(containsArticle(idArticle))
            return articles.get(idArticle);
        else
            return null;
    }
    
    public Theme getTheme(int idTheme)
    {
        if(containsTheme(idTheme))
            return themes.get(idTheme);
        else
            return null;
    }
    
    public Commentaire getCommentaire(int idCommentaire)
    {
        if(containsCommentaire(idCommentaire))
            return commentaires.get(idCommentaire);
        else
            return null;
    }
    
    public Message getMessage(int idMessage)
    {
        if(containsMessage(idMessage))
            return messages.get(idMessage);
        else
            return null;
    }
    
    public Correspondance getCorrespondance(int idMessage, int idDest)
    {
        for(Correspondance c : correspondances)
        {
            if(c.getIdMessage() == idMessage && c.getIdDestinataire() == idDest)
            {
                return c;
            }
        }
        return null;
    }

    public Map<Integer, Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public Map<Integer, Article> getArticles() {
        return articles;
    }

    public Map<Integer, Theme> getThemes() {
        return themes;
    }

    public Map<Integer, Commentaire> getCommentaires() {
        return commentaires;
    }

    public Map<Integer, Message> getMessages() {
        return messages;
    }

    public List<Correspondance> getCorrespondances() {
        return correspondances;
    }
}
