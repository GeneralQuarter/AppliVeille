/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille;

import javax.swing.JMenu;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 *
 * @author x1QG1x
 */
public class EcouteurMenu implements MenuListener{

    private MainWindow mw;
    
    public EcouteurMenu(MainWindow mw)
    {
        this.mw = mw;
    }    

    @Override
    public void menuSelected(MenuEvent e) {
        JMenu m = (JMenu) e.getSource();
        mw.changeMainFrame(m.getText());
    }

    @Override
    public void menuDeselected(MenuEvent e) {
        
    }

    @Override
    public void menuCanceled(MenuEvent e) {
        
    }
}
