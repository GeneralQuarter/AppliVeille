/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.modele;

import ihmpts2appliveille.modele.accesbd.entites.Utilisateur;
import java.util.List;
import javax.swing.AbstractListModel;

/**
 * Classe modélisant la liste des messages
 * @author x1QG1x
 */
public class ModelListeUtilisateurMessage extends AbstractListModel<Utilisateur>{
    private List<Utilisateur> utilisateurs;
    
    public ModelListeUtilisateurMessage(List<Utilisateur> utilisateurs)
    {
        if(utilisateurs != null)
        {
            this.utilisateurs = utilisateurs;
        }
    }
    
    @Override
    public int getSize() {
        return utilisateurs.size();
    }

    @Override
    public Utilisateur getElementAt(int index) {
        return utilisateurs.get(index);
    }
    
}
