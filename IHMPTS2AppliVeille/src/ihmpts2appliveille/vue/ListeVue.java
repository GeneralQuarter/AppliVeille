/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.vue;

import ihmpts2appliveille.controleur.MainControleur;
import ihmpts2appliveille.modele.AppliColor;
import ihmpts2appliveille.modele.ModelListe;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

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
    private QGButton deleteButton;
    
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
        mainTable = new JTable();
        scrollerTable = new JScrollPane(mainTable);
        
        f = new Font("Arial", 0, 16);
        
        acceptButton = new QGButton("Demander", AppliColor.BLUE.getColor(), AppliColor.LIGHT_BLUE.getColor(), Color.white, f);
        optionnalButton = new QGButton("Archiver", AppliColor.BLUE.getColor(), AppliColor.LIGHT_BLUE.getColor(), Color.white, f);
        deleteButton = new QGButton("Supprimer", AppliColor.BLUE.getColor(), AppliColor.LIGHT_BLUE.getColor(), Color.white, f);
        
        addTitle = new JLabel("Proposer thème");
        addField = new JTextField();
        confirmAddButton = new QGButton("Valider", AppliColor.BLUE.getColor(), AppliColor.LIGHT_BLUE.getColor(), Color.white, f);
        
        sp = new SpringLayout();
        
        centerPanel.setLayout(sp);
        centerPanel.add(mainTable);
        centerPanel.add(acceptButton);
        centerPanel.add(optionnalButton);
        centerPanel.add(deleteButton);
        
        sp.putConstraint(SpringLayout.WEST, mainTable, 5, SpringLayout.WEST, centerPanel);
        sp.putConstraint(SpringLayout.EAST, mainTable, -5, SpringLayout.EAST, centerPanel);
        sp.putConstraint(SpringLayout.NORTH, mainTable, 5, SpringLayout.NORTH, centerPanel);
        sp.putConstraint(SpringLayout.SOUTH, mainTable, -5, SpringLayout.NORTH, acceptButton);
        
        sp.putConstraint(SpringLayout.EAST, deleteButton, -5, SpringLayout.EAST, centerPanel);
        sp.putConstraint(SpringLayout.SOUTH, deleteButton, -5, SpringLayout.SOUTH, centerPanel);
        
        sp.putConstraint(SpringLayout.EAST, optionnalButton, -5, SpringLayout.WEST, deleteButton);
        sp.putConstraint(SpringLayout.SOUTH, optionnalButton, -5, SpringLayout.SOUTH, centerPanel);
        
        sp.putConstraint(SpringLayout.EAST, acceptButton, -5, SpringLayout.WEST, optionnalButton);
        sp.putConstraint(SpringLayout.SOUTH, acceptButton, -5, SpringLayout.SOUTH, centerPanel);
        
        this.add(this.title, BorderLayout.NORTH);
        this.add(this.centerPanel, BorderLayout.CENTER);
        
    }
    
    public void setDonneesTable(ModelListe ml)
    {
        mainTable.setModel(ml);
    }
}
