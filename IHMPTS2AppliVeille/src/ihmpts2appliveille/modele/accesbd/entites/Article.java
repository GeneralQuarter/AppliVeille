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
public class Article {
    private int idArticle;
    private int idAuteur;
    private int idTheme;
    private int nbCommArt;
    private String intitule;
    private String contenu;
    private Calendar datePubli;
    private Calendar dateModif;
    private float note;

    public int getIdArticle() {
        return idArticle;
    }

    public int getIdAuteur() {
        return idAuteur;
    }

    public int getIdTheme() {
        return idTheme;
    }

    public int getNbCommArt() {
        return nbCommArt;
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

    public float getNote() {
        return note;
    }

    public Article(int idArticle, int idAuteur, int idTheme, int nbCommArt, String intitule, String contenu, Calendar datePubli, Calendar dateModif, float note) {
        this.idArticle = idArticle;
        this.idAuteur = idAuteur;
        this.idTheme = idTheme;
        this.nbCommArt = nbCommArt;
        this.intitule = intitule;
        this.contenu = contenu;
        this.datePubli = datePubli;
        this.dateModif = dateModif;
        this.note = note;
    }
}
