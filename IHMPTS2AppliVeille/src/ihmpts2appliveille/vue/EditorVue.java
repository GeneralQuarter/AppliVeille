/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.vue;

import ihmpts2appliveille.controleur.MainControleur;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

/**
 *
 * @author x1QG1x
 */
public class EditorVue extends JPanel{
    private MainControleur mctrl;
    
    private JPanel contentPanel;
    private SpringLayout sp;
    
    private JLabel title;
    
    private JLabel articleTitleLabel;
    private JTextField articleTitleField;
    
    private JLabel contentLabel;
    private JTextPane contentField;
    private JScrollPane contentScroller;
    
    private JButton boldButton;
    private JButton italicButton;
    private JButton underlineButton;
    private JButton linksButton;
    private JButton addImageButton;
    
    private JComboBox stylesComboxBox;
    
    private QGButton cancelEditButton;
    private QGButton acceptEditButton;
    private QGButton optionnalActionButton;
    
    private Font f;
            
    public EditorVue(String title, MainControleur mctrl) // Need to add other parameters ... (new message ?)
    {
        this.mctrl = mctrl;
        this.mctrl.setEditorVue(this);
        
        this.setLayout(new BorderLayout());
        
        // -- Setup labels --
        f = new Font("Arial", Font.BOLD, 40);
        this.title = new JLabel(title);
        this.title.setFont(f);
        this.title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        f = new Font("Arial", 0, 18);
        articleTitleLabel = new JLabel("Titre");
        articleTitleLabel.setFont(f);
        contentLabel = new JLabel("Contenu");
        contentLabel.setFont(f);
        
        // -- Setup Text Fields --
        articleTitleField = new JTextField();
        articleTitleField.setFont(f);
        contentField = new JTextPane();
        contentField.setText("Test");
        contentField.setContentType("text/html");
        String bodyRule = "body { font-family:\"Arial\"; " +
            "font-size:14pt;}";
        ((HTMLDocument)contentField.getDocument()).getStyleSheet().addRule(bodyRule);
        ((HTMLEditorKit)contentField.getEditorKit()).setDefaultCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));

    
        contentScroller = new JScrollPane(contentField);
        contentScroller.setPreferredSize(new Dimension(1000,1000));
        
        
        // -- Setup Buttons --
        f = new Font("Arial", Font.BOLD, 16);
        boldButton = new JButton("G");
        boldButton.setFont(f);
        boldButton.addActionListener(new EcouteurBoutonStyle());
        boldButton.setPreferredSize(new Dimension(45, 30));
        f = new Font("Arial", Font.ITALIC, 16);
        italicButton = new JButton("I");
        italicButton.setFont(f);
        italicButton.addActionListener(new EcouteurBoutonStyle());
        italicButton.setPreferredSize(new Dimension(45, 30));
        f = new Font("Arial", 0, 16);
        underlineButton = new JButton("U");
        underlineButton.setFont(f);
        underlineButton.addActionListener(new EcouteurBoutonStyle());
        underlineButton.setPreferredSize(new Dimension(45, 30));
        linksButton = new JButton("Lien");
        linksButton.addActionListener(new EcouteurBoutonStyle());
        linksButton.setPreferredSize(new Dimension(70, 30));
        linksButton.setFont(f);
        addImageButton = new JButton("Ajouter image");
        addImageButton.addActionListener(new EcouteurBoutonStyle());
        addImageButton.setPreferredSize(new Dimension(150, 30));
        addImageButton.setFont(f);
        acceptEditButton = new QGButton("Publier", new Color(33, 150, 243), new Color(66, 165, 245), Color.white, f);
        acceptEditButton.setPreferredSize(new Dimension(100, 30));
        cancelEditButton = new QGButton("Annuler", new Color(189, 189, 189), Color.white, Color.black, f);
        cancelEditButton.setPreferredSize(new Dimension(100, 30));
        optionnalActionButton = new QGButton("Brouillon", new Color(189, 189, 189), Color.white, Color.black, f);
        
        // -- Setup ComboBox --
        String[] styles = {"Titre 1", "Titre 2", "Paragraphe"}; 
        stylesComboxBox = new JComboBox(styles);
        stylesComboxBox.setSelectedIndex(2);
        stylesComboxBox.addActionListener(new EcouteurBoutonStyle());
        stylesComboxBox.setFont(f);
        stylesComboxBox.setPreferredSize(new Dimension(150, 28));
        
        // -- Setup content --
        contentPanel = new JPanel();
        contentPanel.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, new Color(33,33,33)));
        sp = new SpringLayout();
        contentPanel.setLayout(sp);
        contentPanel.add(articleTitleLabel);
        contentPanel.add(articleTitleField);
        contentPanel.add(contentLabel);
        contentPanel.add(boldButton);
        contentPanel.add(italicButton);
        contentPanel.add(underlineButton);
        contentPanel.add(stylesComboxBox);
        contentPanel.add(contentScroller);
        contentPanel.add(acceptEditButton);
        contentPanel.add(cancelEditButton);
        contentPanel.add(linksButton);
        contentPanel.add(addImageButton);
        
        sp.putConstraint(SpringLayout.WEST, articleTitleLabel, 5, SpringLayout.WEST, contentPanel);
        sp.putConstraint(SpringLayout.NORTH, articleTitleLabel, 20, SpringLayout.NORTH, contentPanel);
        
        sp.putConstraint(SpringLayout.WEST, articleTitleField, 5, SpringLayout.WEST, contentPanel);
        sp.putConstraint(SpringLayout.NORTH, articleTitleField, 5, SpringLayout.SOUTH, articleTitleLabel);
        sp.putConstraint(SpringLayout.EAST, articleTitleField, -5, SpringLayout.EAST, contentPanel);
        
        sp.putConstraint(SpringLayout.WEST, contentLabel, 5, SpringLayout.WEST, contentPanel);
        sp.putConstraint(SpringLayout.NORTH, contentLabel, 20, SpringLayout.SOUTH, articleTitleField);
        
        sp.putConstraint(SpringLayout.WEST, boldButton, 5, SpringLayout.WEST, contentPanel);
        sp.putConstraint(SpringLayout.NORTH, boldButton, 5, SpringLayout.SOUTH, contentLabel);
        
        sp.putConstraint(SpringLayout.WEST, italicButton, 5, SpringLayout.EAST, boldButton);
        sp.putConstraint(SpringLayout.NORTH, italicButton, 5, SpringLayout.SOUTH, contentLabel);
        
        sp.putConstraint(SpringLayout.WEST, underlineButton, 5, SpringLayout.EAST, italicButton);
        sp.putConstraint(SpringLayout.NORTH, underlineButton, 5, SpringLayout.SOUTH, contentLabel);
        
        sp.putConstraint(SpringLayout.WEST, stylesComboxBox, 5, SpringLayout.EAST, underlineButton);
        sp.putConstraint(SpringLayout.NORTH, stylesComboxBox, 6, SpringLayout.SOUTH, contentLabel);
        
        sp.putConstraint(SpringLayout.WEST, linksButton, 5, SpringLayout.EAST, stylesComboxBox);
        sp.putConstraint(SpringLayout.NORTH, linksButton, 5, SpringLayout.SOUTH, contentLabel);
        
        sp.putConstraint(SpringLayout.WEST, addImageButton, 5, SpringLayout.EAST, linksButton);
        sp.putConstraint(SpringLayout.NORTH, addImageButton, 5, SpringLayout.SOUTH, contentLabel);
        
        sp.putConstraint(SpringLayout.WEST, contentScroller, 5, SpringLayout.WEST, contentPanel);
        sp.putConstraint(SpringLayout.NORTH, contentScroller, 5, SpringLayout.SOUTH, boldButton);
        sp.putConstraint(SpringLayout.EAST, contentScroller, -5, SpringLayout.EAST, contentPanel);
        sp.putConstraint(SpringLayout.SOUTH, contentScroller, -5, SpringLayout.SOUTH, contentPanel);
        
        sp.putConstraint(SpringLayout.EAST, cancelEditButton, -5, SpringLayout.EAST, contentPanel);
        sp.putConstraint(SpringLayout.SOUTH, cancelEditButton, -5, SpringLayout.NORTH, contentScroller);
        
        sp.putConstraint(SpringLayout.SOUTH, acceptEditButton, -5, SpringLayout.NORTH, contentScroller);
        sp.putConstraint(SpringLayout.EAST, acceptEditButton, -5, SpringLayout.WEST, cancelEditButton);
        
        this.add(this.title, BorderLayout.NORTH);
        this.add(contentPanel, BorderLayout.CENTER);
    }
    
    public class EcouteurBoutonStyle implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(boldButton))
            {
                contentField.getActionMap().get("font-bold").actionPerformed(null);
                contentField.requestFocus();
            }
            
            if(e.getSource().equals(italicButton))
            {
                contentField.getActionMap().get("font-italic").actionPerformed(null);
                contentField.requestFocus();
            }
            
            if(e.getSource().equals(underlineButton))
            {
                contentField.getActionMap().get("font-underline").actionPerformed(null);
                contentField.requestFocus();
            }
            
            if(e.getSource().equals(stylesComboxBox))
            {
                switch(stylesComboxBox.getSelectedIndex())
                {
                    case 0:
                        contentField.getActionMap().get("font-size-24").actionPerformed(null);
                        break;
                    case 1:
                        contentField.getActionMap().get("font-size-16").actionPerformed(null);
                        break;
                    case 2:
                        contentField.getActionMap().get("font-size-14").actionPerformed(null);
                        break;
                }
                contentField.requestFocus();
            }
            
            if(e.getSource().equals(addImageButton)) // Don't do it all here maybe ? call controleur...
            {
                String url = JOptionPane.showInputDialog(null, "Entrer l'url de l'image", "Ajouter une image", JOptionPane.QUESTION_MESSAGE);
                if(url.matches("https?://.+\\..+\\.(png|jpeg|jpg|bmp)"))
                {
                    HTMLDocument doc = (HTMLDocument) contentField.getDocument();

                    try {
                        String sale = "9:12:45:63:5:76:2:1:87:3:2:45:3";
                        doc.insertString(contentField.getCaretPosition(), sale, null);
                        String[] textSplit = contentField.getText().split(sale);
                        contentField.setText(textSplit[0] + "<img src=\""+url+"\"/>" + textSplit[1]);
                    } catch (BadLocationException ex) {
                        Logger.getLogger(EditorVue.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "URL : " + url + " invalide", "URL Invalide", JOptionPane.ERROR_MESSAGE);
                }
            }
            
            if(e.getSource().equals(linksButton)) // Don't do it all here maybe ? call controleur...
            {
                String url = JOptionPane.showInputDialog(null, "Entrer l'url du lien", "Ajouter un lien", JOptionPane.QUESTION_MESSAGE);
                if(url.matches("https?://.+\\..+"))
                {
                    String sel = contentField.getSelectedText();
                    HTMLDocument doc = (HTMLDocument) contentField.getDocument();
                    String sale = "9:12:45:63:5:76:2:1:87:3:2:45:3";
                    try {
                        doc.insertString(contentField.getCaretPosition(), sale, null);
                    } catch (BadLocationException ex) {
                        Logger.getLogger(EditorVue.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String[] textSplit = contentField.getText().split(sale+sel);
                    contentField.setText(textSplit[0] + "<a href=\"" + url + "\">" + sel + "</a>" + textSplit[1]);
                }else{
                    JOptionPane.showMessageDialog(null, "URL : " + url + " invalide", "URL Invalide", JOptionPane.ERROR_MESSAGE);
                } 
            }
        } 
    }
    
    public void setTitle(String title)
    {
        this.title.setText(title);
    }
}
