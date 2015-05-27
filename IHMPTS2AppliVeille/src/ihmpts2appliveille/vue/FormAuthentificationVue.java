/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.vue;

import ihmpts2appliveille.controleur.MainControleur;
import ihmpts2appliveille.modele.AppliColor;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author x1QG1x
 */
public class FormAuthentificationVue extends JPanel{
    private MainControleur mctrl;
    
    private JPanel form;
    private JPanel fields;
    
    private GridBagConstraints gbc;
    
    private JLabel connexionLabel;
    private JLabel loginLabel;
    private JLabel mdpLabel;
    private JLabel infoLabel;
    
    private JTextField loginField;
    private JPasswordField mdpField;
    
    private QGButton connexionButton;
    
    private Dimension formDim;
    private Dimension frameDim;
    
    public FormAuthentificationVue(MainControleur mctrl)
    {
        // -- Setup MainFrame --
        super(); // Utile ?
        
        // -- Setup COntroleur --
        this.mctrl = mctrl;
        this.mctrl.setFormAuthentificationVue(this);
        
        setBackground(AppliColor.GRAY.getColor());
        setLayout(new GridBagLayout());
        setMinimumSize(formDim);
        
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
        
        infoLabel = new JLabel("Entrez vos identifiants de connexion", JLabel.CENTER);
        mdpLabel.setFont(f);
        
        // -- Setup Fields --        
        loginField = new JTextField();
        loginField.setFont(f);
        loginField.setPreferredSize(new Dimension(800, 50));
        loginField.setMaximumSize(loginField.getPreferredSize());
        loginField.addKeyListener(new EcouteurToucheEntree());
        
        mdpField = new JPasswordField();
        mdpField.setFont(f);
        mdpField.setPreferredSize(new Dimension(800, 50));
        mdpField.setMaximumSize(mdpField.getPreferredSize());
        mdpField.addKeyListener(new EcouteurToucheEntree());
        
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
        fields.add(Box.createRigidArea(new Dimension(0,10)));
        fields.add(infoLabel);
        
        // -- Setup connexionButton --
        connexionButton = new QGButton("CONNEXION", AppliColor.BLUE.getColor(), AppliColor.LIGHT_BLUE.getColor(), Color.white, f);
        connexionButton.addActionListener(new EcouteurConnexion());
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
        //this.mw.setMinimumSize(frameDim); Need to reimplments that !!
        
        // -- Info Message --
        infoLabel.setVisible(false);
        infoLabel.setForeground(Color.red);
    }
    
    public class EcouteurConnexion implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            mctrl.connection(loginField.getText(), mdpField.getText());
        }
        
    }
    
    public class EcouteurToucheEntree implements KeyListener
    {

        @Override
        public void keyTyped(KeyEvent e) {
            
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ENTER)
            {
                connexionButton.doClick();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            
        }
        
    }
    
    public void afficherErreurIdentifiants()
    {
        infoLabel.setVisible(true);
        infoLabel.setText("Identifiant ou mot de passe incorrect");
    }
    
    public void resetConnection()
    {
        loginField.setText("");
        mdpField.setText("");
        infoLabel.setVisible(false);
    }
}
