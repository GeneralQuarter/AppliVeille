/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.modele.accesbd.entites;

/**
 *
 * @author vpivet
 */
public class Theme {
    private int idTheme;
    private int idProp; //Il peut être null
    private String intitule;
    private String descritpion;

    public int getIdTheme() {
        return idTheme;
    }

    public int getIdProp() {
        return idProp;
    }

    public String getIntitule() {
        return intitule;
    }

    public String getDescritpion() {
        return descritpion;
    }

    public Theme(int idTheme, int idProp, String intitule, String descritpion) {
        this.idTheme = idTheme;
        this.idProp = idProp;
        this.intitule = intitule;
        this.descritpion = descritpion;
    }
}
