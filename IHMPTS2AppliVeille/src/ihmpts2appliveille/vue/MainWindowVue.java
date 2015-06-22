/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.vue;

import ihmpts2appliveille.controleur.MainControleur;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Classe d'affichage de la fenêtre principale
 * @author x1QG1x
 */
public class MainWindowVue extends JFrame{
    private MainControleur mctrl;
    
    private MenuBarVue mbv;
    private FormAuthentificationVue fav;
    private BodyContentVue bcv;
    
    private BorderLayout mainLayout;
    private JPanel currentMainFrame;
    
    public MainWindowVue(MainControleur mctrl)
    {
        // -- Setup Controleur --
        this.mctrl = mctrl;
        this.mctrl.setMainWindowVue(this);
        
        try {
            // -- Look and Feel --
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainWindowVue.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // -- Setup Basic JFrame --
        this.setLocation(100, 100);
        this.setSize(1280,720);
        this.setTitle("Appli Veille");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent)
            {
                MainWindowVue.this.mctrl.fermerFenetre();
            }
        });
        
        
        // -- Setup Layout --
        mainLayout = new BorderLayout();
        this.setLayout(mainLayout);
        
        // -- Setup Frames --
        fav = new FormAuthentificationVue(this.mctrl);
        bcv = new BodyContentVue(this.mctrl);
        mbv = new MenuBarVue(this.mctrl);
        
        // -- Setup currentMainFrame --
        currentMainFrame = fav;
        this.getContentPane().add(fav);
        this.setJMenuBar(mbv);
        mbv.setVisible(false);
        
        this.revalidate();
        this.repaint();
        
        // -- Display --
        try {
        this.setIconImage(ImageIO.read(new File("images/logo.png")));
        } catch (IOException e) {
            System.err.println("Impossible de charger l'icone de l'application");
        }
        this.setVisible(true);
    }
    
    public void changeMainFrame(JPanel mainFrame, boolean menuVisible)
    {
        if(currentMainFrame != null)
            this.remove(currentMainFrame);
        this.getContentPane().add(mainFrame);
        currentMainFrame = mainFrame;
        mbv.setVisible(menuVisible);
        this.revalidate();
        this.repaint();
    }
}
