/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.modele;

import ihmpts2appliveille.controleur.MainControleur;
import ihmpts2appliveille.modele.accesbd.entites.Theme;
import ihmpts2appliveille.modele.accesbd.entites.Utilisateur;
import java.util.Map;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author x1QG1x
 */
public class ModelListeTheme extends AbstractTableModel{
    private Map<Integer, Theme> donnees;
    private Map<Integer, Utilisateur> utilisateurs;
    private Object[] titres = {"Intitulé", "Possesseur", "Description"};
    
    public ModelListeTheme(Map<Integer, Theme> donnees, Map<Integer, Utilisateur> utilisateurs)
    {
        this.donnees = donnees;
        this.utilisateurs = utilisateurs;
    }
    
    @Override
    public int getRowCount() {
        return donnees.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }
    
    @Override
    public String getColumnName(int columnIndex)
    {
        return titres[columnIndex].toString();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex)
        {
            case 0:
                return donnees.get(rowIndex+1).getIntitule();
            case 1:
                if(donnees.get(rowIndex+1).getIdProp() != 0)
                    return utilisateurs.get(donnees.get(rowIndex+1).getIdProp()).getNom();
                else
                    return "<Libre>";
            case 2:
                if(!donnees.get(rowIndex+1).getDescritpion().isEmpty())
                    return donnees.get(rowIndex+1).getDescritpion();
                else
                    return "<Aucune description>";
            case 3 : 
                
            default:
                return donnees.get(rowIndex+1).getIdTheme();
        }
    }
}
