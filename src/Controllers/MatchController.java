
package Controllers;

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
        String sql ="INSERT INTO `match`(`localisation`, `arbitrePrincipale`, `tour`) VALUES (?,?,?)";
        try {
            ste=mc.prepareStatement(sql);
            
            ste.setString(1, m.getLocalisation());
            ste.setString(2, m.getArbitrePrincipale());
            ste.setString(3, m.getTour());
            ste.executeUpdate();
            System.out.println("Match Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
 
     public List<Match> afficherMatch(){
        List<Match> match = new ArrayList<>();
        String sql="SELECT `localisation`, `arbitrePrincipale`, `tour`";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Match m = new Match();
                m.setIdM(rs.getInt("id"));
                m.setLocalisation(rs.getString("localisation"));
                m.setArbitrePrincipale(rs.getString("aarbitrePrincipale"));
               
                m.setTour(rs.getString("Tour"));
                match.add(m);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return match;
    }    
    
     
   public List<Match> updateMatch(Match m ){
        List<Match> match = new ArrayList<>();
        String sql="UPDATE match SET id = ?, arbitrePrincipale = ?, localisation = ?,tour = ? WHERE id = 1";
         try {
            ste=mc.prepareStatement(sql);
            ste.setInt(1, m.getIdM());
            ste.setString(2, m.getArbitrePrincipale());
            ste.setString(3, m.getLocalisation());
            ste.setString(4, m.getTour());
            ste.executeUpdate();
            System.out.println("match Modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return match;
        
        
      }
    public List <Match> DeleteMatch(){
        List<Match> match = new ArrayList<>();
        String sql="DELETE FROM `match` WHERE 0";
     try {
            ste=mc.prepareStatement(sql);
            System.out.println("match supprimée");

    }
      catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return match;
        
        
      }

}
