/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.modele;

import java.util.Observable;

/**
 *
 * @author x1QG1x
 */
public class Main extends Observable{
    private Statut statut;
    private Session session;
    
    public Main(){
        statut = Statut.DECONNECTE;
    }
    
    public void connexion(String login, String mdp)
    {
        if(statut == Statut.DECONNECTE)
        {
            session = new Session(login, mdp);
            if(session.connection())
            {
                statut = Statut.CONNECTE;
            }else{
                statut = Statut.ERREUR_IDENTIFIANTS;
                session = null;
            }   
        }else{
            statut = Statut.DECONNECTE;
            session = null;
        }
        this.setChanged();
        this.notifyObservers();
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
