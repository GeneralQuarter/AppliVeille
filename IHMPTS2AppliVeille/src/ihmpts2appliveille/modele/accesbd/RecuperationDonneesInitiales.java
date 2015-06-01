/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.modele.accesbd;

import ihmpts2appliveille.modele.Droits;
import ihmpts2appliveille.modele.Statut;
import ihmpts2appliveille.modele.accesbd.entites.Utilisateur;
import iutlr.dutinfo.bd.AccesBD;
import java.sql.SQLException;
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
    private Map<Integer, Utilisateur> utilisateurs;
    
    public RecuperationDonneesInitiales(){
        this.acces = new AccesBD();
    }
    
    public Utilisateur recupererUtilisateurEnConnection(String identifiant)
    {
        Utilisateur utilisateur = null;
        try {
            List<List<String>> resultats = acces.interrogerBase("select id_utilisateur, nom, note, nbconn, nbcomm, nbarticle, identifiant, mdp, type_profil, etat from utilisateur where identifiant='" + identifiant + "'");
            List<String> row = resultats.get(0);
            int idUtilisateur = Integer.parseInt(row.get(0));
            String nom = row.get(1);
            System.out.println(row.get(2));
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
}
