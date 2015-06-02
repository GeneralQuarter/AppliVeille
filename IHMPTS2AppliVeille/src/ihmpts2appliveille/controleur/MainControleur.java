/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.controleur;

import ihmpts2appliveille.modele.Cryptage;
import ihmpts2appliveille.modele.LienExterne;
import ihmpts2appliveille.modele.accesbd.RecuperationDonneesInitiales;
import ihmpts2appliveille.modele.accesbd.entites.Utilisateur;
import ihmpts2appliveille.vue.ActualiteArticleVue;
import ihmpts2appliveille.vue.EditorVue;
import ihmpts2appliveille.vue.MenuBarVue;
import ihmpts2appliveille.vue.BodyContentVue;
import ihmpts2appliveille.vue.FormAuthentificationVue;
import ihmpts2appliveille.vue.ListeVue;
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
    private ListeVue lv;
    
    private LienExterne moodle;
    private LienExterne ent;
    private LienExterne formadep;
    
    private RecuperationDonneesInitiales rdi;
    
    private Utilisateur utilisateurConnecte;
    
    public MainControleur()
    {
        rdi = new RecuperationDonneesInitiales();
        moodle = new LienExterne("https://moodle.univ-lr.fr");
        ent = new LienExterne("https://ent.univ-lr.fr");
        formadep = new LienExterne("http://www.formadepetudiant.fr/?ref=19");
    }
    
    public void connection(String login, String mdp)
    {
        if(!login.isEmpty() && !mdp.isEmpty())
        {
            utilisateurConnecte = rdi.recupererUtilisateurEnConnection(login);
            if(utilisateurConnecte != null)
            {
                if(utilisateurConnecte.getMdp().equals(Cryptage.getEncodedPassword(mdp)))
                {
                    mmbv.setProfilName(utilisateurConnecte.getNom());
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
    }
    
    public void deconnection()
    {
       if(true){
           utilisateurConnecte = null;
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
            case "Formadep":
                formadep.browse();
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
                ev.setTitle("Nouvel Article");
                bcv.changeMainContent(ev);
                break;
            case "Nouveau Message":
                ev.setTitle("Nouveau Message");
                bcv.changeMainContent(ev);
                break;
            case "Liste des thèmes":
                bcv.changeMainContent(lv);
                break;
        }
    }

    public void setListeVue(ListeVue lv) {
        this.lv = lv;
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
