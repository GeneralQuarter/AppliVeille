/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.modele;

import ihmpts2appliveille.modele.accesbd.entites.Utilisateur;
import java.util.Map;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author x1QG1x
 */
public class ModelListeUtilisateur extends AbstractTableModel{
    private final String[] titres = {"Nom", "Type de profil", "Nombre articles", "Nombre commentaires", "Statut"};
    private final Map<Integer, Utilisateur> utilisateurs;
    
    
    public ModelListeUtilisateur(Map<Integer, Utilisateur> utilisateurs)
    {
        this.utilisateurs = utilisateurs;
    }
    
    @Override
    public int getRowCount() {
        return utilisateurs.size();
    }

    @Override
    public int getColumnCount() {
        return titres.length;
    }
    
    @Override
    public String getColumnName(int columnIndex)
    {
        return titres[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex)
        {
            case 0: return utilisateurs.get(rowIndex+1).getNom();
            case 1: return utilisateurs.get(rowIndex+1).getTypeProfil();
            case 2: return utilisateurs.get(rowIndex+1).getNbArticle();
            case 3: return utilisateurs.get(rowIndex+1).getNbComm();
            case 4: return utilisateurs.get(rowIndex+1).getEtat();
            default: return null;
        }
    }
    
}
