/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.modele.accesbd;

import ihmpts2appliveille.modele.Droits;
import ihmpts2appliveille.modele.Statut;
import ihmpts2appliveille.modele.accesbd.entites.Article;
import ihmpts2appliveille.modele.accesbd.entites.Commentaire;
import ihmpts2appliveille.modele.accesbd.entites.Correspondance;
import ihmpts2appliveille.modele.accesbd.entites.Message;
import ihmpts2appliveille.modele.accesbd.entites.Theme;
import ihmpts2appliveille.modele.accesbd.entites.Utilisateur;
import iutlr.dutinfo.bd.AccesBD;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author x1QG1x
 */
public class RecuperationDonneesInitiales {
    private AccesBD acces;
    private Map<Integer, Utilisateur> utilisateurs;
    private Map<Integer, Article> articles;
    private Map<Integer, Theme> themes;
    private Map<Integer, Commentaire> commentaires;
    private Map<Integer, Message> messages;
    private Map<Integer, Correspondance> correspondances;
    
    public RecuperationDonneesInitiales(){
        this.acces = new AccesBD();
        this.themes = new HashMap<>();
        this.utilisateurs = new HashMap<>();
    }
    
    public Map<Integer, Utilisateur> recupererUtilisateurs()
    {
        try {
            List<List<String>> resultats = acces.interrogerBase("select id_utilisateur, nom, note, nbconn, nbcomm, nbarticle, identifiant, mdp, type_profil, etat from utilisateur");
            int idUtilisateur, nbConn, nbComm, nbArticle;
            String nom, identifiant, mdp;
            float note = 0.0f;
            Droits typeProfil = null;
            Statut etat = null;
            List<String> row;
            for(int i = 0; i < resultats.size();i++)
            {
                row = resultats.get(i);
                idUtilisateur = Integer.parseInt(row.get(0));
                if(!utilisateurs.containsKey(idUtilisateur))
                {
                    nom = row.get(1);
                    //note = ?
                    nbConn = Integer.parseInt(row.get(3));
                    nbComm = Integer.parseInt(row.get(4));
                    nbArticle = Integer.parseInt(row.get(5));
                    identifiant = row.get(6);
                    mdp = row.get(7);
                    switch(row.get(8))
                    {
                        case "professeur":
                            typeProfil = Droits.PROFESSEUR;
                            break;
                        case "eleve":
                            typeProfil = Droits.ETUDIANT;
                            break;
                        case "admin":
                            typeProfil = Droits.ADMINISTRATEUR;
                            break;
                    }      
                    switch(row.get(9))
                    {
                        case "C":
                            etat = Statut.CONNECTE;
                            break;
                        case "N":
                            etat = Statut.CONNECTE;
                            break;
                    }
                    utilisateurs.put(idUtilisateur, new Utilisateur(idUtilisateur, nom, note, nbConn, nbComm, nbArticle, identifiant, mdp, typeProfil, etat));
                    
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return utilisateurs;
    }
    
    public Utilisateur recupererUtilisateur(int id)
    {
        if(utilisateurs.containsKey(id))
            return utilisateurs.get(id);
        Utilisateur utilisateur = null;
        try {
            List<List<String>> resultats = acces.interrogerBase("select id_utilisateur, nom, note, nbconn, nbcomm, nbarticle, identifiant, mdp, type_profil, etat from utilisateur where id_utilisateur=" + id);
            List<String> row = resultats.get(0);
            int idUtilisateur = Integer.parseInt(row.get(0));
            String nom = row.get(1);
            float note = 0.0f;
            int nbConn = Integer.parseInt(row.get(3));
            int nbComm = Integer.parseInt(row.get(4));
            int nbArticle = Integer.parseInt(row.get(5));
            String identifiant = row.get(6);
            String mdp = row.get(7);
            Droits typeProfil = null;
            switch(row.get(8))
            {
                case "professeur":
                    typeProfil = Droits.PROFESSEUR;
                    break;
                case "eleve":
                    typeProfil = Droits.ETUDIANT;
                    break;
                case "admin":
                    typeProfil = Droits.ADMINISTRATEUR;
                    break;
            }      
            Statut etat = null;
            switch(row.get(9))
            {
                case "C":
                    etat = Statut.CONNECTE;
                    break;
                case "N":
                    etat = Statut.CONNECTE;
                    break;
            }
            utilisateur = new Utilisateur(idUtilisateur, nom, note, nbConn, nbComm, nbArticle, identifiant, mdp, typeProfil, etat);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return utilisateur;
    }
    
    public Utilisateur recupererUtilisateurEnConnection(String identifiant)
    {
        Utilisateur utilisateur = null;
        try {
            List<List<String>> resultats = acces.interrogerBase("select id_utilisateur, nom, note, nbconn, nbcomm, nbarticle, identifiant, mdp, type_profil, etat from utilisateur where identifiant='" + identifiant + "'");
            if(resultats.isEmpty())
            {
                return null;
            }
            List<String> row = resultats.get(0);
            int idUtilisateur = Integer.parseInt(row.get(0));
            String nom = row.get(1);
            float note = 0.0f;
            int nbConn = Integer.parseInt(row.get(3));
            int nbComm = Integer.parseInt(row.get(4));
            int nbArticle = Integer.parseInt(row.get(5));
            String mdp = row.get(7);
            Droits typeProfil = null;
            switch(row.get(8))
            {
                case "professeur":
                    typeProfil = Droits.PROFESSEUR;
                    break;
                case "eleve":
                    typeProfil = Droits.ETUDIANT;
                    break;
                case "admin":
                    typeProfil = Droits.ADMINISTRATEUR;
                    break;
            }      
            Statut etat = null;
            switch(row.get(9))
            {
                case "C":
                    etat = Statut.CONNECTE;
                    break;
                case "N":
                    etat = Statut.CONNECTE;
                    break;
            }
            utilisateur = new Utilisateur(idUtilisateur, nom, note, nbConn, nbComm, nbArticle, identifiant, mdp, typeProfil, etat);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        return utilisateur;
    }
    
    public Article recupererArticle(int idArticle){
        Article article = null;
        if(articles.containsKey(idArticle))
            return articles.get(idArticle);
        try{
            List<List<String>> resultats = acces.interrogerBase("SELECT * FROM ARTICLE WHERE ID_ARTICLE ='" + idArticle + "'");
            List<String> row = resultats.get(0);
            if(idArticle == Integer.parseInt(row.get(0)))
            {
                int idAuteur = Integer.parseInt(row.get(1));
                int idTheme = Integer.parseInt(row.get(2));
                int nbCommArt = Integer.parseInt(row.get(3));
                String intitule = row.get(4);
                String contenu = row.get(5);
                Calendar datePubli = Calendar.getInstance(); // A MODIFIER
                Calendar dateModif = Calendar.getInstance(); // A MODIFIER
                float note = 0.0f; // A MODIFIER
                article = new Article(idArticle, idAuteur, idTheme, nbCommArt, intitule, contenu, datePubli, dateModif, note);
            }else{
                //Erreur de selection ou article non existant
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return article;
    }
    
    public Map<Integer, Theme> recupererThemes()
    {
        try{
            List<List<String>> resultats = acces.interrogerBase("SELECT * FROM THEME");
            List<String> row;
            int idTheme, idProp;
            String intitule, description;
            for(int i = 0; i < resultats.size(); i++)
            {
                row = resultats.get(i);
                idTheme = Integer.parseInt(row.get(0));
                if(!themes.containsKey(idTheme))
                {
                    if(row.get(1) == null){
                        idProp = 0;
                    }else{
                        idProp = Integer.parseInt(row.get(1));
                        recupererUtilisateur(idProp);
                    }
                    intitule = row.get(2);
                    description = row.get(3);
                    if(description == null)
                    {
                        description = "";
                    }
                    themes.put(idTheme, new Theme(idTheme, idProp, intitule, description));
                }
            }
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return themes;
    }
    
    public Theme recupererTheme(int idTheme){
        Theme theme = null;
        if(themes.containsKey(idTheme))
            return themes.get(idTheme);
        try{
            List<List<String>> resultats = acces.interrogerBase("SELECT * FROM THEME WHERE ID_THEME = '" + idTheme + "'");
            List<String> row = resultats.get(0);
            if(idTheme == Integer.parseInt(row.get(0))){
                int idProp = Integer.parseInt(row.get(1)); //Il peut être null
                String intitule = row.get(2);
                String description = row.get(3);
                theme = new Theme(idTheme, idProp, intitule, description);
            }else{
                //Erreur de selection ou theme non existant
            }
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return theme;
    }
    
    public Commentaire recupererCommentaire(int idCommentaire){
        Commentaire commentaire = null;
        if(commentaires.containsKey(idCommentaire))
            return commentaires.get(idCommentaire);
        try{
            List<List<String>> resultats = acces.interrogerBase("SELECT * FROM COMMENTAIRE WHERE ID_COMMENTAIRE = '"+ idCommentaire + "'");
            List<String> row = resultats.get(0);
            if(idCommentaire == Integer.parseInt(row.get(0))){
                   int idAuteur = Integer.parseInt(row.get(1));
                   int idArticle = Integer.parseInt(row.get(2));
                   String intitule = row.get(3);
                   String contenu = row.get(4);
                   Calendar datePubli = Calendar.getInstance(); //A MODIFIER
                   Calendar dateModif = Calendar.getInstance(); //A MODIFIER
                   commentaire = new Commentaire(idCommentaire, idAuteur, idArticle, intitule, contenu, datePubli, dateModif);
            }else{
                //Erreur de selection ou commentaire inexistant

            }
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return commentaire;
    }
    
    public Message recupererMessage(int idMessage){
        Message message = null;
        if(messages.containsKey(idMessage))
            return messages.get(idMessage);
        try{
            List<List<String>> resultats = acces.interrogerBase("SELECT * FROM COMMENTAIRE WHERE ID_MESSAGE = '"+ idMessage + "'");
            List<String> row = resultats.get(0);
            if(idMessage == Integer.parseInt(row.get(0))){
                   int idAuteur = Integer.parseInt(row.get(1));
                   String intitule = row.get(3);
                   String contenu = row.get(4);
                   Calendar dateEnvoi = Calendar.getInstance(); //A MODIFIER
                   message = new Message(idMessage, idAuteur, intitule, contenu, dateEnvoi);
            }else{
                //Erreur de selection ou commentaire inexistant

            }
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return message;
    }
    
    public Correspondance recupererCorrespondance(int idMessage, int idDestinataire){
        Correspondance correspondance = null;
        return correspondance;
    }
    
    public void retirerUtilisateurListe(String nom)
    {
        for(Entry<Integer, Utilisateur> u : utilisateurs.entrySet())
        {
            if(u.getValue().getNom().equals(nom))
            {
                utilisateurs.remove(u.getKey());
            }
        }
    }
}
