/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.modele.accesbd.entites;

import java.util.Calendar;

/**
 * Classe Message : Une message est une structure d'informations envoyé d'une origine à un ou plusieurs destinataires
 * @author vpivet
 */
public class Message {
    private int idMessage;
    private int idAuteur;
    private String intitule;
    private String contenu;
    private Calendar dateEnvoi;
   
    /**
     * Constructeur de la classe
     * @param idMessage l'identifiant du message
     * @param idAuteur l'identitfiant de l'auteur du message
     * @param intitule l'intitulé ou le titre du message
     * @param contenu le contenu du message
     * @param dateEnvoi la date à laquelle le message est envoyé
    */
    public Message(int idMessage, int idAuteur, String intitule, String contenu, Calendar dateEnvoi) {
        this.idMessage = idMessage;
        this.idAuteur = idAuteur;
        this.intitule = intitule;
        this.contenu = contenu;
        this.dateEnvoi = dateEnvoi;
    }

    /**
     * Getteur sur l'identifiant du message
     * @return l'idMessage
     */
    public int getIdMessage() {
        return idMessage;
    }

    /**
     * Getteur sur l'identifiant de l'auteur du message
     * @return l'idAuteur
     */
    public int getIdAuteur() {
        return idAuteur;
    }

    /**
     * Getteur sur l'intitulé du message
     * @return l'intitule
     */
    public String getIntitule() {
        return intitule;
    }

    /**
     * Getteur sur le contenu du message
     * @return le contenu
     */
    public String getContenu() {
        return contenu;
    }

    /**
     * Getteur sur la date d'envoi du message
     * @return la dateEnvoi
     */
    public Calendar getDateEnvoi() {
        return dateEnvoi;
    }

    
}

