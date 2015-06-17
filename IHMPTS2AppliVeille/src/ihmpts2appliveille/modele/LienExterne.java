/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.modele;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Classe permettant l'accès aux liens extrernes
 * @author x1QG1x
 */
public class LienExterne {
    private String url;
    
    /**
     * Constructeur de la classe.
     * @param url Le lien vers la page souhaitée
     */
    public LienExterne(String url)
    {
        this.url = url;
    }
    
    /**
     * La fonction browse permet d'ouvrir le navigateur par défaut de l'utilistaure avec comme entrée le lien de l'URL qu'il a séléctionné
     */
    public void browse()
    {
        try {
            Desktop.getDesktop().browse(URI.create(url));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex, "Erreur à l'ouverture de l'url", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Setter sur le lien.
     * @param url
     */
    public void setUrl(String url)
    {
        this.url = url;
    }
}
