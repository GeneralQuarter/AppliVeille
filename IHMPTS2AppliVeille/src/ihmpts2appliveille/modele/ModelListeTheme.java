/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.modele;

import ihmpts2appliveille.modele.accesbd.entites.Theme;
import ihmpts2appliveille.modele.accesbd.entites.Utilisateur;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.table.AbstractTableModel;

/**
 * Classe modélisant la liste des thèmes
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
        int i = -1;
        for(Theme t : donnees.values())
        {
            i++;
            if(i == rowIndex)
            {
                switch(columnIndex)
                {
                    case 0: return t.getIntitule();
                    case 1:
                        if(t.getIdProp() != 0){
                            return utilisateurs.get(t.getIdProp()).getNom();
                        }else
                            return "<Libre>";
                    case 2:
                        if(!t.getDescritpion().isEmpty())
                            return t.getDescritpion();
                        else
                            return "<Aucune description>";
                    case 3: return t.getIdTheme();
                    default: return null;
                }
            }
        }
        return null;
    }
}
