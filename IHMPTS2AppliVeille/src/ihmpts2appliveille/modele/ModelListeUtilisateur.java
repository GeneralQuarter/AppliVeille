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
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author x1QG1x
 */
public class ModelListeUtilisateur extends AbstractTableModel{
    private final ImageIcon connected = new ImageIcon("connected.png");
    private final ImageIcon deconnected = new ImageIcon("deconnected.png");
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
    public Class getColumnClass(int columnIndex)
    {
        switch(columnIndex)
        {
            case 0:
            case 1:
            case 2:
            case 3:
                return String.class;
            case 4:
                return JLabel.class;
            default:
                return String.class;
        }
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
                    case 4: 
                        switch(u.getValue().getEtat())
                        {
                            case CONNECTE:
                                JLabel l = new JLabel(connected);
                                l.setOpaque(true);
                                l.setToolTipText("Connecté");
                                return l;
                            case DECONNECTE:
                                JLabel l1 = new JLabel(deconnected);
                                l1.setOpaque(true);
                                l1.setToolTipText("Déonnecté");
                                return l1;
                        }
                    case 5: return u.getValue().getIdUtilisateur();
                    default: return null;
                }
            }
        }
        return null;
    }
    
}
