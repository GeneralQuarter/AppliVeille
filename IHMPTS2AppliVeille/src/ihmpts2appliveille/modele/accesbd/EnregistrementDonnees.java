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

    public EnregistrementDonnees() {
        this.acces = new AccesBD();
    }
    
    public void ajoutUtilisateur(String nom, String identifiant, String mdp, String typeProfil){
        String mdpCrypte;
        mdpCrypte = Cryptage.getEncodedPassword(mdp);
        try{
            acces.interrogerBase("INSERT INTO utilisateur(ID_UTILISATEUR,NOM,NOTE,NBCONN,NBCOMM,NBARTICLE,IDENTIFIANT,MDP,TYPE_PROFIL,ETAT) VALUES ((select NVL(max(ID_UTILISATEUR), 0)+1 from utilisateur),'"+ nom +"',NULL,0,0,0,'"+identifiant +"','"+mdpCrypte+"','"+typeProfil+"','N')");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    //public void ajoutArticle()
}
