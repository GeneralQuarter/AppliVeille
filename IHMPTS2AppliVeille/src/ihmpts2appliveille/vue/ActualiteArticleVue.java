/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.vue;

import ihmpts2appliveille.controleur.MainControleur;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Random;
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
    private MainControleur mctrl;
    
    private JLabel title;
    private JPanel articleHolder;
    private JScrollPane articleScroller;
    
    private Font fTitle;
    
    public ActualiteArticleVue(String title, MainControleur mctrl)
    {
        // -- Setup Controleur --
        this.mctrl = mctrl;
        this.mctrl.setActualiteArticleVue(this);
        
        this.setLayout(new BorderLayout(10, 10)); 
        
        // -- Setup Title --
        this.title = new JLabel(title);
        fTitle = new Font("Arial", Font.BOLD, 40);
        this.title.setFont(fTitle);
        this.title.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0)); // See Editor for changes...
        
        // -- Setup articleHolder --
        this.articleHolder = new JPanel();
        this.articleHolder.setLayout(new BoxLayout(this.articleHolder, BoxLayout.Y_AXIS));
        this.articleHolder.add(Box.createRigidArea(new Dimension(0,10)));
        
        // -- Setup articleScroller --
        this.articleScroller = new JScrollPane(this.articleHolder);
        this.articleScroller.setBorder(null);
        this.articleScroller.getVerticalScrollBar().setUnitIncrement(16);
        this.articleScroller.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, new Color(33,33,33))); // need to extend to side see gap up
        
        this.add(this.title, BorderLayout.NORTH);
        this.add(this.articleScroller, BorderLayout.CENTER);
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // WTF !! remove that ! ASAP 
        
        this.testInsert(); // TO REMOVE !!
    }
    
    public void ajouterArticle(ArticleListItem ali)
    {
        this.articleHolder.add(ali);
        this.articleHolder.add(Box.createRigidArea(new Dimension(0,10)));
    }
    
    public void setTitle(String title)
    {
        this.title.setText(title);
    }
    
    private void testInsert()
    {
        Random rand = new Random();
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
            "GANGLER Quentin", "01/02/12", rand.nextInt(50), rand.nextInt(5)+1));
        }
    }
}
