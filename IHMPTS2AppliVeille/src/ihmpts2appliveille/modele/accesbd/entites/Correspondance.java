/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.modele.accesbd.entites;

/**
 *
 * @author Bobysmiile
 */
public class Correspondance {
    private int idMessage;
    private int idDestinataire;

    public Correspondance(int idMessage, int idDestinataire) {
        this.idMessage = idMessage;
        this.idDestinataire = idDestinataire;
    }

    public int getIdMessage() {
        return idMessage;
    }

    public int getIdDestinataire() {
        return idDestinataire;
    }
    
}
