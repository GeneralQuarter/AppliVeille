/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.modele;

import java.awt.Color;

/**
 *
 * @author x1QG1x
 */
public enum AppliColor {
    BLUE(new Color(33, 150, 243)),
    LIGHT_BLUE(new Color(66, 165, 245)),
    GRAY(new Color(189,189,189)),
    GRAY_BG(new Color(236, 239, 241));
    
    private final Color color;
    
    AppliColor(Color color)
    {
        this.color = color;
    }
    
    public Color getColor()
    {
        return color;
    }
}
