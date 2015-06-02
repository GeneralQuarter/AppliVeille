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
public class Message {
    private int idMessage;
    private int idAuteur;
    private String intitule;
    private String contenu;
    private Calendar dateEnvoi;

    public int getIdMessage() {
        return idMessage;
    }

    public int getIdAuteur() {
        return idAuteur;
    }

    public String getIntitule() {
        return intitule;
    }

    public String getContenu() {
        return contenu;
    }

    public Calendar getDateEnvoi() {
        return dateEnvoi;
    }

    public Message(int idMessage, int idAuteur, String intitule, String contenu, Calendar dateEnvoi) {
        this.idMessage = idMessage;
        this.idAuteur = idAuteur;
        this.intitule = intitule;
        this.contenu = contenu;
        this.dateEnvoi = dateEnvoi;
    }
}
