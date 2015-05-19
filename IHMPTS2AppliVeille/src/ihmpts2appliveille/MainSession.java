/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author x1QG1x
 */
public class MainSession extends JPanel{
    private MainWindow mw;
    private Messagerie msa;
    private ActualiteArticleView aaw;
    private ActualiteArticleView aawArticle;
    
    private JPanel currentMainContent;
    
    private EcouteurBouton ec;
    
    private JButton returnButton;
    
    public MainSession(MainWindow mw)
    {
        super(); // Utile ?
        
        // -- Setup MainWindow --
        this.mw = mw;
        ec = new EcouteurBouton(this.mw);
        
        // -- Setup Layout --
        this.setLayout(new BorderLayout());
        
        // -- Setup Settings MainSession --
        this.setBackground(Color.white);
        
        // -- Setup Messagerie --
        msa = new Messagerie(this);
        this.add(msa, BorderLayout.EAST);
        
        // -- Setup MainContent --
        aaw = new ActualiteArticleView(this, "Actualités");
        aawArticle = new ActualiteArticleView(this, "Articles");
        
        currentMainContent = null;
        this.changeMainContent(aaw);
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
    
    public void goTo(String action)
    {
        switch(action)
        {
            case "ACCUEIL":
                this.changeMainContent(aaw);
                break;
            case "Tous les articles":
                this.changeMainContent(aawArticle);
                break;
        }
    }
}
