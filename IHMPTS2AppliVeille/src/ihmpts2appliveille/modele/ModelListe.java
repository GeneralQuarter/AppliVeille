/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.modele;

import ihmpts2appliveille.controleur.MainControleur;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author x1QG1x
 */
public class ModelListe extends AbstractTableModel{
    Object[][] donnees;
    String[] titres;
    
    public ModelListe(Object[][] donnees, String[] titres)
    {
        this.donnees = donnees;
        this.titres = titres;
    }
    
    @Override
    public int getRowCount() {
        return donnees[0].length;
    }

    @Override
    public int getColumnCount() {
        return donnees.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return donnees[rowIndex][columnIndex];
    }
    
}
