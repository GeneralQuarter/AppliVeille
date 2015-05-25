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
public class Navigation extends Observable{
    private String cible;
    
    public Navigation()
    {
        cible = "";
    }
    
    public void naviguer(String cible)
    {
        this.cible = cible;
        this.setChanged();
        this.notifyObservers();
    }
    
    public String getCible()
    {
        return cible;
    }
}
