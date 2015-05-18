/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille;

import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author x1QG1x
 */
public class MainMenuBar extends JMenuBar{
    private MainWindow mw;
    
    private EcouteurMenu em;
    
    // -- Menus --
    private JMenu accueilMenu;
    private JMenu articleMenu;
    private JMenu themeMenu;
    private JMenu profilMenu;
    private JMenu deconnexionMenu;
    
    // -- Sub Menus --
    
    // -- Article
    private JMenuItem newArticleSubMenu;
    private JMenuItem myArticleSubMenu;
    private JMenuItem allArticleSubMenu;
    
    // -- Theme --
    private JMenuItem editMyThemeSubMenu;
    private JMenuItem listThemeSubMenu;
    
    // -- Profil --
    private JMenuItem myProfilSubMenu;
    private JMenuItem listUsersSubMenu;
    
    public MainMenuBar(MainWindow mw)
    {
        this.mw = mw;
        
        em = new EcouteurMenu(this.mw);
        
        // -- Init Sub Menus --
        newArticleSubMenu = new JMenuItem("Nouvel article...");
        myArticleSubMenu = new JMenuItem("Mes articles");
        allArticleSubMenu = new JMenuItem("Tout les articles");
        editMyThemeSubMenu = new JMenuItem("Editer mon thème");
        listThemeSubMenu = new JMenuItem("Liste des thèmes");
        myProfilSubMenu = new JMenuItem("Mon profil");
        listUsersSubMenu = new JMenuItem("Liste des utilisateurs");
        
        // -- Init Menus --
        accueilMenu = new JMenu("Accueil");
        articleMenu = new JMenu("Article");
        themeMenu = new JMenu("Thème");
        profilMenu = new JMenu("Mr Bernt");
        profilMenu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT); 
        deconnexionMenu = new JMenu("Déconnexion");
        deconnexionMenu.addMenuListener(em);
        
        // -- Adding sub menus --
        articleMenu.add(newArticleSubMenu);
        articleMenu.add(myArticleSubMenu);
        articleMenu.add(allArticleSubMenu);
        
        themeMenu.add(editMyThemeSubMenu);
        themeMenu.add(listThemeSubMenu);
        
        profilMenu.add(myProfilSubMenu);
        profilMenu.add(listUsersSubMenu);
        
        // -- Adding Menus --
        this.add(accueilMenu);
        this.add(articleMenu);
        this.add(themeMenu);
        this.add(Box.createHorizontalGlue());
        this.add(profilMenu);
        this.add(deconnexionMenu);
    }
}
