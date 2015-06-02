/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.vue;

import ihmpts2appliveille.controleur.MainControleur;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author x1QG1x
 */
public class BodyContentVue extends JPanel{
    private MainControleur mctrl;
    
    private MessagerieVue mv;
    private ActualiteArticleVue aav;
    private EditorVue ev;
    private ListeVue lv;
    
    private JPanel currentMainContent;
    
    private JButton returnButton;
    
    public BodyContentVue(MainControleur mctrl)
    {
        super(); // Utile ?
        
        // -- Setup Controleur --
        this.mctrl = mctrl;
        this.mctrl.setBodyContentVue(this);
        
        // -- Setup Layout --
        this.setLayout(new BorderLayout());
        
        // -- Setup Settings MainSession --
        this.setBackground(Color.white);
        
        // -- Setup Messagerie --
        mv = new MessagerieVue(this.mctrl);
        this.add(mv, BorderLayout.EAST);
        
        // -- Setup MainContent --
        aav = new ActualiteArticleVue("Actualités", this.mctrl);
        ev = new EditorVue("Editer Article", this.mctrl);
        lv = new ListeVue("Liste des thèmes", this.mctrl);
        
        currentMainContent = null;
        this.changeMainContent(ev);
    }
    
    public void changeMainContent(JPanel content)
    {
        if(currentMainContent != null)
            this.remove(currentMainContent);
        currentMainContent = content;
        this.add(currentMainContent, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }
}
