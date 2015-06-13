/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.modele.accesbd.entites;

/**
 * Classe Correspondance : Une correspondace lie un utilisateur avec un message qui lui est envoyé.
 * @author vpivet
 */
public class Correspondance {
    private int idMessage;
    private int idDestinataire;
    
   /**
     * Constructeur de la classe
     * @param idMessage l'identifiant du message qui a été envoyé
     * @param idDestinataire l'identitfiant du destinataire à qui le message a été envoyé
    */
    public Correspondance(int idMessage, int idDestinataire) {
        this.idMessage = idMessage;
        this.idDestinataire = idDestinataire;
    }

    /**
     * Getter sur l'identifiant du message
     * @return l'idMessage
     */
    public int getIdMessage() {
        return idMessage;
    }

    /**
     * Getter sur l'identifiant du destinataire
     * @return l'idDestinataire
     */
    public int getIdDestinataire() {
        return idDestinataire;
    }
    
}
