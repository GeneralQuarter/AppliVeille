/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ihmpts2appliveille.vue;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Classe Etoile : Cette classe permet l'affichage de la note d'un article représentée sous forme d'étoiles
 * @author qgangler
 */
public class Etoile extends JPanel{
    private int note;
    private final ImageIcon starFull = new ImageIcon("star_full.png");
    private final ImageIcon starEmpty = new ImageIcon("star_empty.png");
    private List<JLabel> etoiles;
    
    /**
     * Constructeur de la classe étoie. Un point prend la valeur d'une étoile.
     * @param note la note de l'article qui a été attribué à un professeur
     */
    public Etoile(int note)
    {
        this.note = note;
        this.etoiles = new ArrayList<>();
        this.setBackground(Color.white);
        for(int i = 1; i <= 5; i++)
        {
            JLabel etoile = null;
            if(i <= this.note)
               etoile = new JLabel(starFull);
            else
               etoile = new JLabel(starEmpty);
            this.add(etoile);
        }
    }
}
