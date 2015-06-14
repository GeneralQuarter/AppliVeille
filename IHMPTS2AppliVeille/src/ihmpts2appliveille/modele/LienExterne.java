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
    
    public LienExterne(String url)
    {
        this.url = url;
    }
    
    public void browse()
    {
        try {
            Desktop.getDesktop().browse(URI.create(url));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex, "Erreur à l'ouverture de l'url", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void setUrl(String url)
    {
        this.url = url;
    }
}
