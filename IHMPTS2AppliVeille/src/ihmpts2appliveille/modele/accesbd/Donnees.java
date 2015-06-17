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
 * Classe de récupération des données de la base de donnée pour les maintenir dans l'application
 * @author x1QG1x
 */
public class Donnees {
    private Map<Integer, Utilisateur> utilisateurs;
    private Map<Integer, Article> articles;
    private Map<Integer, Theme> themes;
    private Map<Integer, Commentaire> commentaires;
    private Map<Integer, Message> messages;
    private List<Correspondance> correspondances;
    
    /**
     * Constructeur de la classe données. 
     */
    public Donnees()
    {
        utilisateurs = new HashMap<>();
        articles = new HashMap<>();
        themes = new HashMap<>();
        commentaires = new HashMap<>();
        messages = new HashMap<>();
        correspondances = new ArrayList<>();
    }
    
    /**
     * Méthode de mise à jour du propriétaire d'un thème
     * @param idTheme L'identifiant de l'utilisateur
     * @param idUtilisateur L'identifiant du propriétaire
     */
    public void updateProprietaireTheme(int idTheme, int idUtilisateur)
    {
        Theme t = themes.get(idTheme);
        t.setIdProp(idUtilisateur);
        themes.remove(idTheme);
        themes.put(idTheme, t);
    }
    
    /**
     * Méthode de mise à jour de la description d'un thème
     * @param idTheme L'identifiant du thème
     * @param description La nouvelle description du thème
     */
    public void updateDescriptionTheme(int idTheme, String description)
    {
        Theme t = themes.get(idTheme);
        t.setDescritpion(description);
        themes.remove(idTheme);
        themes.put(idTheme, t);
    }
    
    /**
     * Méthode permettant la modification du HashMap utilisateur pour lui ajouter un nouvel utilisateur
     * @param u L'utilisateur ajouté
     */
    public void ajouterUtilisateur(Utilisateur u)
    {
        if(!utilisateurs.containsKey(u.getIdUtilisateur()))
        {
            utilisateurs.put(u.getIdUtilisateur(), u);
        }else{
            utilisateurs.remove(u.getIdUtilisateur());
            utilisateurs.put(u.getIdUtilisateur(), u);
        }
    }
    
    /**
     * Méthode permettant la modification du HashMap articles pour lui ajouter un nouvel article
     * @param a  L'article ajouté
     */
    public void ajouterArticle(Article a)
    {
        if(!articles.containsKey(a.getIdArticle()))
        {
            articles.put(a.getIdArticle(), a);
        }else{
            articles.remove(a.getIdArticle());
            articles.put(a.getIdArticle(), a);
        }
    }
    
    /**
     * Méthode permettant la modification du HashMap themes pour lui ajouter un nouveau thème
     * @param t Le thème ajouté
     */
    public void ajouterTheme(Theme t)
    {
        if(!themes.containsKey(t.getIdTheme()))
        {
            themes.put(t.getIdTheme(), t);
        }else{
            themes.remove(t.getIdTheme());
            themes.put(t.getIdTheme(), t);
        }
    }
    
    /**
     * Méthode permettant la modification du HashMap commentaires pour lui ajouter un nouveau commentaire
     * @param c Le commentaire ajouté
     */
    public void ajouterCommentaire(Commentaire c)
    {
        if(!commentaires.containsKey(c.getIdCommentaire()))
        {
            commentaires.put(c.getIdCommentaire(), c);
        }else{
            commentaires.remove(c.getIdCommentaire());
            commentaires.put(c.getIdCommentaire(), c);
        }
    }
    
    /**
     * Méthode permettant la modification du HashMap messages pour lui ajouter un nouveau message
     * @param m Le message ajouté
     */
    public void ajouterMessage(Message m)
    {
        if(!messages.containsKey(m.getIdMessage()))
        {
            messages.put(m.getIdMessage(), m);
        }else{
            messages.remove(m.getIdMessage());
            messages.put(m.getIdMessage(), m);
        }
    }
    
    /**
     * Méthode permettant la modification du HashMap correspondances pour lui ajouter une nouvelle correspondance
     * @param c La correspondance ajoutée
     */
    public void ajouterCorresponde(Correspondance c)
    {
        if(!correspondances.contains(c))
        {
            correspondances.add(c);
        }else{
            correspondances.remove(c);
            correspondances.add(c);
        }
    }
    
    /**
     * Méthode permettant de supprimer un utilisateur du HashMap utilisateurs
     * @param idUtilisateur L'identifiant de l'utilisateur à supprimer
     */
    public void supprimerUtilisateur(int idUtilisateur)
    {
        if(utilisateurs.containsKey(idUtilisateur))
        {
            utilisateurs.remove(idUtilisateur);
        }
    }
    
    /**
     * Méthode permettant de supprimer un article du HashMap articles
     * @param idArticle L'identifiant de l'article à supprimer
     */
    public void supprimerArticle(int idArticle)
    {
        if(articles.containsKey(idArticle))
        {
            articles.remove(idArticle);
        }
    }
    
    /**
     * Méthode permettant de supprimer un theme du HashMap themes
     * @param idTheme L'identifiant du theme à supprimer 
     */
    public void supprimerTheme(int idTheme)
    {
        if(themes.containsKey(idTheme))
        {
            themes.remove(idTheme);
        }
    }
    
    /**
     * Méthode permettant de supprimer un commentaire du HashMap commentaires
     * @param idCommentaire L'identifiant du commentaire à supprimer
     */
    public void supprimerCommentaire(int idCommentaire)
    {
        if(commentaires.containsKey(idCommentaire))
        {
            commentaires.remove(idCommentaire);
        }
    }
    
    /**
     * Méthode permettant de supprimer un message du HashMap messages
     * @param idMessage L'identifiant du message à supprimer
     */
    public void supprimerMessage(int idMessage)
    {
        if(messages.containsKey(idMessage))
        {
            messages.remove(idMessage);
        }
    }
    
    /**
     * Méthode permettant de supprimer une correspondance du HashMap correspondances
     * @param c L'identifiant de la correspondance à supprimer
     */
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
