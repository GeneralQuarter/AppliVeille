/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.vue;

import ihmpts2appliveille.controleur.MainControleur;
import ihmpts2appliveille.modele.AppliColor;
import ihmpts2appliveille.modele.Droits;
import ihmpts2appliveille.modele.ModelListeTheme;
import ihmpts2appliveille.modele.ModelListeUtilisateur;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author x1QG1x
 */
public class ListeVue extends JPanel{
    private MainControleur mctrl;
    
    private JLabel title;
    
    private JPanel centerPanel;
    
    private JTable mainTable;
    private JScrollPane scrollerTable;
    
    private QGButton acceptButton;
    private QGButton optionnalButton;
    
    private JLabel addTitle;
    private JTextField addField;
    private QGButton confirmAddButton;
    
    private SpringLayout sp;
    
    private Font f;
    
    public ListeVue(String title, MainControleur mctrl)
    {
        this.mctrl = mctrl;
        this.mctrl.setListeVue(this);
        
        this.setLayout(new BorderLayout());
        
        f = new Font("Arial", Font.BOLD, 40);
        
        this.title = new JLabel(title);
        this.title.setFont(f);
        this.title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
        centerPanel = new JPanel();
        centerPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, new Color(33,33,33)));
        
        // -- Setup JTable --       
        mainTable = new JTable();
        mainTable.getTableHeader().setReorderingAllowed(false);
        f = new Font("Arial", 0, 14);
        mainTable.setFont(f);
        f = new Font("Arial", Font.BOLD, 14);
        mainTable.getTableHeader().setFont(f);
        mainTable.setBorder(null);
        scrollerTable = new JScrollPane(mainTable);
        scrollerTable.setBorder(null);
        
        
        
        f = new Font("Arial", 0, 16);
        
        acceptButton = new QGButton("Demander", AppliColor.BLUE.getColor(), AppliColor.LIGHT_BLUE.getColor(), Color.white, f);
        acceptButton.addActionListener(new EcouteurBouton());
        optionnalButton = new QGButton("Archiver", AppliColor.BLUE.getColor(), AppliColor.LIGHT_BLUE.getColor(), Color.white, f);
        optionnalButton.addActionListener(new EcouteurBouton());
        
        addTitle = new JLabel("Proposer thème");
        addField = new JTextField();
        confirmAddButton = new QGButton("Valider", AppliColor.BLUE.getColor(), AppliColor.LIGHT_BLUE.getColor(), Color.white, f);
        
        sp = new SpringLayout();
        
        centerPanel.setLayout(sp);
        centerPanel.add(scrollerTable);
        centerPanel.add(acceptButton);
        centerPanel.add(optionnalButton);
        
        sp.putConstraint(SpringLayout.WEST, scrollerTable, 5, SpringLayout.WEST, centerPanel);
        sp.putConstraint(SpringLayout.EAST, scrollerTable, -5, SpringLayout.EAST, centerPanel);
        sp.putConstraint(SpringLayout.NORTH, scrollerTable, 5, SpringLayout.NORTH, centerPanel);
        sp.putConstraint(SpringLayout.SOUTH, scrollerTable, -5, SpringLayout.NORTH, acceptButton);
        
        sp.putConstraint(SpringLayout.EAST, optionnalButton, -5, SpringLayout.EAST, centerPanel);
        sp.putConstraint(SpringLayout.SOUTH, optionnalButton, -5, SpringLayout.SOUTH, centerPanel);
        
        sp.putConstraint(SpringLayout.EAST, acceptButton, -5, SpringLayout.WEST, optionnalButton);
        sp.putConstraint(SpringLayout.SOUTH, acceptButton, -5, SpringLayout.SOUTH, centerPanel);
        
        this.add(this.title, BorderLayout.NORTH);
        this.add(this.centerPanel, BorderLayout.CENTER);
        
    }
    
    public void setDonneesTable(AbstractTableModel m)
    {
        mainTable.setModel(m);
        mainTable.setRowHeight(50);
        mainTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        
        DefaultTableCellRenderer r = new DefaultTableCellRenderer() {
          @Override
          public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
          {
              super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
              setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
              return this;
          }
        };
        
        for(int i = 0; i < mainTable.getColumnCount(); i++)
        {
            mainTable.getColumnModel().getColumn(i).setCellRenderer(r);
        }
        
        if(m instanceof ModelListeTheme)
        {
            mainTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        }
    }
    
    public void setInterfaceUtilisateur(Droits droits)
    {
        if(mainTable.getModel() instanceof ModelListeUtilisateur)
        {
            switch(droits)
            {
                case ETUDIANT:
                case PROFESSEUR:
                    acceptButton.setText("Consulter");
                    optionnalButton.setText("Message");
                    break;
                case ADMINISTRATEUR:
                    acceptButton.setText("Ajouter");
                    optionnalButton.setText("Supprimer");
                    break;
            }
        }else if(mainTable.getModel() instanceof ModelListeTheme){
            switch(droits)
            {
                case ETUDIANT:
                    acceptButton.setText("Demander");
                    optionnalButton.setText("Proposer");
                    break;
                case PROFESSEUR:
                    acceptButton.setText("Attribuer");
                    optionnalButton.setText("Archiver");
                    break;
                case ADMINISTRATEUR:
                    acceptButton.setText("Ajouter");
                    optionnalButton.setText("Supprimer");
                    break;
            }
        }
        
    }
    
    public void setTitle(String text)
    {
        this.title.setText(text);
    }
    
    public class EcouteurBouton implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            switch(e.getActionCommand())
            {
                case "Ajouter":
                    if(mainTable.getModel() instanceof ModelListeUtilisateur){
                        mctrl.naviguerVers("Ajouter Utilisateur");
                    }else if(mainTable.getModel() instanceof ModelListeTheme){
                        mctrl.naviguerVers("Ajouter Thème");
                    }
                    break;
                case "Supprimer":
                    if(mainTable.getModel() instanceof ModelListeUtilisateur){
                        mctrl.supprimerUtilisateur((int) mainTable.getModel().getValueAt(mainTable.getSelectedRow(), 5));
                    }else if(mainTable.getModel() instanceof ModelListeTheme){
                        mctrl.naviguerVers("Supprimer Thème");
                    }
                    break;
                case "Consulter":
                    mctrl.allerVersProfil((int) mainTable.getModel().getValueAt(mainTable.getSelectedRow(), 5));
                    break;
            }
        }
        
    }
}
