
package Controllers;

import Models.Equipe;
import Models.Match;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tools.MaConnexion;


public class MatchController {
   
  
    Connection mc;
    PreparedStatement ste;
    
    
      public MatchController() {
        mc=MaConnexion.getInstance().getCnx();
    }
    
    
    public void ajouterMatch(Match m){
        String sql ="INSERT INTO `match` (`localisation`, `arbitrePrincipale`, `tour`) VALUES (?,?,?)";
        try {
            ste=mc.prepareStatement(sql);
   
            ste.setString(1, m.getLocalisation());
            ste.setString(2, m.getArbitrePrincipale());
            ste.setString(3, m.getTour());
            ste.executeUpdate();
            System.out.println("match Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    
        public List<Match> afficherMatch(){
        List<Match> matches = new ArrayList<>();
        String sql="SELECT * FROM `match`";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Match m = new Match();
                m.setIdM(rs.getInt("id"));
                m.setLocalisation(rs.getString("localisation"));
                m.setArbitrePrincipale(rs.getString("arbitrePrincipale"));
                m.setTour(rs.getString("tour"));
                matches.add(m);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return matches;
    }    
    
    
    
    
     public List<Match> updateMatch(Match m ){
        List<Match> matches = new ArrayList<>();
        String sql="UPDATE `match` SET `localisation` = ?, `arbitrePrincipale` = ?, `tour` = ?  WHERE `match`.`id` = 1";
         try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, m.getLocalisation());
            ste.setString(2, m.getArbitrePrincipale());
            ste.setString(3, m.getTour());
            ste.executeUpdate();
            System.out.println("match Modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return matches;
        
        
      }
        
        
        
        
     public List<Match> supprimerMatch(){
        List<Match> matches = new ArrayList<>();
        String sql="DELETE FROM `match` WHERE `match`.`id` = 1";
        try {
            ste=mc.prepareStatement(sql);
            ste.executeUpdate();
            System.out.println("match supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return matches;
    }  
        
        
  //
    
    
    
    
}
