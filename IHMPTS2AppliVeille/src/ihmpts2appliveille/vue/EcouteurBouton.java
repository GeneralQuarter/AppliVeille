/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.vue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author x1QG1x
 */
public class EcouteurBouton implements ActionListener{
    private MainWindowVue mw;

    public EcouteurBouton(MainWindowVue mw)
    {
        this.mw = mw;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        mw.getModele().connexion(mw.getFormAuthentificationVue().getLogin(), mw.getFormAuthentificationVue().getMdp());
    }
    
}
