/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.vue;

import ihmpts2appliveille.vue.QGMenu;
import ihmpts2appliveille.vue.QGButton;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.Box;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author x1QG1x
 */
public class MainMenuBarVue extends JMenuBar{
    private MainWindowVue mw;
    
    // -- Menus --
    private QGButton accueilButton;
    private JMenu articleMenu;
    private JMenu themeMenu;
    private JMenu links;
    private JMenu profilMenu;
    private QGButton deconnexionButton;
    
    // -- Sub Menus --
    
    // -- Article
    private JMenuItem newArticleSubMenu;
    private JMenuItem myArticleSubMenu;
    private JMenuItem allArticleSubMenu;
    
    // -- Theme --
    private JMenuItem editMyThemeSubMenu;
    private JMenuItem listThemeSubMenu;
    
    // -- Links --
    private JMenuItem moodle;
    private JMenuItem ent;
    
    // -- Profil --
    private JMenuItem myProfilSubMenu;
    private JMenuItem listUsersSubMenu;
    
    private Font f;
    
    private EcouteurConnexion ec;
    private EcouteurNavigation en;
    
    public MainMenuBarVue(MainWindowVue mw)
    {
        this.mw = mw;
        
        ec = new EcouteurConnexion(this.mw);
        en = new EcouteurNavigation(this.mw);
        
        f = new Font("Arial", Font.BOLD, 16);
        
        this.setPreferredSize(new Dimension(getWidth(),40));
        // -- Init Sub Menus --
        newArticleSubMenu = new JMenuItem("Nouvel article...");
        newArticleSubMenu.addActionListener(en);
        myArticleSubMenu = new JMenuItem("Mes articles");
        allArticleSubMenu = new JMenuItem("Tous les articles");
        allArticleSubMenu.addActionListener(en);
        editMyThemeSubMenu = new JMenuItem("Editer mon thème");
        listThemeSubMenu = new JMenuItem("Liste des thèmes");
        moodle = new JMenuItem("Moodle");
        moodle.addActionListener(en);
        ent = new JMenuItem("ENT");
        ent.addActionListener(en);
        myProfilSubMenu = new JMenuItem("Mon profil");
        listUsersSubMenu = new JMenuItem("Liste des utilisateurs");
        
        // -- Init Menus --
        accueilButton = new QGButton("ACCUEIL", new Color(33, 150, 243), new Color(66, 165, 245), Color.white, f);
        accueilButton.setMaximumSize(new Dimension(accueilButton.getPreferredSize().width, 40));
        accueilButton.addActionListener(en);
        articleMenu = new QGMenu("ARTICLE", new Color(33, 150, 243), new Color(66, 165, 245), Color.white, f);
        themeMenu = new QGMenu("THEME", new Color(33, 150, 243), new Color(66, 165, 245), Color.white, f);
        links = new QGMenu("LIENS", new Color(33, 150, 243), new Color(66, 165, 245), Color.white, f);
        links.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        profilMenu = new QGMenu("Gangler Quentin", new Color(33, 150, 243), new Color(66, 165, 245), Color.white, f);
        profilMenu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        
        // -- Adding sub menus --
        articleMenu.add(newArticleSubMenu);
        articleMenu.add(myArticleSubMenu);
        articleMenu.addSeparator();
        articleMenu.add(allArticleSubMenu);
        
        themeMenu.add(editMyThemeSubMenu);
        themeMenu.addSeparator();
        themeMenu.add(listThemeSubMenu);
        
        links.add(moodle);
        links.add(ent);
        
        profilMenu.add(myProfilSubMenu);
        profilMenu.addSeparator();
        profilMenu.add(listUsersSubMenu);
        
        deconnexionButton = new QGButton("DECONNEXION", new Color(33, 150, 243), new Color(66, 165, 245), Color.white, f);
        deconnexionButton.setMaximumSize(new Dimension(deconnexionButton.getPreferredSize().width, 40));
        deconnexionButton.addActionListener(ec);
        
        // -- Adding Menus --
        this.add(accueilButton);
        this.add(articleMenu);
        this.add(themeMenu);
        this.add(Box.createHorizontalGlue());
        this.add(links);
        this.add(profilMenu);
        this.add(deconnexionButton);
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(33,150,243));
        g2d.fillRect(0, 0, getWidth(), getHeight()-1);
    }
    
    public void setProfilName(String nomPrenom)
    {
        profilMenu.setText(nomPrenom);
    }
}
