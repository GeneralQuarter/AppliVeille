/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.modele;

import ihmpts2appliveille.modele.accesbd.entites.Utilisateur;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 * Classe définissant l'aspect le rendu des cellules pour les listes
 * @author x1QG1x
 */
public class ListCellRendererUtilisateur extends JLabel implements ListCellRenderer<Utilisateur>{

    @Override
    public Component getListCellRendererComponent(JList<? extends Utilisateur> list, Utilisateur value, int index, boolean isSelected, boolean cellHasFocus) {
      if (isSelected) {
         setBackground(AppliColor.LIGHT_BLUE.getColor());
         setForeground(list.getSelectionForeground());
         setText(" " +value.getNom());
      }else{
         setBackground(list.getBackground());
         setForeground(list.getForeground());
         setText(" " +value.getNom());
      }
      setEnabled(list.isEnabled());
      setFont(list.getFont());
      setOpaque(true);
      return this;
    }
    
}
