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

//    @Override
//    public void update(Observable o, Object arg) {
//        switch(this.mw.getNavigation().getCible())
//        {
//            case "Moodle":
//                try {
//                    Desktop.getDesktop().browse(URI.create("https://moodle.univ-lr.fr"));
//                } catch (IOException ex) {
//                    Logger.getLogger(MainWindowVue.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                break;
//            case "ENT":
//                try {
//                    Desktop.getDesktop().browse(URI.create("https://ent.univ-lr.fr"));
//                } catch (IOException ex) {
//                    Logger.getLogger(MainWindowVue.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                break;
//            case "Tous les articles":
//                this.changeMainContent(aawArticle);
//                break;
//            case "ACCUEIL":
//                this.changeMainContent(aaw);
//                break;
//            case "Nouvel article...":
//                this.changeMainContent(ev);
//        }
//    }
}
