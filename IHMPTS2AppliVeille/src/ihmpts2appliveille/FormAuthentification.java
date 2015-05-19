/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author x1QG1x
 */
public class FormAuthentification extends JPanel{
    private MainWindow mw;
    
    private EcouteurBouton ec;
    
    private JPanel form;
    private JPanel fields;
    
    private GridBagConstraints gbc;
    
    private JLabel connexionLabel;
    private JLabel loginLabel;
    private JLabel mdpLabel;
    
    private JTextField loginField;
    private JPasswordField mdpField;
    
    private QGButton connexionButton;
    
    private Dimension formDim;
    private Dimension frameDim;
    
    public FormAuthentification(MainWindow mw)
    {
        // -- Setup MainFrame --
        super(); // Utile ?
        setBackground(new Color(189,189,189));
        setLayout(new GridBagLayout());
        setMinimumSize(formDim);
        this.mw = mw;
        ec = new EcouteurBouton(this.mw);
        
        formDim = new Dimension(350, 400);
        frameDim = new Dimension(450, 500);
        
        // -- Setup gbc --
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        
        // -- Setup Labels --
        Font f = new Font("Arial", 0, 30);
        connexionLabel = new JLabel("Authentification", JLabel.CENTER);
        connexionLabel.setFont(f);
        
        f = new Font("Arial", 0, 20);
        loginLabel = new JLabel("Identifiant");
        loginLabel.setFont(f);
        
        mdpLabel = new JLabel("Mot de Passe");
        mdpLabel.setFont(f);
        
        // -- Setup Fields --        
        loginField = new JTextField();
        loginField.setFont(f);
        loginField.setPreferredSize(new Dimension(800, 50));
        loginField.setMaximumSize(loginField.getPreferredSize());
        
        mdpField = new JPasswordField();
        mdpField.setFont(f);
        mdpField.setPreferredSize(new Dimension(800, 50));
        mdpField.setMaximumSize(mdpField.getPreferredSize());
        
        // -- Setup Fields Panel --
        fields = new JPanel();
        fields.setLayout(new BoxLayout(fields, BoxLayout.Y_AXIS));
        fields.add(Box.createRigidArea(new Dimension(0,30)));
        fields.add(loginLabel);
        fields.add(Box.createRigidArea(new Dimension(0,10)));
        fields.add(loginField);
        fields.add(Box.createRigidArea(new Dimension(0,20)));
        fields.add(mdpLabel);
        fields.add(Box.createRigidArea(new Dimension(0,10)));
        fields.add(mdpField);
        
        // -- Setup connexionButton --
        connexionButton = new QGButton("CONNEXION", new Color(33, 150, 243), new Color(66, 165, 245), Color.white, f);
        connexionButton.addActionListener(ec);
        connexionButton.setPreferredSize(new Dimension(100,50));
         
        // -- Setup Form -- 
        form = new JPanel();
        form.setPreferredSize(formDim);
        form.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        form.setLayout(new BorderLayout(20,20));
        form.add(connexionLabel, BorderLayout.NORTH);
        form.add(fields, BorderLayout.CENTER);
        form.add(connexionButton, BorderLayout.SOUTH);
        add(form, gbc);
        
        // -- Change preferred dim for JFrame --
        this.mw.setMinimumSize(frameDim);
    }
}
