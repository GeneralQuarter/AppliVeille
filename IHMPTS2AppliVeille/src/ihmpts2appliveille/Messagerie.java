/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;

/**
 *
 * @author x1QG1x
 */
public class Messagerie extends JPanel{
    private MainSession ms;
    
    private SpringLayout layout;
    
    private JPanel messagesHolder;
    private JScrollPane messagesScroller;
    
    private JLabel title;
    private QGButton newMessage;
    
    private Font f;
    
    private EcouteurBouton ec;
    
    public Messagerie(MainSession ms)
    {
        super(); // Utile ??
        this.ms = ms;
        this.setBackground(new Color(236, 239, 241));
        this.setPreferredSize(new Dimension(380,100));
        layout = new SpringLayout();
        this.setLayout(layout);
        
        f = new Font("Arial", 0, 20);
        
        // -- Setup NewMessage --
        newMessage = new QGButton("Nouveau Message", new Color(33, 150, 243), new Color(66, 165, 245), Color.white, f);
        newMessage.setPreferredSize(new Dimension(370,50));
        add(newMessage);
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
        for(int i = 0; i < 10; i++)
        {
            messagesHolder.add(new MessageListItem("Message " + (i+1), "Lorem ipsum dolor sit amet, consectetur "
                    + "adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. "
                    + "Ut enim ad minim veniam, quis nostrud", "Auteur " + (i+1), "05/12/2015", this));
            messagesHolder.add(Box.createRigidArea(new Dimension(0,5)));
        }
        messagesScroller = new JScrollPane(messagesHolder);
        messagesScroller.setBorder(null);
        messagesScroller.getVerticalScrollBar().setUnitIncrement(16);
        messagesScroller.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(33,33,33)));
        this.add(messagesScroller);
        layout.putConstraint(SpringLayout.WEST, messagesScroller, 5, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, messagesScroller, 10, SpringLayout.SOUTH, title);
        layout.putConstraint(SpringLayout.SOUTH, messagesScroller, -5, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.EAST, messagesScroller, -5, SpringLayout.EAST, this);
    }
}
