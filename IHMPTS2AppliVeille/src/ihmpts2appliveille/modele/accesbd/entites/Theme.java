/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.modele.accesbd.entites;

/**
 * Classe Thème : un thème est un fil conducteur que doit suivre l'étudiant pour poster ses articles. Il doit demander le thème à un professeur qui lui sera ensuite attribué
 * @author vpivet
 */
public class Theme {
    private int idTheme;
    private int idProp;
    private String intitule;
    private String descritpion;
    
    /**
     * Constructeur de la classe
     * @param idTheme l'identifiant du thème
     * @param idProp l'identitfiant du propriétaire du thème (peut être null)
     * @param intitule l'intitulé ou le titre du thème
     * @param descritpion la description du thème qui peut être modifié par la suite par l'étudiant
    */
    public Theme(int idTheme, int idProp, String intitule, String descritpion) {
        this.idTheme = idTheme;
        this.idProp = idProp;
        this.intitule = intitule;
        this.descritpion = descritpion;
    }

    /**
     * Getteur sur l'identifiant du thème
     * @return l'idTheme
     */
    public int getIdTheme() {
        return idTheme;
    }

    /**
     * Getteur sur l'identifiant du propriétaire du thème
     * @return l'idProp
     */
    public int getIdProp() {
        return idProp;
    }

    /**
     * Getteur sur l'intitule du thème
     * @return l'intitule
     */
    public String getIntitule() {
        return intitule;
    }

    /**
     * Getteur sur la description du thème
     * @return la description
     */
    public String getDescritpion() {
        return descritpion;
    }

}
