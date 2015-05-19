/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author x1QG1x
 */
public class EcouteurBouton implements ActionListener{
    private MainWindow mw;

    public EcouteurBouton(MainWindow mw)
    {
        this.mw = mw;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        mw.buttonClicked(e.getActionCommand());
    }
    
}
