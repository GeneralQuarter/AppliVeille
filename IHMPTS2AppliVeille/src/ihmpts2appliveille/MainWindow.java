/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author x1QG1x
 */
public class MainWindow extends JFrame{
    private MainMenuBar mmb;
    private FormAuthentification fa;
    private MainSession ms;
    
    private BorderLayout mainLayout;
    private JPanel currentMainFrame;
    
    public MainWindow()
    {
        try {
            // -- Look and Feel --
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // -- Setup Basic JFrame --
        this.setLocation(100, 100);
        this.setSize(1280,720);
        this.setTitle("Appli Veille");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // -- Setup Layout --
        mainLayout = new BorderLayout();
        this.setLayout(mainLayout);
        
        // -- Setup Frames --
        fa = new FormAuthentification(this);
        ms = new MainSession(this);
        mmb = new MainMenuBar(this);
        
        // -- Setup currentMainFrame --
        currentMainFrame = fa;
        this.getContentPane().add(fa);
        this.setJMenuBar(mmb);
        mmb.setVisible(false);
        
        this.revalidate();
        this.repaint();
        
        // -- Display --
        try {
        this.setIconImage(ImageIO.read(new File("logo2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setVisible(true);
    }
    
    public void changeMainFrame(JPanel mainFrame, boolean menuVisible)
    {
        if(currentMainFrame != null)
            this.remove(currentMainFrame);
        this.getContentPane().add(mainFrame);
        currentMainFrame = mainFrame;
        mmb.setVisible(menuVisible);
        this.revalidate();
        this.repaint();
    }
    
    public void buttonClicked(String actionCommand)
    {
        switch(actionCommand)
        {
            case "CONNEXION":
                changeMainFrame(ms, true);
                break;
            case "DECONNEXION":
                changeMainFrame(fa, false);
                break;
            case "Nouveau Message":
                break;
            case "ENT":
                try {
                    Desktop.getDesktop().browse(URI.create("https://ent.univ-lr.fr"));
                } catch (IOException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "Moodle":
                try {
                    Desktop.getDesktop().browse(URI.create("https://moodle.univ-lr.fr"));
                } catch (IOException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }
    }
}
