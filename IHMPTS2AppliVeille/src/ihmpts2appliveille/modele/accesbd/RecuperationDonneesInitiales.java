/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.modele.accesbd;

import ihmpts2appliveille.modele.Droits;
import ihmpts2appliveille.modele.Statut;
import ihmpts2appliveille.modele.accesbd.entites.Article;
import ihmpts2appliveille.modele.accesbd.entites.Theme;
import ihmpts2appliveille.modele.accesbd.entites.Utilisateur;
import iutlr.dutinfo.bd.AccesBD;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author x1QG1x
 */
public class RecuperationDonneesInitiales {
    private AccesBD acces;
    private Donnees donnees;
    
    public RecuperationDonneesInitiales(Donnees donnees){
        this.acces = new AccesBD();
        this.donnees = donnees;
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
                        etat = Statut.DECONNECTE;
                        break;
                }
                donnees.ajouterUtilisateur(new Utilisateur(idUtilisateur, nom, note, nbConn, nbComm, nbArticle, identifiant, mdp, typeProfil, etat));        
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return donnees.getUtilisateurs();
    }
    
    public Utilisateur recupererUtilisateur(int id)
    {
        Utilisateur utilisateur = null;
        try {
            List<List<String>> resultats = acces.interrogerBase("select id_utilisateur, nom, note, nbconn, nbcomm, nbarticle, identifiant, mdp, type_profil, etat from utilisateur where id_utilisateur=" + id);
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
                    etat = Statut.DECONNECTE;
                    break;
            }
            utilisateur = new Utilisateur(idUtilisateur, nom, note, nbConn, nbComm, nbArticle, identifiant, mdp, typeProfil, etat);
            donnees.ajouterUtilisateur(new Utilisateur(idUtilisateur, nom, note, nbConn, nbComm, nbArticle, identifiant, mdp, typeProfil, etat));
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
        if(donnees.containsArticle(idArticle))
            return donnees.getArticle(idArticle);
        try{
            List<List<String>> resultats = acces.interrogerBase("SELECT * FROM ARTICLE WHERE ID_ARTICLE ='" + idArticle + "'");
            if(resultats.isEmpty())
            {
                return null;
            }
            List<String> row = resultats.get(0);
            if(idArticle == Integer.parseInt(row.get(0)))
            {
                int idAuteur = Integer.parseInt(row.get(1));
                int idTheme = Integer.parseInt(row.get(2));
                int nbCommArt = Integer.parseInt(row.get(3));
                String intitule = row.get(4);
                String contenu = row.get(5);
                System.out.println(row.get(6));
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s");
                Date datePubli = null; 
                try {
                    datePubli = df.parse(row.get(6)); // A MODIFIER
                } catch (ParseException ex) {
                    System.err.println(ex.getMessage());
                }
                Date dateModif = null; // A MODIFIER
                if(row.get(7) != null)
                {
                   try {
                        dateModif = df.parse(row.get(7)); // A MODIFIER
                    } catch (ParseException ex) {
                        System.err.println(ex.getMessage());
                    } 
                }
                float note = 0.0f;
                boolean visible = true;
                switch(row.get(9)){
                    case "V" : visible = true; break ; 
                    case "N" : visible = false; break;
                    default : break ;
                }
                article = new Article(idArticle, idAuteur, idTheme, nbCommArt, intitule, contenu, datePubli, dateModif, note, visible);
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
                donnees.ajouterTheme(new Theme(idTheme, idProp, intitule, description));
            }
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return donnees.getThemes();
    }
    
    public Theme recupererTheme(int idTheme){
        Theme theme = null;
        if(donnees.containsTheme(idTheme))
            return donnees.getTheme(idTheme);
        try{
            List<List<String>> resultats = acces.interrogerBase("SELECT * FROM THEME WHERE ID_THEME = '" + idTheme + "'");
            if(resultats.isEmpty())
            {
                return null;
            }
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
    
    public Theme recupererThemeUtilisateur(int idUtilisateur)
    {
        Theme theme = null;
        for(Theme t : donnees.getThemes().values())
        {
            if(t.getIdProp() == idUtilisateur)
                return t;
        }
        try{
            List<List<String>> resultats = acces.interrogerBase("SELECT * FROM THEME WHERE ID_PROP = '" + idUtilisateur + "'");
            if(resultats.isEmpty())
            {
                return null;
            }
            List<String> row = resultats.get(0);
            int idTheme = Integer.parseInt(row.get(0));
            int idProp = Integer.parseInt(row.get(1));
            String intitule = row.get(2);
            String description = row.get(3);
            theme = new Theme(idTheme, idProp, intitule, description);
            if(!donnees.containsTheme(idTheme))
                donnees.ajouterTheme(theme);
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return theme;
    }

    public List<Article> recupererArticles() {
        List<Article> articlesOrdonnes = new ArrayList<>();
        try{
            List<List<String>> resultats = acces.interrogerBase("SELECT id_article,id_auteur,id_theme, nb_comm_art, intitule, contenu, datepubli, datemodif, note, visible FROM ARTICLE ORDER BY 7 DESC");
            if(resultats.isEmpty())
            {
                return null;
            }
            for(List<String> row : resultats)
            {
                int idArticle = Integer.parseInt(row.get(0));
                System.out.println(row.get(0));
                int idAuteur = Integer.parseInt(row.get(1));
                int idTheme = Integer.parseInt(row.get(2));
                int nbCommArt = Integer.parseInt(row.get(3));
                String intitule = row.get(4);
                String contenu = row.get(5);
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s");
                Date datePubli = null; 
                try {
                    datePubli = df.parse(row.get(6)); // A MODIFIER
                } catch (ParseException ex) {
                    System.err.println(ex.getMessage());
                }
                Date dateModif = null; // A MODIFIER
                if(row.get(7) != null)
                {
                   try {
                        dateModif = df.parse(row.get(7)); // A MODIFIER
                    } catch (ParseException ex) {
                        System.err.println(ex.getMessage());
                    } 
                }
                float note = 0.0f;
                boolean visible = true;
                switch(row.get(9)){
                    case "V" : visible = true; break ; 
                    case "N" : visible = false; break;
                    default : break ;
                }
                donnees.ajouterArticle(new Article(idArticle, idAuteur, idTheme, nbCommArt, intitule, contenu, datePubli, dateModif, note, visible));
                articlesOrdonnes.add(new Article(idArticle, idAuteur, idTheme, nbCommArt, intitule, contenu, datePubli, dateModif, note, visible));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return articlesOrdonnes;
    }
}
