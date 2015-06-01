/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.modele.accesbd.entites;

import ihmpts2appliveille.modele.Droits;
import ihmpts2appliveille.modele.Statut;

/**
 *
 * @author x1QG1x
 */
public class Utilisateur {
    private int idUtilisateur;
    private String nom;
    private float note;
    private int nbConn;
    private int nbComm;
    private int nbArticle;
    private String identifiant;
    private String mdp;
    private Droits typeProfil;
    private Statut etat;

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public String getNom() {
        return nom;
    }

    public float getNote() {
        return note;
    }

    public int getNbConn() {
        return nbConn;
    }

    public int getNbComm() {
        return nbComm;
    }

    public int getNbArticle() {
        return nbArticle;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public String getMdp() {
        return mdp;
    }

    public Droits getTypeProfil() {
        return typeProfil;
    }

    public Statut getEtat() {
        return etat;
    }

    public Utilisateur(int idUtilisateur, String nom, float note, int nbConn, int nbComm, int nbArticle, String identifiant, String mdp, Droits typeProfil, Statut etat) {
        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.note = note;
        this.nbConn = nbConn;
        this.nbComm = nbComm;
        this.nbArticle = nbArticle;
        this.identifiant = identifiant;
        this.mdp = mdp;
        this.typeProfil = typeProfil;
        this.etat = etat;
    }
            
}
