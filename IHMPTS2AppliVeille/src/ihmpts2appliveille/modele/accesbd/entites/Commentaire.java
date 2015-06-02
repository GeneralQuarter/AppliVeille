/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.modele.accesbd.entites;

import java.util.Calendar;

/**
 *
 * @author vpivet
 */
public class Commentaire {
   private int idCommentaire;
   private int idAuteur;
   private int idArticle;
   private String intitule;
   private String contenu;
   private Calendar datePubli;
   private Calendar dateModif;

    public int getIdCommentaire() {
        return idCommentaire;
    }

    public int getIdAuteur() {
        return idAuteur;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public String getIntitule() {
        return intitule;
    }

    public String getContenu() {
        return contenu;
    }

    public Calendar getDatePubli() {
        return datePubli;
    }

    public Calendar getDateModif() {
        return dateModif;
    }

    public Commentaire(int idCommentaire, int idAuteur, int idArticle, String intitule, String contenu, Calendar datePubli, Calendar dateModif) {
        this.idCommentaire = idCommentaire;
        this.idAuteur = idAuteur;
        this.idArticle = idArticle;
        this.intitule = intitule;
        this.contenu = contenu;
        this.datePubli = datePubli;
        this.dateModif = dateModif;
    }
}
