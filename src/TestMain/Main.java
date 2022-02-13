
package TestMain;

import Controllers.EquipeController;
import Controllers.JoueurController;
<<<<<<< HEAD
import Controllers.UserController;
import Models.Equipe;
import Models.Joueur;
import Models.Users;
=======
import Models.Equipe;
import Models.Joueur;
>>>>>>> fce15cd73be365e3533c1fe6e253cf06dce538aa
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
<<<<<<< HEAD
         
       
       Users u=new Users(1,12345,29630628,"mahdi", "aroua","mahdiaroua@gmailcom","1234","1234","admin");
       UserController Uc = new UserController();
        Uc.ajouterUser(u);
        System.out.println(Uc.afficherUser());
        
        Uc.updateUser(u);
      
        System.out.println(Uc.supprimerUser());
        System.out.println(Uc.afficherUser());
      
=======
>>>>>>> fce15cd73be365e3533c1fe6e253cf06dce538aa
      
      
    }
      
     
     
}
