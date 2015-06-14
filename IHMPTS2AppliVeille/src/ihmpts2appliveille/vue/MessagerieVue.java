/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.vue;

import ihmpts2appliveille.controleur.MainControleur;
import ihmpts2appliveille.modele.AppliColor;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;

/**
 * Messagerie vue est l'interface qui s'affiche à droite de l'écran quelque soit la position de l'utilisateur dans l'application
 * @author x1QG1x
 */
public class MessagerieVue extends JPanel{
    private MainControleur mctrl;
    
    private SpringLayout layout;
    
    private JPanel messagesHolder;
    private JScrollPane messagesScroller;
    
    private JLabel title;
    private QGButton newMessage;
    
    private Font f;
    
    /**
     * Constructeur de la vue
     * @param mctrl main controleur de l'application
     */
    public MessagerieVue(MainControleur mctrl)
    {
        super(); // Utile ??
        this.mctrl = mctrl;
        this.mctrl.setMessagerieVue(this);
        this.setBackground(AppliColor.GRAY_BG.getColor());
        this.setPreferredSize(new Dimension(380,100));
        this.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.LIGHT_GRAY));
        layout = new SpringLayout();
        this.setLayout(layout);
        
        f = new Font("Arial", 0, 20);
        
        // -- Setup NewMessage --
        newMessage = new QGButton("Nouveau Message", AppliColor.BLUE.getColor(), AppliColor.LIGHT_BLUE.getColor(), Color.white, f);
        newMessage.setPreferredSize(new Dimension(370,50));
        
        add(newMessage);
        newMessage.addActionListener(new EcouteurNouveauMessage());
        
        layout.putConstraint(SpringLayout.WEST, newMessage, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, newMessage, 10, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.EAST, newMessage, -10, SpringLayout.EAST, this);
        
        // -- Setup Label --
        title = new JLabel("Boite de réception");
        title.setFont(f);
        this.add(title);
        
        layout.putConstraint(SpringLayout.WEST, title, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, title, 10, SpringLayout.SOUTH, newMessage);
        
        // -- Setup list item --
        messagesHolder = new JPanel();
        messagesHolder.setLayout(new BoxLayout(messagesHolder, BoxLayout.Y_AXIS));
        messagesHolder.add(Box.createRigidArea(new Dimension(0,5)));
        
        messagesScroller = new JScrollPane(messagesHolder);
        messagesScroller.setBorder(null);
        messagesScroller.getVerticalScrollBar().setUnitIncrement(16);
        messagesScroller.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(33,33,33)));
        
        this.add(messagesScroller);
        
        layout.putConstraint(SpringLayout.WEST, messagesScroller, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, messagesScroller, 10, SpringLayout.SOUTH, title);
        layout.putConstraint(SpringLayout.SOUTH, messagesScroller, -5, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.EAST, messagesScroller, -5, SpringLayout.EAST, this);
        
        testInsert(); // TO REMOVE !!
    }
    
    private void testInsert()
    {
        for(int i = 0; i < 10; i++)
        {
            messagesHolder.add(new MessageListItem("Message " + (i+1), "Lorem ipsum dolor sit amet, consectetur "
                    + "adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. "
                    + "Ut enim ad minim veniam, quis nostrud", "Auteur " + (i+1), "05/12/2015"));
            messagesHolder.add(Box.createRigidArea(new Dimension(0,5)));
        }
    }
    
    /**
     * Ecouteur sur le bouton pour l'IHM de rédaction d'un nouveau message
     */
    public class EcouteurNouveauMessage implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            mctrl.naviguerVers(e.getActionCommand());
        }
        
    }
}
