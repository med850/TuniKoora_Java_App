
package TestMain;

import Controllers.EquipeController;
import Controllers.JoueurController;
import Controllers.MatchController;
import Models.Match;
import Models.Participation;
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
    }
      
     
     
}
