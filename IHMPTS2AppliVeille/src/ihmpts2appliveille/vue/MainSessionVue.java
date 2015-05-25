/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.vue;

import ihmpts2appliveille.modele.Navigation;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author x1QG1x
 */
public class MainSessionVue extends JPanel implements Observer{
    
    private MainWindowVue mw;
    
    private MessagerieVue msa;
    private ActualiteArticleVue aaw;
    private ActualiteArticleVue aawArticle;
    private EditorVue ev; 
    
    private JPanel currentMainContent;
    
    private EcouteurConnexion ec;
    
    private JButton returnButton;
    
    public MainSessionVue(MainWindowVue mw)
    {
        super(); // Utile ?
        
        // -- Setup MainWindow --
        this.mw = mw;
        this.mw.getNavigation().addObserver(this);
        ec = new EcouteurConnexion(this.mw);
        
        // -- Setup Layout --
        this.setLayout(new BorderLayout());
        
        // -- Setup Settings MainSession --
        this.setBackground(Color.white);
        
        // -- Setup Messagerie --
        msa = new MessagerieVue(this);
        this.add(msa, BorderLayout.EAST);
        
        // -- Setup MainContent --
        aaw = new ActualiteArticleVue(this, "Actualités");
        aawArticle = new ActualiteArticleVue(this, "Articles");
        ev = new EditorVue("Editer Article", this);
        
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

    @Override
    public void update(Observable o, Object arg) {
        switch(this.mw.getNavigation().getCible())
        {
            case "Moodle":
                try {
                    Desktop.getDesktop().browse(URI.create("https://moodle.univ-lr.fr"));
                } catch (IOException ex) {
                    Logger.getLogger(MainWindowVue.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "ENT":
                try {
                    Desktop.getDesktop().browse(URI.create("https://ent.univ-lr.fr"));
                } catch (IOException ex) {
                    Logger.getLogger(MainWindowVue.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "Tous les articles":
                this.changeMainContent(aawArticle);
                break;
            case "ACCUEIL":
                this.changeMainContent(aaw);
                break;
            case "Nouvel article...":
                this.changeMainContent(ev);
        }
    }
    
    public Navigation getNavigation()
    {
        return mw.getNavigation();
    }
}
