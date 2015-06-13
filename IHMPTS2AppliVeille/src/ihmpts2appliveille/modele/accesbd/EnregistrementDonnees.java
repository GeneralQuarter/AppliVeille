/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.modele.accesbd;

import ihmpts2appliveille.modele.Cryptage;
import iutlr.dutinfo.bd.AccesBD;
import java.sql.SQLException;

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
    
    public void setUtilisateurConnecte(int idUtilisateur)
    {
        try{
            acces.mettreAjourBase("UPDATE utilisateur SET ETAT='C' WHERE ID_UTILISATEUR=" + idUtilisateur + "");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
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
    
    //public void ajoutArticle()
}
