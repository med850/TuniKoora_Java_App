
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
//




import Models.Equipe;
import Models.Joueur;

import Models.Review;

import Models.Equipe;
import Models.Joueur;
import Models.Produit;
import Models.Ticket;



import Controllers.MatchController;
import Controllers.ParticipationController;

import Models.Match;
import Models.Participation;
import Models.Equipe;
import Models.Joueur;
import java.io.IOException;


import tools.MaConnexion;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
       MaConnexion m = MaConnexion.getInstance();
       
       
//

 /*     ******************************  CRUD Equipe  ***************************************        */

        
   
//       Equipe e=new Equipe(1,"rajich",1, "logo2.png");
//       EquipeController Ec = new EquipeController();
//        Ec.ajouterEquipe(e);
//        System.out.println(Ec.afficherEquipe());
        
//        Ec.updateEquipe(e);
//      
//        System.out.println(Ec.supprimerEquipe());
//        System.out.println(Ec.afficherEquipe());
             
    
    //
 /*     ***********************************************************************************        */   
       
 //
      /*******************************  CRUD Joueur  ***************************************        */
      
// /      Joueur j=new Joueur(1,"user1","test","img1", "milieu",98745632,2);
//        JoueurController Jc = new JoueurController();
//        Jc.ajouterJoueur(j);
//        System.out.println(Jc.afficherJoueur());

//        Jc.updateJoueur(j);
//         System.out.println(Jc.afficherJoueur());
//         
//         System.out.println(Jc.supprimerJoueur());
//         System.out.println(Jc.afficherJoueur());
      
//
   /* ****************************************************************************************        */     
//
     
     /************************************  CRUD Produit  ***************************************        */

///
////        Ec.ajouterProduit(p);
//          System.out.println(Ec.afficherProduit());
//        
//          Ec.updateProduit(p);
//          System.out.println(Ec.afficherProduit());    
            
///
//          System.out.println(Ec.supprimerProduit());
//          System.out.println(Ec.afficherProduit());
      
 /*******************************************************************************************        */  
//

  /************************************  CRUD Ticket  ***************************************        */

//         Ticket t=new Ticket(1,"ess","ess",60,1,1);
//         TicketController Ec = new TicketController();
//        Ec.ajouterTicket(t);
//        System.out.println(Ec.afficherTicket());
//        
//         Ec.updateTicket(t);
//         System.out.println(Ec.afficherTicket());
//     
//          System.out.println(Ec.supprimerTicket());
//          System.out.println(Ec.afficherTicket());


 /*******************************************************************************************        */  



    /**********************************  CRUD Users  ***************************************        */
//       
//       Users u=new Users(1,1558,29630628,"mahdi", "aroua","mahdiaroua@gmailcom","1234","1234","user");
//       UserController Uc = new UserController();
//        Uc.ajouterUser(u);
//        System.out.println(Uc.afficherUser());
        
        //Uc.updateUser(u);
//      
///        System.out.println(Uc.supprimerUser());
//        System.out.println(Uc.afficherUser());
      

 /*******************************************************************************************        */  

 /*************************************  CRUD Review  ***************************************        */


//          Review r = new Review(1,"wass",7,1);
//          ReviewController Rc = new ReviewController();
//          //Rc.ajouterReview(r);
//          //Rc.updateReview(r);
//          Rc.supprimerReview();
//          System.out.println(Rc.afficherReview());
          
 /*******************************************************************************************        */  

 /*************************************  CRUD Article  ***************************************        */
 
 
//          Article a= new Article(1,"wass","wasss",1,"11-11-2000");
//          ArticleController Ac = new ArticleController();
//          Ac.ajouterArticle(a);
////            Ac.updateArticle(a);
////          Ac.supprimerArticle();
//            System.out.println(Ac.afficherArticle());
          
 /************************************************************************************************* */  
 
 
 
  /*************************************  CRUD Produit  *************************************** **** */


//       Produit p=new Produit(1,"gfgf",50,60, "logo1.png","ess",1);
//       ProduitController Ec = new ProduitController();
//        Ec.ajouterProduit(p);
//        System.out.println(Ec.afficherProduit());
//        
//        Ec.updateProduit(p);
//     
//          System.out.println(Ec.supprimerProduit());
//          System.out.println(Ec.afficherProduit());
      
 /************************************************************************************************* */  
//

  /*************************************  CRUD Ticket  *************************************** **** */

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
//
/************************************************************************************************* */  
//
//   
 /*************************************  CRUD Match  *************************************** **** */
     
//     Match n=new Match(2,"hhh","gcgc","vfcgc");
//     MatchController mc= new MatchController();
//     // mc.ajouterMatch(n);
//        //System.out.println(mc.afficherMatch());
//         // mc.updateMatch(n);
//        // System.out.println(mc.afficherMatch());
////         
//         System.out.println(mc.supprimerMatch());
//         System.out.println(mc.afficherMatch());
//     
/************************************************************************************************* */  

//
 /*************************************  CRUD Participation  *************************************** **** */

//
 
//    Participation n=new Participation(1,2,3,"12-11-2022");
//         ParticipationController pc= new ParticipationController();
//         //  pc.ajouterParticipation(n);
////           System.out.println(pc.afficherParticipation());
////             pc.updateParticipation(n);
////        // System.out.println(mc.afficherMatch());
//////         
//         System.out.println(pc.supprimerParticipation());
//         System.out.println(pc.afficherParticipation());
/************************************************************************************************* */  
      //  new dataProviderController().getData("France");
      
      
      
//      dataProviderService dp = new dataProviderService();
//      dp.getData();
  }
      }

