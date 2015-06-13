/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.modele.accesbd.entites;

import java.util.Calendar;
import java.util.Date;

/**
 * Classe Commentaire : un commentaire est une remarque qui peut être postée par un étudiant ou par un professeur sous un article séléctionné
 * @author vpivet
 */
public class Commentaire {
   private int idCommentaire;
   private int idAuteur;
   private int idArticle;
   private String intitule;
   private String contenu;
   private Date datePubli;
   private Date dateModif;
   private boolean visible;

   /**
     * Constructeur de la classe
     * @param idCommentaire l'identifiant du commentaire
     * @param idAuteur l'identitfiant de l'auteur du commentaire
     * @param idArticle l'identifiant de l'article auquel le commentaire est attribué
     * @param intitule l'intitulé ou le titre du commentaire
     * @param contenu le contenu du commentaire
     * @param datePubli la date de publication du commentaire
     * @param dateModif la date de modification du commentaire
     * @param visible la visibilité du commentaire sur l'interface
     */
   public Commentaire(int idCommentaire, int idAuteur, int idArticle, String intitule, String contenu, Date datePubli, Date dateModif, boolean visible) {
        this.idCommentaire = idCommentaire;
        this.idAuteur = idAuteur;
        this.idArticle = idArticle;
        this.intitule = intitule;
        this.contenu = contenu;
        this.datePubli = datePubli;
        this.dateModif = dateModif;
        this.visible = visible;
    }
   
   /**
     * Getter sur l'identifiant du commentaire
     * @return l'idCommentaire
     */
    public int getIdCommentaire() {
        return idCommentaire;
    }
    
    /**
     * Getter sur l'identifiant de l'auteur du commentaire
     * @return l'idAuteur
     */
    public int getIdAuteur() {
        return idAuteur;
    }

    /**
     * Getter sur l'identifiant de l'article auquel le commentaire est affilié
     * @return l'idArticle
     */
    public int getIdArticle() {
        return idArticle;
    }

    /**
     * Getter sur l'intitulé du commentaire
     * @return l'intitule
     */
    public String getIntitule() {
        return intitule;
    }

    /**
     * Getter sur le contenu du commentaire
     * @return le contenu
     */
    public String getContenu() {
        return contenu;
    }

    /**
     * Getter sur la date de publication du commentaire
     * @return la datePublic : la date de publication
     */
    public Date getDatePubli() {
        return datePubli;
    }

    /**
     * Getter sur la date de modification du commentaire
     * @return la dateModif : la date de modification
     */
    public Date getDateModif() {
        return dateModif;
    }
    
    /**
     * Getter sur la visibilité du commentaire
     * @return visible
     */
    public boolean getVisible(){
        return visible;
    }

}

