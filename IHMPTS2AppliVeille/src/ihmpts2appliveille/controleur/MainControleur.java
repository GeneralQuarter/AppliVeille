/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.controleur;

import ihmpts2appliveille.modele.LienExterne;
import ihmpts2appliveille.modele.Session;
import ihmpts2appliveille.modele.Statut;
import ihmpts2appliveille.vue.ActualiteArticleVue;
import ihmpts2appliveille.vue.EditorVue;
import ihmpts2appliveille.vue.MenuBarVue;
import ihmpts2appliveille.vue.BodyContentVue;
import ihmpts2appliveille.vue.FormAuthentificationVue;
import ihmpts2appliveille.vue.MainWindowVue;
import ihmpts2appliveille.vue.MessagerieVue;

/**
 *
 * @author x1QG1x
 */
public class MainControleur {
    // -- Vue --
    private MainWindowVue mmv;
    
    private ActualiteArticleVue aav;
    private EditorVue ev;
    
    private MessagerieVue mv;
    private MenuBarVue mmbv;
    private BodyContentVue bcv;
    private FormAuthentificationVue fav;
    
    private Session session;
    private LienExterne moodle;
    private LienExterne ent;
    
    public MainControleur()
    {
        session = new Session();
        moodle = new LienExterne("https://moodle.univ-lr.fr");
        ent = new LienExterne("https://ent.univ-lr.fr");
    }
    
    public void connection(String login, String mdp)
    {
        if(!login.isEmpty() && !mdp.isEmpty())
        {
            session.connection(login, mdp);
            if(session.getStatut() == Statut.CONNECTE)
            {
                mmbv.setProfilName(session.getNomPrenom());
                aav.setTitle("Actualités");
                bcv.changeMainContent(aav);
                mmv.changeMainFrame(bcv, true);
            }else{
                fav.afficherErreurIdentifiants();
            } 
        }else{
            fav.afficherErreurIdentifiants();
        }
    }
    
    public void deconnection()
    {
       if(session.getStatut() == Statut.CONNECTE){
           session.deconnexion();
           fav.resetConnection();
           mmv.changeMainFrame(fav, false);
       }else{
           //Message erreur
       }
    }
    
    public void lienVersInternet(String nom)
    {
        switch(nom)
        {
            case "Moodle":
                moodle.browse();
                break;
            case "ENT":
                ent.browse();
                break;
        }
    }
    
    public void naviguerVers(String nom)
    {
        switch(nom)
        {
            case "ACCUEIL":
                aav.setTitle("Actualités");
                bcv.changeMainContent(aav);
                break;
            case "Tous les articles":
                aav.setTitle("Articles");
                bcv.changeMainContent(aav);
                break;
            case "Nouvel article...":
                bcv.changeMainContent(ev);
                break;
        }
    }
    
    public void setMainWindowVue(MainWindowVue mmv) {
        this.mmv = mmv;
    }

    public void setActualiteArticleVue(ActualiteArticleVue aav) {
        this.aav = aav;
    }

    public void setEditorVue(EditorVue ev) {
        this.ev = ev;
    }

    public void setMessagerieVue(MessagerieVue mv) {
        this.mv = mv;
    }

    public void setMenuBarVue(MenuBarVue mmbv) {
        this.mmbv = mmbv;
    }

    public void setBodyContentVue(BodyContentVue bcv) {
        this.bcv = bcv;
    }

    public void setFormAuthentificationVue(FormAuthentificationVue fav) {
        this.fav = fav;
    }
}
