
package TestMain;

import Controllers.EquipeController;
import Controllers.JoueurController;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import Controllers.UserController;
import Models.Equipe;
import Models.Joueur;
import Models.Users;
=======


>>>>>>> 4d091680a04fa45db9be91de5489ec12b931b3f1
import Models.Equipe;
import Models.Joueur;


=======
=======
>>>>>>> d5325f0fc4f664c65adf893dc8c9fcb41f70458b
import Controllers.MatchController;
import Models.Match;
import Models.Participation;
import Models.Equipe;
import Models.Joueur;
<<<<<<< HEAD
>>>>>>> d5325f0fc4f664c65adf893dc8c9fcb41f70458b
=======
>>>>>>> d5325f0fc4f664c65adf893dc8c9fcb41f70458b
import tools.MaConnexion;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       MaConnexion m = MaConnexion.getInstance();
       
       
<<<<<<< HEAD
<<<<<<< HEAD
       /* //CRUD équipe
=======
       /* //CRUD équipe 
>>>>>>> d5325f0fc4f664c65adf893dc8c9fcb41f70458b
=======
       /* //CRUD équipe 
>>>>>>> d5325f0fc4f664c65adf893dc8c9fcb41f70458b
       
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
      
<<<<<<< HEAD
<<<<<<< HEAD
      
      */
<<<<<<< HEAD

         
       
       Users u=new Users(1,12345,29630628,"mahdi", "aroua","mahdiaroua@gmailcom","1234","1234","admin");
       UserController Uc = new UserController();
        Uc.ajouterUser(u);
        System.out.println(Uc.afficherUser());
        
        Uc.updateUser(u);
      
        System.out.println(Uc.supprimerUser());
        System.out.println(Uc.afficherUser());
      

      
=======
//       Produit p=new Produit(1,"gfgf",50,60, "logo1.png","ess",1);
//       ProduitController Ec = new ProduitController();
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
>>>>>>> 4d091680a04fa45db9be91de5489ec12b931b3f1
=======
=======
>>>>>>> d5325f0fc4f664c65adf893dc8c9fcb41f70458b
      idM ; 
    public String localisation,arbitrePrincipale,tour  ;
       m.ajouterMatch(m);
        System.out.println(m.afficherMatch());
        m.updateMatch(j);
         System.out.println(m.afficherJoueur());
         
         System.out.println(m.supprimerJoueur());
         System.out.println(m.afficherJoueur());
      
       */
      
      /*crud match*/
     Match n=new Match(1,"stade rades","monji mannai","devision2");
     MatchController mc= new MatchController();
     mc.ajouterMatch(n);
     System.out.println(mc.afficherMatch());
      //  mc.updateMatch(n);
        // System.out.println(mc.afficherMatch());
         
       /*<  System.out.println(mc.DeleteMatch());
         System.out.println(mc.afficherMatch());
     */
<<<<<<< HEAD
>>>>>>> d5325f0fc4f664c65adf893dc8c9fcb41f70458b
=======
>>>>>>> d5325f0fc4f664c65adf893dc8c9fcb41f70458b
    }
      
     
     
}
