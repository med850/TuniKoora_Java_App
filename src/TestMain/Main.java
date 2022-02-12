
package TestMain;

import Controllers.EquipeController;
import Models.Equipe;
import tools.MaConnexion;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       MaConnexion m = MaConnexion.getInstance();
      Equipe e=new Equipe(1,"esperence",2, "logo2.png");
       EquipeController Ec = new EquipeController();
        Ec.ajouterEquipe(e);
        System.out.println(Ec.afficherEquipe());
        
      //  Ec.updatePersonne(e);
      
      System.out.println(Ec.supprimerEquipe());
      //System.out.println(Ec.afficherEquipe());
    }
    
}
