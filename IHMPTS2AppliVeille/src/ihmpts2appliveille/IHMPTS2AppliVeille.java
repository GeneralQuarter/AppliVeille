/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille;

import ihmpts2appliveille.controleur.MainControleur;
import ihmpts2appliveille.modele.Cryptage;
import ihmpts2appliveille.vue.MainWindowVue;
import javax.swing.SwingUtilities;

/**
 *
 * @author x1QG1x
 */
public class IHMPTS2AppliVeille {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {
                
                System.out.println(Cryptage.getEncodedPassword("mdp"));
                MainControleur mctrl = new MainControleur();
                MainWindowVue mwv = new MainWindowVue(mctrl);
            }
        
        });
    }
    
}
