/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihmpts2appliveille.controleur;

import ihmpts2appliveille.modele.Cryptage;
import ihmpts2appliveille.modele.Droits;
import ihmpts2appliveille.modele.LienExterne;
import ihmpts2appliveille.modele.ModelListeTheme;
import ihmpts2appliveille.modele.ModelListeUtilisateur;
import ihmpts2appliveille.modele.Statut;
import ihmpts2appliveille.modele.accesbd.Donnees;
import ihmpts2appliveille.modele.accesbd.EnregistrementDonnees;
import ihmpts2appliveille.modele.accesbd.RecuperationDonneesInitiales;
import ihmpts2appliveille.modele.accesbd.entites.Article;
import ihmpts2appliveille.modele.accesbd.entites.Commentaire;
import ihmpts2appliveille.modele.accesbd.entites.Message;
import ihmpts2appliveille.modele.accesbd.entites.Utilisateur;
import ihmpts2appliveille.vue.ActualiteArticleVue;
import ihmpts2appliveille.vue.AjoutThemeVue;
import ihmpts2appliveille.vue.AjoutUtilisateurVue;
import ihmpts2appliveille.vue.ArticleCommentairesVue;
import ihmpts2appliveille.vue.EditorVue;
import ihmpts2appliveille.vue.MenuBarVue;
import ihmpts2appliveille.vue.BodyContentVue;
import ihmpts2appliveille.vue.FormAuthentificationVue;
import ihmpts2appliveille.vue.ListeVue;
import ihmpts2appliveille.vue.MainWindowVue;
import ihmpts2appliveille.vue.MessageVue;
import ihmpts2appliveille.vue.MessagerieVue;
import ihmpts2appliveille.vue.NouveauMessageVue;
import ihmpts2appliveille.vue.ProfilVue;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

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
    private AjoutUtilisateurVue auv;
    private AjoutThemeVue atv;
    private ArticleCommentairesVue acv;
    
    private LienExterne moodle;
    private LienExterne ent;
    private LienExterne formadep;
    
    private Donnees d;
    private RecuperationDonneesInitiales rdi;
    private EnregistrementDonnees ed;
    
    private Utilisateur utilisateurConnecte;
    
    public MainControleur()
    {
        d = new Donnees();
        rdi = new RecuperationDonneesInitiales(d);
        ed = new EnregistrementDonnees(d);
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
                    if(rdi.recupererUtilisateur(utilisateurConnecte.getIdUtilisateur()).getEtat() == Statut.DECONNECTE)
                    {
                        mmbv.setInterfaceUtilisateur(utilisateurConnecte);
                        mmv.changeMainFrame(bcv, true);
                        naviguerVers("ACCUEIL");
                        ed.setUtilisateurConnecte(utilisateurConnecte.getIdUtilisateur());
                        utilisateurConnecte.setNbConn(utilisateurConnecte.getNbConn()+1);
                        d.ajouterUtilisateur(utilisateurConnecte);
                    }else{
                        fav.afficherErreur("Vous êtes déjà connecté sur un autre poste");
                    }
                }else{
                     fav.afficherErreur("Login ou mot de passe incorrect");
                }
            }else{
                fav.afficherErreur("Login ou mot de passe incorrect (ou connexion impossible)");
            }
        }
    }
    
    public void fermerFenetre()
    {
        if(utilisateurConnecte != null)
            deconnection();
        mmv.dispose();
    }
    
    public void deconnection()
    {
           ed.setUtilisateurDeconnecte(utilisateurConnecte.getIdUtilisateur());
           utilisateurConnecte = null;
           d = new Donnees();
           rdi = new RecuperationDonneesInitiales(d);
           ed = new EnregistrementDonnees(d);
           fav.resetConnection();
           mmv.changeMainFrame(fav, false);
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
        mv.updateMessagerie(rdi.recupererBoite(utilisateurConnecte.getIdUtilisateur()), rdi.recupererUtilisateurs());
        switch(nom)
        {
            case "ACCUEIL":
                aav.setTitle("Actualités");
                aav.setArticles(rdi.recupererArticles(), rdi.recupererThemes(), rdi.recupererUtilisateurs());
                bcv.changeMainContent(aav);
                break;
            case "Tous les articles":
                aav.setTitle("Articles");
                aav.setArticles(rdi.recupererArticles(), rdi.recupererThemes(), rdi.recupererUtilisateurs());
                bcv.changeMainContent(aav);
                break;
            case "Nouvel article...":
                ev.nouvelArticle();
                bcv.changeMainContent(ev);
                break;
            case "Nouveau Message":
                bcv.changeMainContent(new NouveauMessageVue(new ArrayList<>(rdi.recupererUtilisateurs().values()), this));
                break;
            case "Liste des thèmes":
                lv.setTitle("Liste des thèmes");
                lv.setDonneesTable(new ModelListeTheme(rdi.recupererThemes(), rdi.recupererUtilisateurs()));
                lv.setInterfaceUtilisateur(utilisateurConnecte.getTypeProfil());
                bcv.changeMainContent(lv);
                break;
            case "Liste des utilisateurs":
                lv.setTitle("Liste des utilisateurs");
                lv.setDonneesTable(new ModelListeUtilisateur(rdi.recupererUtilisateurs()));
                lv.setInterfaceUtilisateur(utilisateurConnecte.getTypeProfil());
                bcv.changeMainContent(lv);
                break;
            case "Ajouter Utilisateur":
                bcv.changeMainContent(auv);
                break;
            case "Ajouter Thème":
                bcv.changeMainContent(atv);
                break;
            case "Mon profil":
                allerVersProfil(utilisateurConnecte.getIdUtilisateur());
                break;
            case "Editer mon thème":
                allerVersProfil(utilisateurConnecte.getIdUtilisateur());
                break;
            case "Mes articles":
                aav.setTitle("Mes articles");
                aav.setArticleUtilisateur(recupererArticleUtilisateur(utilisateurConnecte.getIdUtilisateur()), rdi.recupererThemeUtilisateur(utilisateurConnecte.getIdUtilisateur()), utilisateurConnecte);
                bcv.changeMainContent(aav);
        }
    }
    
    public void ajoutUtilisateur(String nom, String identifiant, String mdp, String typeProfil) {
        ed.ajoutUtilisateur(nom, identifiant, mdp, typeProfil);
    }
    
    public void ajoutTheme(String intitule, String description){
        ed.ajoutTheme(intitule, description);
    }
    
    public void ajouterArticle(String titre, String content)
    {
        if(content.length() <= 4000)
        {
            if (!titre.isEmpty()) {
                if (rdi.recupererThemeUtilisateur(utilisateurConnecte.getIdUtilisateur()) != null) {
                    ed.ajoutArticle(utilisateurConnecte.getIdUtilisateur(), rdi.recupererThemeUtilisateur(utilisateurConnecte.getIdUtilisateur()).getIdTheme(), titre, content);
                    JOptionPane.showMessageDialog(null, "Article publié", "Publication", JOptionPane.INFORMATION_MESSAGE);
                    naviguerVers("Mes articles");
                }else{
                    JOptionPane.showMessageDialog(null, "Vous n'avez pas de thème attribué impossible de créer un article", "Theme inexistant", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Votre article n'a pas de titre", "Aucun titre", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Votre article dépasse la longueur de charactères autorisé", "Texte trop long", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void supprimerArticle(int idArticle)
    {
        List<Commentaire> commentairesASupprimer = rdi.recupererCommentaires(idArticle);
        if(commentairesASupprimer == null)
            commentairesASupprimer = new ArrayList<>();
        int choix = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment supprimer " + rdi.recupererArticle(idArticle).getIntitule() + " et ses " + commentairesASupprimer.size() + " commentaires", "Supprimer article", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if(choix == JOptionPane.YES_OPTION)
        {
            for(Commentaire c : commentairesASupprimer)
            {
                ed.supprimerCommentaire(c.getIdCommentaire(), c.getIdArticle(), c.getIdAuteur());
            }
            ed.supprimerArticle(idArticle, rdi.recupererUtilisateur(rdi.recupererArticle(idArticle).getIdAuteur()).getIdUtilisateur());
            aav.supprimerArticle(idArticle);
        }
    }
    
    public void envoyerMessage(String objet, String content, List<Utilisateur> destinataires)
    {
        if(objet.length() <= 50)
        {
            if(content.length() <= 2000)
            {
                if(!destinataires.isEmpty())
                {
                    ed.ajouterMessage(utilisateurConnecte.getIdUtilisateur(), objet, content);
                    for(Utilisateur u : destinataires)
                    {
                        ed.ajouterCorrespondance(rdi.recupererMessageEnvoye(utilisateurConnecte.getIdUtilisateur()).getIdMessage(), u.getIdUtilisateur());
                    }
                    naviguerVers("ACCUEIL");
                }else{
                    JOptionPane.showMessageDialog(null, "Votre message n'a pas de destinataire", "Aucun destinataire", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Votre contenu est trop long : " + content.length() + "(max 2000 caractères)", "Contenu trop long", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Votre objet est trop long " + objet.length() + " (max 50 caractères)", "Objet trop long", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void renvoiMessage(Message m)
    {
        NouveauMessageVue nmv = new NouveauMessageVue(new ArrayList<>(rdi.recupererUtilisateurs().values()), this);
        bcv.changeMainContent(nmv);
        nmv.renvoiMessage(m);
    }
    
    public void modifierArticle(int idArticle, String titre, String content)
    {
        if(content.length() <= 4000)
        {
            if (!titre.isEmpty()) {
                if (rdi.recupererThemeUtilisateur(utilisateurConnecte.getIdUtilisateur()) != null) {
                    ed.modifierArticle(idArticle, titre, content);
                    JOptionPane.showMessageDialog(null, "Article modifié", "Modification", JOptionPane.INFORMATION_MESSAGE);
                    naviguerVers("Mes articles");
                }else{
                    JOptionPane.showMessageDialog(null, "Vous n'avez pas de thème attribué impossible de créer un article", "Theme inexistant", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "Votre article n'a pas de titre", "Aucun titre", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Votre article dépasse la longueur de charactères autorisé", "Texte trop long", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void consulterMessage(int idMessage)
    {
        Message m = rdi.recupererMessage(idMessage);
        bcv.changeMainContent(new MessageVue(m, rdi.recupererUtilisateur(m.getIdAuteur()), this));
    }
    
    public void allerVersModificationArticle(int idArticle)
    {
        Article a = rdi.recupererArticle(idArticle);
        ev.editerArticle(idArticle, a.getIntitule(), a.getContenu());
        bcv.changeMainContent(ev);
    }
    
    public void consulterArticleUtilisateur(int idUtilisateur)
    {
        if(idUtilisateur != utilisateurConnecte.getIdUtilisateur())
        {
            Utilisateur u = rdi.recupererUtilisateur(idUtilisateur);
            aav.setTitle("Articles de " + u.getNom());
            aav.setArticleUtilisateur(recupererArticleUtilisateur(u.getIdUtilisateur()), rdi.recupererThemeUtilisateur(u.getIdUtilisateur()), u);
            bcv.changeMainContent(aav);
        }else
            naviguerVers("Mes articles");
    }
    
    public void consulterArticle(int idArticle)
    {
        bcv.changeMainContent(new ArticleCommentairesVue(rdi.recupererArticle(idArticle), 
                rdi.recupererUtilisateur(rdi.recupererArticle(idArticle).getIdAuteur()), 
                rdi.recupererThemeUtilisateur(rdi.recupererUtilisateur(rdi.recupererArticle(idArticle).getIdAuteur()).getIdUtilisateur()), 
                rdi.recupererCommentaires(idArticle), rdi.recupererUtilisateurs(), this));
    }
    
    public void posterCommentaire(int idArticle, String intitule, String content)
    {
        if(!intitule.isEmpty())
        {
            if(!content.isEmpty())
            {
                ed.ajouterCommenatire(idArticle, utilisateurConnecte.getIdUtilisateur(), intitule, content);
                consulterArticle(idArticle);
            }else{
                JOptionPane.showMessageDialog(null, "Votre commentaire ne possede pas de contenu !", "Contenu manquant", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "Votre commentaire ne possede pas de titre !", "Titre manquant", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void modifierCommentaire(int idCommentaire, int idArticle, String contenu)
    {
        if(!contenu.isEmpty())
        {
            ed.modifierCommentaire(idCommentaire, contenu);
            consulterArticle(idArticle);
        }else{
            JOptionPane.showMessageDialog(null, "Votre commentaire ne possede pas de contenu !", "Contenu manquant", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public List<Article> recupererArticleUtilisateur(int idUtilisateur)
    {
        List<Article> articles = new ArrayList<>();
        Utilisateur u = rdi.recupererUtilisateur(idUtilisateur);
        if(rdi.recupererArticles() != null && u != null)
        {
            for(Article a : rdi.recupererArticles())
            {
                if(a.getIdAuteur() == u.getIdUtilisateur())
                {
                    articles.add(a);
                }
            }
        }
        return articles;
    }
    
    public void supprimerUtilisateur(int idUtilisateur)
    {
        if(idUtilisateur != utilisateurConnecte.getIdUtilisateur())
        {
            int choix = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment supprimer " + rdi.recupererUtilisateur(idUtilisateur).getNom(), "Supprimer Utilisateur", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if(choix == JOptionPane.YES_OPTION)
            {
                ed.depossserderTheme(rdi.recupererThemeUtilisateur(idUtilisateur).getIdTheme());
                ed.supprimerUtilisateur(idUtilisateur);
                naviguerVers("Liste des utilisateurs");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Vous ne pouvez pas vous supprimer", "Suppression impossible", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void supprimerCorrespondance(int idMessage)
    {
        ed.supprimerCorrespondance(idMessage, utilisateurConnecte.getIdUtilisateur());
        naviguerVers("ACCUEIL");
    }
    
    public void supprimerTheme(int idTheme){
        List<Article> articlesASupprimer = recupererArticleUtilisateur(rdi.recupererTheme(idTheme).getIdProp());
        List<Commentaire> commentairesASupprimer = new ArrayList<>();
        if(articlesASupprimer != null)
        {
            for(Article a : articlesASupprimer)
            {
                if(rdi.recupererCommentaires(a.getIdArticle()) != null)
                {
                    commentairesASupprimer.addAll(rdi.recupererCommentaires(a.getIdArticle()));
                }
            }
        }
        int choix = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment supprimer le thème " + rdi.recupererTheme(idTheme).getIntitule() + " ("+ articlesASupprimer.size() + " article(s) et " + commentairesASupprimer.size() + " commentaire(s))", "Supprimer Theme", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if(choix == JOptionPane.YES_OPTION){
            for(Commentaire c : commentairesASupprimer)
            {
                ed.supprimerCommentaire(c.getIdCommentaire(), c.getIdArticle(), c.getIdAuteur());
            }
            for(Article a : articlesASupprimer)
            {
                ed.supprimerArticle(a.getIdArticle(), a.getIdAuteur());
            }
            ed.supprimerTheme(idTheme);
            naviguerVers("Liste des thèmes");
        }
    }
    
    public void supprimerCommentaire(int idCommentaire, int idArticle, int idUtilisateur)
    {
        int choix = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment supprimer ce commentaire ?", "Supprimer Commentaire", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(choix == JOptionPane.YES_OPTION)
        {
            ed.supprimerCommentaire(idCommentaire, idArticle, idUtilisateur);
            consulterArticle(idArticle);
        }
    }
    
    public void attribuerTheme(int idTheme)
    {
        List<Utilisateur> utilisateursSansTheme = new ArrayList<>();
        for(Utilisateur ut : rdi.recupererUtilisateurs().values())
        {
            if(rdi.recupererThemeUtilisateur(ut.getIdUtilisateur()) == null && ut.getTypeProfil() == Droits.ETUDIANT)
            {
               utilisateursSansTheme.add(ut);
            }
        }
        if(!utilisateursSansTheme.isEmpty())
        {
            Utilisateur[] values = new Utilisateur[utilisateursSansTheme.size()];
            for(int i = 0; i < utilisateursSansTheme.size(); i++)
            {
                values[i] = utilisateursSansTheme.get(i);
            }
            Object u = JOptionPane.showInputDialog(null, "Choisissez un etudiant pour le thème " + rdi.recupererTheme(idTheme).getIntitule(), "Attribuer un thème", JOptionPane.QUESTION_MESSAGE, null, values, values[0]);
            Utilisateur choix = (Utilisateur) u;
            ed.attribuerTheme(idTheme, choix.getIdUtilisateur());
            naviguerVers("Liste des thèmes");
        }else{
             JOptionPane.showMessageDialog(null, "Tous les étudiants ont un thème", "Attribution impossible", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void modifierDescriptionTheme(int idTheme, String description)
    {
        if(description.isEmpty())
            description = null;
        ed.setDescriptionTheme(idTheme, description);
    }
    
    public void allerVersProfil(int idUtilisateur)
    {
        bcv.changeMainContent(new ProfilVue(rdi.recupererNote(idUtilisateur), rdi.recupererUtilisateur(idUtilisateur), rdi.recupererThemeUtilisateur(idUtilisateur), this));
    }
    
    public void miseAjourNoteArticle(int idArticle, float note)
    {
        if(note <= 5.0f){
            ed.updateNote(idArticle, note);
            consulterArticle(idArticle);
        }else
            JOptionPane.showMessageDialog(null, "La note " + note + " est trop élevée (max 5)", "Note trop élevée", JOptionPane.ERROR_MESSAGE);
    }
    
    public Utilisateur getUtilisateurConnecte()
    {
        return utilisateurConnecte;
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
    
    public void setAjoutUtilisateurVue(AjoutUtilisateurVue auv){
        this.auv = auv;
    }
    
    public void setAjoutThemeVue(AjoutThemeVue atv)
    {
        this.atv = atv;
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
