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
 *
 * @author vpivet
 */
public class EnregistrementDonnees {
    private AccesBD acces;
    private Donnees donnees;

    public EnregistrementDonnees(Donnees donnees) {
        this.acces = new AccesBD();
        this.donnees = donnees;
    }
    
    public void ajoutUtilisateur(String nom, String identifiant, String mdp, String typeProfil){
        String mdpCrypte;
        mdpCrypte = Cryptage.getEncodedPassword(mdp);
        try{
            acces.mettreAjourBase("INSERT INTO utilisateur(ID_UTILISATEUR,NOM,NOTE,NBCONN,NBCOMM,NBARTICLE,IDENTIFIANT,MDP,TYPE_PROFIL,ETAT) VALUES ((select NVL(max(ID_UTILISATEUR), 0)+1 from utilisateur),'"+ nom +"',NULL,0,0,0,'"+identifiant +"','"+mdpCrypte+"','"+typeProfil+"','N')");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void ajoutTheme(String intitule, String description){
        try{
            description = description.replaceAll("'", "''");
            acces.mettreAjourBase("INSERT INTO theme(ID_THEME, ID_PROP, INTITULE, DESCRIPTION) VALUES ((select NVL(max(ID_THEME), 0)+1 from theme), NULL, '" + intitule + "', '" + description + "')");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
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
    
    public void supprimerCorrespondance(int idMessage, int idUtilisateur)
    {
        try{
            acces.mettreAjourBase("DELETE FROM CORRESPONDANCE WHERE ID_MESSAGE=" + idMessage + " AND ID_DESTINATAIRE=" + idUtilisateur);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void supprimerTheme(int idTheme){
        try{
            acces.mettreAjourBase("DELETE FROM THEME WHERE ID_THEME='" + idTheme + "'");
            donnees.supprimerTheme(idTheme);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void supprimerUtilisateur(int idUtilisateur)
    {
        try{
            acces.mettreAjourBase("DELETE FROM utilisateur WHERE ID_UTILISATEUR='" + idUtilisateur + "'");
            donnees.supprimerUtilisateur(idUtilisateur);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
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
    
    public void attribuerTheme(int idTheme, int idUtilisateur)
    {
        try{
            acces.mettreAjourBase("UPDATE THEME SET ID_PROP='" + idUtilisateur + "'WHERE ID_THEME='" + idTheme + "'");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
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
    
    public void ajouterCorrespondance(int idMessage, int idDestinataire)
    {
        System.out.println("Ajout correspondance : " + idMessage + ", " + idDestinataire);
        try{
            acces.mettreAjourBase("INSERT INTO CORRESPONDANCE VALUES (" + idMessage + ", " + idDestinataire + ")");
        } catch (SQLException ex) {
            Logger.getLogger(EnregistrementDonnees.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
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
    
    public void modifierCommentaire(int idCommentaire, String contenu)
    {
        try{
            contenu = contenu.replaceAll("'", "''");
            acces.mettreAjourBase("UPDATE COMMENTAIRE SET CONTENU='" + contenu + "', DATEMODIF=SYSDATE WHERE ID_COMMENTAIRE=" + idCommentaire);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public void updateNote(int idArticle, float note)
    {
        try{
            acces.mettreAjourBase("UPDATE ARTICLE SET NOTE=" + note + "WHERE ID_ARTICLE=" + idArticle);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
