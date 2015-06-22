/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.modele.accesbd;

import ihmpts2appliveille.modele.Cryptage;
import iutlr.dutinfo.bd.AccesBD;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe d'enregistrement d'informations dans la base de données
 * @author vpivet
 */
public class EnregistrementDonnees {
    private AccesBD acces;
    private Donnees donnees;

    /**
     * Constructeur de la classe
     * @param donnees La classe de maintient des données dans l'application
     */
    public EnregistrementDonnees(Donnees donnees) {
        this.acces = new AccesBD();
        this.donnees = donnees;
    }
    
    /**
     * Ajout d'un utilisateur à la base de données
     * @param nom Le nom du nouvel utilisateur
     * @param identifiant L'identifiant du nouvel utilisateur
     * @param mdp Le mot de passe du nouvel utilisateur
     * @param typeProfil Le type de profil du nouvel utlisateur
     */
    public void ajoutUtilisateur(String nom, String identifiant, String mdp, String typeProfil){
        String mdpCrypte;
        mdpCrypte = Cryptage.getEncodedPassword(mdp);
        try{
            acces.mettreAjourBase("INSERT INTO utilisateur(ID_UTILISATEUR,NOM,NOTE,NBCONN,NBCOMM,NBARTICLE,IDENTIFIANT,MDP,TYPE_PROFIL,ETAT) VALUES ((select NVL(max(ID_UTILISATEUR), 0)+1 from utilisateur),'"+ nom +"',NULL,0,0,0,'"+identifiant +"','"+mdpCrypte+"','"+typeProfil+"','N')");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    /**
     * Ajout d'un thème dans la base de données
     * @param intitule L'intitulé du thème
     * @param description La description du thème
     */
    public void ajoutTheme(String intitule, String description){
        try{
            intitule = intitule.replaceAll("'", "''");
            description = description.replaceAll("'", "''");
            acces.mettreAjourBase("INSERT INTO theme(ID_THEME, ID_PROP, INTITULE, DESCRIPTION) VALUES ((select NVL(max(ID_THEME), 0)+1 from theme), NULL, '" + intitule + "', '" + description + "')");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    /**
     * Ajout d'un commentaire dans la base de données
     * @param idArticle L'identifiant de l'article concerné par le commentaire
     * @param idUtilisateur L'identifiant de l'auteur du commentaire
     * @param intitutle L'intitulé du commentaire 
     * @param contenu Le contenu du commentaire ajouté
     */
    public void ajouterCommenatire(int idArticle, int idUtilisateur, String intitutle, String contenu)
    {
        try{
            intitutle = intitutle.replaceAll("'", "''");
            contenu = contenu.replaceAll("'", "''");           
            acces.mettreAjourBase("INSERT INTO commentaire VALUES((select NVL(max(ID_COMMENTAIRE), 0)+1 from commentaire), " + idUtilisateur + ", " + idArticle + ", '" + intitutle + "', '" + contenu + "', SYSDATE, NULL, 'V')");
            acces.mettreAjourBase("UPDATE article set NB_COMM_ART=(select COUNT(*) FROM commentaire where id_article=" + idArticle + ") where id_article = " + idArticle);
            acces.mettreAjourBase("UPDATE utilisateur set NBCOMM=(select COUNT(*) FROM commentaire where id_auteur=" + idUtilisateur + ") where id_utilisateur= " + idUtilisateur);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    /**
     * Suppression d'une correspondace de la base de données
     * @param idMessage L'identifiant du message à supprimer
     * @param idUtilisateur L'identifiant du destinataire
     */
    public void supprimerCorrespondance(int idMessage, int idUtilisateur)
    {
        try{
            acces.mettreAjourBase("DELETE FROM CORRESPONDANCE WHERE ID_MESSAGE=" + idMessage + " AND ID_DESTINATAIRE=" + idUtilisateur);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    /**
     * Suppression d'un thème de la base de données
     * @param idTheme L'identifiant du thème à supprimer
     */
    public void supprimerTheme(int idTheme){
        try{
            acces.mettreAjourBase("DELETE FROM THEME WHERE ID_THEME='" + idTheme + "'");
            donnees.supprimerTheme(idTheme);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    /**
     * Suppression d'un utilisateur de la base de données
     * @param idUtilisateur L'identifiant de l'utilisateur à supprimer
     */
    public void supprimerUtilisateur(int idUtilisateur)
    {
        try{
            acces.mettreAjourBase("DELETE FROM utilisateur WHERE ID_UTILISATEUR='" + idUtilisateur + "'");
            donnees.supprimerUtilisateur(idUtilisateur);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    /**
     * Suppression d'un article de la base de données et changement du nombre d'article de son auteur
     * @param idArticle identifiant de l'article à supprimer
     * @param idAuteur identifiant de l'auteur de l'article
     */
    public void supprimerArticle(int idArticle, int idAuteur)
    {
        try{
            acces.mettreAjourBase("DELETE FROM article WHERE ID_ARTICLE='" + idArticle + "'");
            acces.mettreAjourBase("UPDATE UTILISATEUR SET NBARTICLE=(select COUNT(*) from article where id_auteur=" + idAuteur + ") WHERE ID_UTILISATEUR = " + idAuteur);
            donnees.supprimerArticle(idArticle);
        }catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    /**
     * Suppression d'un commentaire de la base de données et changement du nombre de commentaire de son auteur
     * @param idCommentaire identifiant du commentaire à supprimer
     * @param idArticle Identifiant de l'article concerné par le commentaire
     * @param idUtilisateur Identifiant de l'auteur du commentaire
     */
    public void supprimerCommentaire(int idCommentaire, int idArticle, int idUtilisateur)
    {
        try{       
            acces.mettreAjourBase("DELETE commentaire where id_commentaire = " + idCommentaire);
            acces.mettreAjourBase("UPDATE article set NB_COMM_ART=(select COUNT(*) FROM commentaire where id_article=" + idArticle + ") where id_article = " + idArticle);
            acces.mettreAjourBase("UPDATE utilisateur set NBCOMM=(select COUNT(*) FROM commentaire where id_auteur=" + idUtilisateur + ") where id_utilisateur= " + idUtilisateur);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void supprimerMessages(int idUtilisateur)
    {
        try{
            acces.mettreAjourBase("DELETE correspondance where id_message IN (select id_message from message where id_auteur = " + idUtilisateur + ")");
            acces.mettreAjourBase("DELETE correspondance where id_destinataire = " + idUtilisateur);
            acces.mettreAjourBase("DELETE message where id_auteur = " + idUtilisateur);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void supprimerCommentaires(int idUtilisateur)
    {
        try{
            acces.mettreAjourBase("DELETE commentaire where id_auteur = " + idUtilisateur);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    /**
     * Mise à jour du propriétaire d'un thème dans la base de données
     * @param idTheme identifiant du thème
     * @param idUtilisateur Identifiant du propriétaire
     */
    public void attribuerTheme(int idTheme, int idUtilisateur)
    {
        try{
            acces.mettreAjourBase("UPDATE THEME SET ID_PROP='" + idUtilisateur + "'WHERE ID_THEME='" + idTheme + "'");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    /**
     * Mise à jour d'un thème pour lui supprimer son propriétaire
     * @param idTheme L'identifiant du thème
     */
    public void depossserderTheme(int idTheme)
    {
        try{
            acces.mettreAjourBase("UPDATE THEME SET ID_PROP=NULL WHERE ID_THEME='" + idTheme + "'");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void setDescriptionTheme(int idTheme, String description)
    {
        try{
            description = description.replaceAll("'", "''");
            acces.mettreAjourBase("UPDATE THEME SET DESCRIPTION='" + description + "'WHERE ID_THEME='" + idTheme + "'");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void setUtilisateurConnecte(int idUtilisateur)
    {
        try{
            acces.mettreAjourBase("UPDATE utilisateur SET ETAT='C', NBCONN=1+(select NBCONN FROM UTILISATEUR WHERE ID_UTILISATEUR=" + idUtilisateur + ") WHERE ID_UTILISATEUR=" + idUtilisateur);
        } catch (SQLException ex) {
            System.err.println("Erreur utilisateur connecte : " + ex.getMessage());
        }
    }
    
    public void setUtilisateurDeconnecte(int idUtilisateur)
    {
        try{
            acces.mettreAjourBase("UPDATE utilisateur SET ETAT='N' WHERE ID_UTILISATEUR=" + idUtilisateur + "");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    /**
     * Ajout d'un article dans la base de données
     * @param idUtilisateur Identifiant de l'auteur
     * @param idTheme Identifiant de son thème
     * @param titre Titre de l'article à ajouté
     * @param content Contenu de l'article à ajouter
     */
    public void ajoutArticle(int idUtilisateur, int idTheme, String titre, String content)
    {
        try{
            titre = titre.replaceAll("'", "''");
            content = content.replaceAll("'", "''");
            acces.mettreAjourBase("INSERT INTO ARTICLE(ID_ARTICLE,ID_AUTEUR,ID_THEME,NB_COMM_ART,INTITULE,CONTENU,DATEPUBLI,DATEMODIF,NOTE,VISIBLE) VALUES ((select NVL(max(ID_ARTICLE), 0)+1 from ARTICLE)," + idUtilisateur + ", " + idTheme + ", 0, '"+ titre + "', '" + content + "', SYSDATE, NULL, NULL, 'V')");
            acces.mettreAjourBase("UPDATE UTILISATEUR SET NBARTICLE=(select COUNT(*) from article where id_auteur=" + idUtilisateur + ") WHERE ID_UTILISATEUR = " + idUtilisateur);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    /**
     * Ajout d'un message dans la base de données
     * @param idUtilisateur l'identifiant de l'auteur du message
     * @param intitule L'intitule du message à ajouté
     * @param content Le contenu du message à ajouté
     */
    public void ajouterMessage(int idUtilisateur, String intitule, String content)
    {
        intitule = intitule.replaceAll("'", "''");
        content = content.replaceAll("'", "''");
        try{
            acces.mettreAjourBase("INSERT INTO MESSAGE VALUES ((select NVL(max(ID_MESSAGE), 0)+1 from message), " + idUtilisateur + ", '" + intitule + "', '" + content + "', SYSDATE)");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    /**
     * Ajout d'une correspondace dans la base de données
     * @param idMessage L'identifiant du message à faire correspondre
     * @param idDestinataire L'identifiant du destinataire
     */
    public void ajouterCorrespondance(int idMessage, int idDestinataire)
    {
        System.out.println("Ajout correspondance : " + idMessage + ", " + idDestinataire);
        try{
            acces.mettreAjourBase("INSERT INTO CORRESPONDANCE VALUES (" + idMessage + ", " + idDestinataire + ")");
        } catch (SQLException ex) {
            Logger.getLogger(EnregistrementDonnees.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Modification d'un article dans la base de données
     * @param idArticle L'identifiant de l'article à modifier
     * @param titre Le titre de l'article à modifier
     * @param content Le contenu de l'article à modifier
     */
    public void modifierArticle(int idArticle, String titre, String content)
    {
        try{
            titre = titre.replaceAll("'", "''");
            content = content.replaceAll("'", "''");
            acces.mettreAjourBase("UPDATE ARTICLE SET INTITULE='" + titre + "', CONTENU='" + content + "', DATEMODIF=SYSDATE WHERE ID_ARTICLE=" + idArticle);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    /**
     * Modification d'un commentaire dans la base de données
     * @param idCommentaire L'identifiant du commentaire à modifier
     * @param contenu Le contenu du commentaire à modifier
     */
    public void modifierCommentaire(int idCommentaire, String contenu)
    {
        try{
            contenu = contenu.replaceAll("'", "''");
            acces.mettreAjourBase("UPDATE COMMENTAIRE SET CONTENU='" + contenu + "', DATEMODIF=SYSDATE WHERE ID_COMMENTAIRE=" + idCommentaire);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    /**
     * Modification de la note d'un article
     * @param idArticle Identifiant de l'article dont la note est modifié
     * @param note La note
     */
    public void updateNote(int idArticle, float note)
    {
        try{
            acces.mettreAjourBase("UPDATE ARTICLE SET NOTE=" + note + "WHERE ID_ARTICLE=" + idArticle);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
