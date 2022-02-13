
package TestMain;


import Controllers.ArticleController;
import Controllers.EquipeController;
import Controllers.JoueurController;
import Models.Article;

import Controllers.EquipeController;
import Controllers.JoueurController;
import Controllers.ReviewController;
import Controllers.UserController;
import Models.Equipe;
import Models.Joueur;
import Models.Users;
import Models.Equipe;
import Models.Joueur;
import Models.Review;
import tools.MaConnexion;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       MaConnexion m = MaConnexion.getInstance();
       
       
       /* //CRUD équipe
       
       Equipe e=new Equipe(1,"etoile",1, "logo1.png");
       EquipeController Ec = new EquipeController();
        Ec.ajouterEquipe(e);
        System.out.println(Ec.afficherEquipe());
        
        Ec.updateEquipe(e);
      
        System.out.println(Ec.supprimerEquipe());
        System.out.println(Ec.afficherEquipe());
      
      */
       
       
      /* //CRUD joueur
      
       Joueur j=new Joueur(1,"user1","test","img1", "milieu",98745632,2);
        JoueurController Jc = new JoueurController();
        Jc.ajouterJoueur(j);
        System.out.println(Jc.afficherJoueur());
        Jc.updateJoueur(j);
         System.out.println(Jc.afficherJoueur());
         
         System.out.println(Jc.supprimerJoueur());
         System.out.println(Jc.afficherJoueur());
      
      
      */
        Review r=new Review(1,"aaaxxaaa",7, 1);
           ReviewController Rc = new ReviewController();
//        Rc.ajouterReview(r);
         System.out.println(Rc.afficherReview());
//        
          Rc.updateReview(r);
//      
        System.out.println(Rc.supprimerReview());
//        System.out.println(Ec.afficherEquipe());
     
    }
}
