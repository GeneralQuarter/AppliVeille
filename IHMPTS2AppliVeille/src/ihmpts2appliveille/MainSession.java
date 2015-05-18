/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
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
        aaw = new ActualiteArticleView(this, "Actualités");
        this.add(aaw, BorderLayout.CENTER);
    }
}
