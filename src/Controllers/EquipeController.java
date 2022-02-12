
package Controllers;

import Models.Equipe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tools.MaConnexion;


public class EquipeController {
   
    Connection mc;
    PreparedStatement ste;
    
    
      public EquipeController() {
        mc=MaConnexion.getInstance().getCnx();
    }
    
    
    public void ajouterEquipe(Equipe e){
        String sql ="insert into equipe(nom,classement,logo) Values(?,?,?)";
        try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, e.getNom());
            ste.setInt(2, e.getClassement());
            ste.setString(3, e.getLogo());
            ste.executeUpdate();
            System.out.println("Equipe Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    
        public List<Equipe> afficherEquipe(){
        List<Equipe> equipes = new ArrayList<>();
        String sql="select * from equipe";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Equipe e = new Equipe();
                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("nom"));
                e.setClassement(rs.getInt("classement"));
                e.setLogo(rs.getString("logo"));
                equipes.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return equipes;
    }    
    
    
    
    
     public List<Equipe> updateEquipe(Equipe e ){
        List<Equipe> equipes = new ArrayList<>();
        String sql="UPDATE equipe SET nom = ?, classement = ?, logo = ? WHERE id = 15";
         try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, e.getNom());
            ste.setInt(2, e.getClassement());
            ste.setString(3, e.getLogo());
            ste.executeUpdate();
            System.out.println("Equipe Modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return equipes;
        
        
      }
        
        
        
        
     public List<Equipe> supprimerEquipe(){
        List<Equipe> equipes = new ArrayList<>();
        String sql="DELETE FROM equipe WHERE id=15";
        try {
            ste=mc.prepareStatement(sql);
            ste.executeUpdate();
            System.out.println("Equipe supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return equipes;
    }  
        
        
    
    
    
    
    
}
