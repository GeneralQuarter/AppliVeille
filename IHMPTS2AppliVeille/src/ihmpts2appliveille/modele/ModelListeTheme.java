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
        Iterator<Map.Entry<Integer, Theme>> it = donnees.entrySet().iterator();
        for(int i=0;i < donnees.size();i++)
        {
            Entry<Integer, Theme> t = it.next();
            if(i == rowIndex)
            {
                switch(columnIndex)
                {
                    case 0: return t.getValue().getIntitule();
                    case 1:
                        if(t.getValue().getIdProp() != 0)
                            return utilisateurs.get(t.getKey()).getNom();
                        else
                            return "<Libre>";
                    case 2:
                        if(!t.getValue().getDescritpion().isEmpty())
                            return t.getValue().getDescritpion();
                        else
                            return "<Aucune description>";
                    case 3: return t.getKey();
                    default: return null;
                }
            }
        }
        return null;
    }
}
