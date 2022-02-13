
package TestMain;


import Controllers.ArticleController;
import Controllers.EquipeController;
import Controllers.JoueurController;
import Controllers.ProduitController;
import Controllers.TicketController;


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

import Models.Equipe;
import Models.Joueur;
import Models.Produit;
import Models.Ticket;


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

     
//        Ec.ajouterProduit(p);
//        System.out.println(Ec.afficherProduit());
//        
//        Ec.updateProduit(p);
//     
//          System.out.println(Ec.supprimerProduit());
//          System.out.println(Ec.afficherProduit());
      

//
//         Ticket t=new Ticket(1,"ess","ess",60,1,1);
//         TicketController Ec = new TicketController();
//        Ec.ajouterTicket(t);
//        System.out.println(Ec.afficherTicket());
//        
//     Ec.updateTicket(t);
//     System.out.println(Ec.afficherTicket());
//     
//          System.out.println(Ec.supprimerTicket());
//          System.out.println(Ec.afficherTicket());
//       
//       Users u=new Users(1,12345,29630628,"mahdi", "aroua","mahdiaroua@gmailcom","1234","1234","admin");
//       UserController Uc = new UserController();
//        Uc.ajouterUser(u);
//        System.out.println(Uc.afficherUser());
//        
//        Uc.updateUser(u);
//      
//        System.out.println(Uc.supprimerUser());
//        System.out.println(Uc.afficherUser());
      
//          Review r = new Review(1,"wass",7,1);
//          ReviewController Rc = new ReviewController();
//          //Rc.ajouterReview(r);
//          //Rc.updateReview(r);
//          Rc.supprimerReview();
//          System.out.println(Rc.afficherReview());
          
          Article a= new Article(1,"wass","wasss",1);
          ArticleController Ac = new ArticleController();
//          Ac.ajouterArticle(a);
//            Ac.updateArticle(a);
//          Ac.supprimerArticle();
            System.out.println(Ac.afficherArticle());
          
    
    }
}
