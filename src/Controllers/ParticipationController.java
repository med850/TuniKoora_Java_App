/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Article;
import Models.Participation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tools.MaConnexion;

/**
 *
 * @author mdhah
 */
public class ParticipationController {
    
    
     Connection mc;
    PreparedStatement ste;
    
    
      public ParticipationController() {
        mc=MaConnexion.getInstance().getCnx();
    }
    
    
      public int calculParticipants(Participation p)
      {
        String sql = "SELECT count(*) as total from participation where match_id = ? ";
        try {
            ste=mc.prepareStatement(sql);
            ste.setInt(1, p.getMatch_id());
            ResultSet rs=ste.executeQuery();
             rs.next();
             return rs.getInt("total");
                  } catch(Exception e){
                       System.out.println("test");
                  }
          return -1;
      }
      public void ajouterParticipation(Participation p){
          System.out.println("adding participant");
                      int calc = this.calculParticipants(p);

            String sql="INSERT INTO participation(match_id,equipe_id) values (?,?)";
        
        try {
            ste=mc.prepareStatement(sql);
            System.out.println(p.getMatch_id()+" "+ p.getEquipe_id());
            ste.setObject(1, p.getMatch_id());
            ste.setInt(2, p.getEquipe_id());
            System.out.println(calc);
            if (calc>= 0 && calc<2)
            {     
            ste.executeUpdate();
            System.out.println("participation Ajoutée");}
            else 
                System.out.println("participation non ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
      
      
      
      
   public List<Participation> afficherParticipation(){
        List<Participation> participations = new ArrayList<>();
        String sql="select * from participation INNER JOIN equipe ON participation.equipe_id = equipe.id ";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Participation p = new Participation();
                p.setId(rs.getInt("id"));
                p.setMatch_id(rs.getInt("match_id"));
                p.setEquipe_id(rs.getInt("equipe_id"));
                participations.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return participations;
    }    
       
    
   public List<Participation> updateParticipation(Participation p ){
        List<Participation> participations = new ArrayList<>();
        String sql="UPDATE participation SET match_id = ?, equipe_id = ? WHERE id = 1";
         try {
            ste=mc.prepareStatement(sql);
            ste.setInt(1, p.getMatch_id());
            ste.setInt(2, p.getEquipe_id());
            ste.executeUpdate();
            System.out.println("Article Modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return participations;
        
        
      }
        
        
     public List<Participation> supprimerParticipation(){
        List<Participation> participations = new ArrayList<>();
        String sql="DELETE FROM participation WHERE id=1";
        try {
            ste=mc.prepareStatement(sql);
            ste.executeUpdate();
            System.out.println("Participation supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return participations;
    } 
   
   
   
   
   
   
    
}
