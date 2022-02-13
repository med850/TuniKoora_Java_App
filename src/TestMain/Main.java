
package TestMain;

import Controllers.ArticleController;
import Controllers.EquipeController;
import Controllers.JoueurController;
import Models.Article;
import Models.Equipe;
import Models.Joueur;
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
      
      //Article a = new Article(1,"aa","affgggfa",1);
      //ArticleController AC= new ArticleController();
      //AC.ajouterArticle(a);
      
      // System.out.println(AC.afficherArticle());
       
       //AC.updateArticle(a);
       //AC.supprimerArticle();
    }
      
     
     
}
