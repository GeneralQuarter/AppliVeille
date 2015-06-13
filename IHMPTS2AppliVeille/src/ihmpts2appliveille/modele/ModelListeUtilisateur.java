/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.modele;

import ihmpts2appliveille.modele.accesbd.entites.Utilisateur;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
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
        Iterator<Entry<Integer, Utilisateur>> it = utilisateurs.entrySet().iterator();
        for (int i = 0; i < utilisateurs.size(); i++) {
            Entry<Integer, Utilisateur> u = it.next();
            if(i == rowIndex)
            {
                switch(columnIndex)
                {
                    case 0: return u.getValue().getNom();
                    case 1: return u.getValue().getTypeProfil();
                    case 2: return u.getValue().getNbArticle();
                    case 3: return u.getValue().getNbComm();
                    case 4: return u.getValue().getEtat();
                    case 5: return u.getValue().getIdUtilisateur();
                    default: return null;
                }
            }
        }
        return null;
    }
    
}
