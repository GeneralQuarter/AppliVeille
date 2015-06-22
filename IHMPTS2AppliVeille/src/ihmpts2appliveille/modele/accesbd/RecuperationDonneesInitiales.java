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
import ihmpts2appliveille.modele.accesbd.entites.Message;
import ihmpts2appliveille.modele.accesbd.entites.Theme;
import ihmpts2appliveille.modele.accesbd.entites.Utilisateur;
import iutlr.dutinfo.bd.AccesBD;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe de récupération de données dans la base de données
 * @author x1QG1x
 */
public class RecuperationDonneesInitiales {
    private AccesBD acces;
    private Donnees donnees;
    
    /**
     * Constructeur de la classe
     * @param donnees 
     */
    public RecuperationDonneesInitiales(Donnees donnees){
        this.acces = new AccesBD();
        this.donnees = donnees;
    }
    
    /**
     * Récupération des utilisateurs dans la base de données
     * @return Une liste d'utilisateur
     */
    public Map<Integer, Utilisateur> recupererUtilisateurs(){
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
    
    /**
     * Récupération d'un utilisateur dans la base de données
     * @param id L'identifiant de l'utilisateur
     * @return Une instance de la classe utilisateur
     */
    public Utilisateur recupererUtilisateur(int id){
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
    
    /**
     * Récupération de l'utilisateur connecté dans l'application dans la base de données
     * @param identifiant de l'utilisateur connecté
     * @return Une instance de la classe Utilisateur
     */
    public Utilisateur recupererUtilisateurEnConnection(String identifiant){
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
    
    /**
     * Récupération d'un article dans la base de données
     * @param idArticle L'identifiant de l'article à récupérer
     * @return Une instance de la classe article
     */
    public Article recupererArticle(int idArticle){
        Article article = null;
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
                float note = -1.0f;
                if(row.get(8) != null)
                {
                    note = Float.parseFloat(row.get(8));
                }
                boolean visible = true;
                switch(row.get(9)){
                    case "V" : visible = true; break ; 
                    case "N" : visible = false; break;
                    default : break ;
                }
                article = new Article(idArticle, idAuteur, idTheme, nbCommArt, intitule, contenu, datePubli, dateModif, note, visible);
                donnees.ajouterArticle(article);
            }else{
                //Erreur de selection ou article non existant
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return article;
    }
    
    /**
     * Récupération des thèmes dans la base de données
     * @return Une liste de thèmes
     */
    public Map<Integer, Theme> recupererThemes(){
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
    
    /**
     * Récupération d'un thème dans la base de données
     * @param idTheme L'identifiant du thème à récupérer
     * @return Une instance de la classe Theme
     */
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
    
    /**
     * Récupération du thème de l'utilisateur connecté dans la base de données
     * @param idUtilisateur L'identifiant de l'utilisateur connecté
     * @return Une instance de la classe Theme
     */
    public Theme recupererThemeUtilisateur(int idUtilisateur){
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

    /**
     * Récupération des articles dans la base de données dans un bon ordre
     * @return Une liste d'articles
     */
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
                int idAuteur = Integer.parseInt(row.get(1));
                int idTheme = Integer.parseInt(row.get(2));
                int nbCommArt = Integer.parseInt(row.get(3));
                String intitule = row.get(4);
                String contenu = row.get(5);
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s");
                Date datePubli = null; 
                try {
                    datePubli = df.parse(row.get(6));
                } catch (ParseException ex) {
                    System.err.println(ex.getMessage());
                }
                Date dateModif = null;
                if(row.get(7) != null)
                {
                   try {
                        dateModif = df.parse(row.get(7));
                    } catch (ParseException ex) {
                        System.err.println(ex.getMessage());
                    } 
                }
                float note = -1.0f;
                if(row.get(8) != null)
                {
                    note = Float.parseFloat(row.get(8));
                }
                boolean visible = true;
                switch(row.get(9)){
                    case "V" : visible = true; break; 
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
    
    /**
     * Récupération des commentaires dans la base de données
     * @param idArticle l'identifiant d'un article dont on souhaite avoir les commentaires
     * @return un liste de commentaire
     */
    public List<Commentaire> recupererCommentaires(int idArticle){
        List<Commentaire> res = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.s");
        try{
            List<List<String>> resultats = acces.interrogerBase("SELECT ID_COMMENTAIRE, ID_AUTEUR, ID_ARTICLE, INTITULE, CONTENU, DATEPUBLI, DATEMODIF, VISIBLE FROM COMMENTAIRE WHERE ID_ARTICLE=" + idArticle + " ORDER BY 6 DESC");
            System.out.println("Nombre de commentaires : " + resultats.size());
            if(resultats.isEmpty())
                return null;
            for(List<String> row : resultats)
            {
                int idCommentaire = Integer.parseInt(row.get(0));
                int idAuteur = Integer.parseInt(row.get(1));
                idArticle = Integer.parseInt(row.get(2));
                String intitule = row.get(3);
                String contenu = row.get(4);
                Date datePubli = null;
                try {
                    datePubli = df.parse(row.get(5));
                } catch (ParseException ex) {
                    System.err.println(ex.getMessage());
                }
                Date dateModif = null;
                if(row.get(6) != null)
                {
                    try {
                        dateModif = df.parse(row.get(6));
                    } catch (ParseException ex) {
                        System.err.println(ex.getMessage());
                    }
                }
                boolean visible = true;
                switch(row.get(7))
                {
                    case "V": visible = true;break;
                    case "N": visible = false;break;
                }
                res.add(new Commentaire(idCommentaire, idAuteur, idArticle, intitule, contenu, datePubli, dateModif, visible));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return res;
    }
    
    /**
     * Recupérer un message dans la base de données
     * @param idMessage l'identifiant du message à récupérer
     * @return Une instance de la classe Message
     */
    public Message recupererMessage(int idMessage){
        Message m = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            List<List<String>> resultats = acces.interrogerBase("SELECT * FROM MESSAGE WHERE ID_MESSAGE=" + idMessage);
            if(resultats.isEmpty())
                return null;
            List<String> row = resultats.get(0);
            int idAuteur = Integer.parseInt(row.get(1));
            String objet = row.get(2);
            String contenu = row.get(3);
            Date dateEnvoi = null;
            try {
                dateEnvoi = df.parse(row.get(4));
            } catch (ParseException ex) {
                Logger.getLogger(RecuperationDonneesInitiales.class.getName()).log(Level.SEVERE, null, ex);
            }
            m = new Message(idMessage, idAuteur, objet, contenu, dateEnvoi);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return m;
    }
    
    /**
     * Récupération les messages d'un utilisateur dans la base de données
     * @param idUtilisateur l'identifiant de l'utilisateur dont on souhaite récupérer les messages
     * @return une liste de messages
     */
    public Message recupererMessageEnvoye(int idUtilisateur){
        Message m = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            List<List<String>> resultats = acces.interrogerBase("SELECT ID_MESSAGE, ID_AUTEUR, INTITULE, CONTENU, DATEENVOI FROM MESSAGE WHERE ID_AUTEUR=" + idUtilisateur + " ORDER BY ID_MESSAGE DESC");
            if(resultats.isEmpty())
                return null;
            List<String> row = resultats.get(0);
            int idMessage = Integer.parseInt(row.get(0));
            int idAuteur = Integer.parseInt(row.get(1));
            String objet = row.get(2);
            String contenu = row.get(3);
            Date dateEnvoi = null;
            try {
                dateEnvoi = df.parse(row.get(4));
            } catch (ParseException ex) {
                Logger.getLogger(RecuperationDonneesInitiales.class.getName()).log(Level.SEVERE, null, ex);
            }
            m = new Message(idMessage, idAuteur, objet, contenu, dateEnvoi);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return m; 
    }
    
    /**
     * Récupérer la messagerie d'un utilisateur
     * @param idUtilisateur Identifiant de l'utilisateur dont on souhaite obtenir la messagerie
     * @return Une liste de correspondances
     */
    public List<Message> recupererBoite(int idUtilisateur){
        List<Message> m = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            List<List<String>> resultats = acces.interrogerBase("SELECT * FROM CORRESPONDANCE WHERE ID_DESTINATAIRE=" + idUtilisateur);
            if(resultats.isEmpty())
                return null;
            System.out.println("Message reçu !");
            for(List<String> row : resultats)
            {
                m.add(recupererMessage(Integer.parseInt(row.get(0))));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return m; 
    }
    
    /**
     * Récupération des résultats d'un utilisateur
     * @param idUtilisateur L'identifiant de l'utilisateur dont on souhaite récupérer les résultats
     * @return Le cumul des notes des trois critères
     */
    public double recupererNote(int idUtilisateur){
        double noteMoyenneArticle = -1;
        double comparaisonNombreArticle = -1;
        double comparaisonNombreCommArt = -1;
        
        double nombreArticleUtilisateur = -1;
        double nombreMoyenArticle = -1;
        double nombreArticle = -1;
        double nombreCommentaireUtilisateur = -1;
        try {
            List<List<String>> resultats = acces.interrogerBase("SELECT AVG(NOTE) FROM ARTICLE WHERE ID_AUTEUR=" + idUtilisateur);
            if(resultats == null)
                noteMoyenneArticle = 0;
            List<String> row = resultats.get(0);
            if(row.get(0) == null)
                noteMoyenneArticle = 0;
            else
                noteMoyenneArticle = Double.parseDouble(row.get(0));
            
            resultats = acces.interrogerBase("SELECT NBARTICLE FROM UTILISATEUR WHERE ID_UTILISATEUR=" + idUtilisateur);
            if(resultats == null)
                nombreArticleUtilisateur = 0;
            row = resultats.get(0);
            if(row.get(0) == null)
                nombreArticleUtilisateur = 0;
            else
                nombreArticleUtilisateur = Double.parseDouble(row.get(0));
            
            resultats = acces.interrogerBase("SELECT AVG(NBARTICLE) FROM UTILISATEUR");
            if(resultats == null)
                nombreMoyenArticle = 0;
            row = resultats.get(0);
            if(row.get(0) == null)
                nombreMoyenArticle = 0;
            else
                nombreMoyenArticle = Double.parseDouble(row.get(0));
            
            resultats = acces.interrogerBase("SELECT COUNT(*) FROM ARTICLE");
            if(resultats == null)
                nombreArticle = 0;
            row = resultats.get(0);
            if(row.get(0) == null)
                nombreArticle = 0;
            else
                nombreArticle = Double.parseDouble(row.get(0));
            
            resultats = acces.interrogerBase("SELECT COUNT(*) FROM COMMENTAIRE WHERE ID_AUTEUR = " + idUtilisateur);
            if(resultats == null)
                nombreCommentaireUtilisateur = 0;
            row = resultats.get(0);
            if(row.get(0) == null)
                nombreCommentaireUtilisateur = 0;
            else
                nombreCommentaireUtilisateur = Double.parseDouble(row.get(0));
            
            System.out.println("Nombre d'article utilisateur : " + nombreArticleUtilisateur);
            System.out.println("Nombre d'article moyen : " + nombreMoyenArticle);
            if(nombreArticleUtilisateur == 0)
                comparaisonNombreArticle = 0;
            else if(nombreMoyenArticle == 0)
                comparaisonNombreArticle = 0;
            else{
                comparaisonNombreArticle = nombreArticleUtilisateur - nombreMoyenArticle;
                if(comparaisonNombreArticle >= 3)
                    comparaisonNombreArticle = 5;
                else if(comparaisonNombreArticle >= 2)
                    comparaisonNombreArticle = 4;
                else if(comparaisonNombreArticle >= 1)
                    comparaisonNombreArticle = 3;
                else if(comparaisonNombreArticle >= -1)
                    comparaisonNombreArticle = 2;
                else if(comparaisonNombreArticle >= -2)
                    comparaisonNombreArticle = 1;
                else
                    comparaisonNombreArticle = 0;
            }
            
            if(nombreArticle != 0)
            {
                comparaisonNombreCommArt = (nombreCommentaireUtilisateur / (nombreArticle / 5.0))*5.0;
                if(comparaisonNombreCommArt > 5.0)
                    comparaisonNombreCommArt = 5.0;
            }
            else
                comparaisonNombreCommArt = 0;
        }catch(SQLException ex){
            
        }
        System.out.println("Note moyenne article : " + noteMoyenneArticle + "\nComparaison nombre article : " + comparaisonNombreArticle + "\nComparaison nombre comm article : " + comparaisonNombreCommArt);
        return noteMoyenneArticle + comparaisonNombreArticle + comparaisonNombreCommArt;
    }
}
