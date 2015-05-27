/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.modele;

/**
 *
 * @author x1QG1x
 */
public class Session {
    private String login;
    private String mdp;
    private String nom;
    private String prenom;
    private Droits droits;
    private Statut statut;
    
    public Session()
    {
        this.statut = Statut.DECONNECTE;
    }
    
    public void connection(String login, String mdp)
    {
        // BDD
        // Recupération du nom/prenom
        // Recupération droits
        if(login.equals("qgangler") && mdp.equals("password")){
            this.login = login;
            this.mdp = mdp;
            nom = "Pivet";
            prenom = "Valentin";
            droits = Droits.ETUDIANT;
            statut = Statut.CONNECTE;
        }else{
            statut = Statut.ERREUR_IDENTIFIANTS;
        }
    }
    
    public void deconnexion()
    {
        statut = Statut.DECONNECTE;
        login = null;
        mdp = null;
        nom = null;
        prenom = null;
        droits = null;
    }

    public Statut getStatut() {
        return statut;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getNomPrenom() {
        return nom + " " + prenom;
    }

    public Droits getDroits() {
        return droits;
    }

    public void setDroits(Droits droits) {
        this.droits = droits;
    }
}
