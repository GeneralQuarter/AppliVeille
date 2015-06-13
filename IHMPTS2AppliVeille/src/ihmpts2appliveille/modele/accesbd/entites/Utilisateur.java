/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.modele.accesbd.entites;

import ihmpts2appliveille.modele.Droits;
import ihmpts2appliveille.modele.Statut;

/**
 * Classe Utilisateur : Un utilisateur peut être de trois type (Administrateur, Professeur ou étudiant). Selon son type il aura un rôle à joué différent
 * dans l'application
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
    
    /**
     * Constructeur de la classe
     * @param idUtilisateur l'identifiant de l'utilisateur
     * @param nom le nom réel de l'utilisateur
     * @param note la note de l'utilisateur 
     * @param nbConn le nombre de connection à l'application de l'utilisateur
     * @param nbComm le nombre de commentaires postés par l'utilisateur
     * @param nbArticle le nombre d'articles postés par l'utilisateur
     * @param identifiant l'identifiant de connection de l'utilisateur (Le nom de compte qui lui permet de se connecter)
     * @param mdp le mot de passe qui permet à l'utilisateur de se connecter
     * @param typeProfil le type de profil de l'utilisateur
     * @param etat l'état connecté ou déconnecté de l'utilisateur
    */
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

    /**
     * Getteur sur l'identifiant de l'utilisateur
     * @return l'idUtilisateur
     */
    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    /**
     * Getteur sur le nom de l'utilisateur
     * @return le nom 
     */
    public String getNom() {
        return nom;
    }

    /**
     * Getteur sur la note de l'utilisateur
     * @return la note 
     */
    public float getNote() {
        return note;
    }

    /**
     * Getteur sur le nombre de connections de l'utilisateur
     * @return le nombre de connections 
     */
    public int getNbConn() {
        return nbConn;
    }

    /**
     * Getteur sur le nombre de commentaires de l'utilisateur
     * @return le nbComm : Le nombre de commentaires 
     */
    public int getNbComm() {
        return nbComm;
    }

    /**
     * Getteur sur le nombre d'articles de l'utilisateur
     * @return le nbArticle : Le nombre d'articles 
     */
    public int getNbArticle() {
        return nbArticle;
    }

    /**
     * Getteur sur l'identifiant de connection de l'utilisateur
     * @return l'identifiant 
     */
    public String getIdentifiant() {
        return identifiant;
    }

    /**
     * Getteur sur le mot de passe de l'utilisateur (crypté)
     * @return le mdp 
     */
    public String getMdp() {
        return mdp;
    }

    /**
     * Getteur sur les droits de l'utilisateur
     * @return le typeProfil 
     */
    public Droits getTypeProfil() {
        return typeProfil;
    }

    /**
     * Getteur sur le statut de connection de l'utilisateur
     * @return l'etat 
     */
    public Statut getEtat() {
        return etat;
    }
            
}
