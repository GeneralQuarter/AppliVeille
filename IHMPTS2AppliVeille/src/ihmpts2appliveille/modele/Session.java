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
    
    public Session(String login, String mdp)
    {
        this.login = login;
        this.mdp = mdp;
    }
    
    public boolean connection()
    {
        // BDD
        // Recupération du nom/prenom
        // Recupération droits
        if(login.equals("qgangler") && mdp.equals("password"))
        {
            nom = "Gangler";
            prenom = "Quentin";
            droits = Droits.ETUDIANT;
            return true;
        }else
            return false;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Droits getDroits() {
        return droits;
    }

    public void setDroits(Droits droits) {
        this.droits = droits;
    }
}
