/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille;

import ihmpts2appliveille.modele.Main;
import ihmpts2appliveille.modele.Navigation;
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
                Main m = new Main();
                Navigation nav = new Navigation();
                MainWindowVue mw = new MainWindowVue(m, nav);
            }
        
        });
    }
    
}
