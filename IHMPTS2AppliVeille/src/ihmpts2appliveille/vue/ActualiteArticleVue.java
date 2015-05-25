/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.vue;

import ihmpts2appliveille.vue.ArticleListItem;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author x1QG1x
 */
public class ActualiteArticleVue extends JPanel{
    private MainSessionVue ms;
    
    private JLabel title;
    private JPanel articleHolder;
    private JScrollPane articleScroller;
    
    private Font fTitle;
    
    public ActualiteArticleVue(MainSessionVue ms, String title)
    {
        this.ms = ms;
        this.setLayout(new BorderLayout(10, 10));
        
        this.title = new JLabel(title);
        fTitle = new Font("Arial", Font.BOLD, 40);
        this.title.setFont(fTitle);
        this.title.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        
        
        this.articleHolder = new JPanel();
        this.articleHolder.setLayout(new BoxLayout(this.articleHolder, BoxLayout.Y_AXIS));
        this.articleHolder.add(Box.createRigidArea(new Dimension(0,10)));
        for(int i = 0; i < 50; i++)
        {
            this.ajouterArticle(new ArticleListItem("Google","Les Google Glass au RDV" + (i+1), 
                "EDIT: After you told me that that didn't work then I decided "
                        + "to take a second crack at it... First, put your "
                        + "images into a completely separate folder. "
                        + "I usually call this /res. Next, put your image "
                        + "in there. Now, for loading I took a completely "
                        + "different route. I decided to use ImageIO instead "
                        + "of default loading. To load the image, you use this code:",
            "GANGLER Quentin", "01/02/12", 40+i, this));
        }
        
        this.articleScroller = new JScrollPane(this.articleHolder);
        this.articleScroller.setBorder(null);
        this.articleScroller.getVerticalScrollBar().setUnitIncrement(16);
        this.articleScroller.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, new Color(33,33,33)));
        
        this.add(this.title, BorderLayout.NORTH);
        this.add(this.articleScroller, BorderLayout.CENTER);
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));  
    }
    
    public void ajouterArticle(ArticleListItem ali)
    {
        this.articleHolder.add(ali);
        this.articleHolder.add(Box.createRigidArea(new Dimension(0,10)));
    }
}
