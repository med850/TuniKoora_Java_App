/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;



import Models.Livraison;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.MaConnexion;
/**
 *
 * @author jerby
 */
public class LivraisonController {
    
    
    Connection mc;
    PreparedStatement ste;
    
    
      public LivraisonController() {
        mc=MaConnexion.getInstance().getCnx();
    }
    
    
    public void ajouterLivraison(Livraison l){
        String sql ="insert into livraison(ref,localisation,etat,user_id) Values(?,?,?,?)";
        try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, l.getRef());
            ste.setString(2, l.getLocalisation());
            ste.setString(3, l.getEtat());
            ste.setInt(4, l.getUser_id());
            ste.executeUpdate();
            System.out.println("Livraison Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    
    public ObservableList<Livraison> afficherLivraison(){
        ObservableList<Livraison> livraisons = FXCollections.observableArrayList();
        String sql="select * from livraison";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Livraison l = new Livraison();
                l.setId(rs.getInt("id"));
                l.setRef(rs.getString("ref"));
                l.setLocalisation(rs.getString("localisation"));
                l.setEtat(rs.getString("etat"));
                l.setUser_id(rs.getInt("user_id"));
                livraisons.add(l);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return livraisons;
    }    
    
    
    
    public List<Livraison> updateLivraison(Livraison l ){
        List<Livraison> Livraisons = new ArrayList<>();
        String sql="UPDATE livraison SET ref = ?, localisation = ?, etat = ?, user_id = ?  WHERE id = 2";
         try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, l.getRef());
            ste.setString(2, l.getLocalisation());
            ste.setString(4, l.getEtat());
            ste.setInt(5, l.getUser_id());
            ste.executeUpdate();
            System.out.println("Livraison Modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return Livraisons;
        
        
      }
        
        
        
        
     public List<Livraison> supprimerLivraison(){
        List<Livraison> livraisons = new ArrayList<>();
        String sql="DELETE FROM livraison WHERE id=2";
        try {
            ste=mc.prepareStatement(sql);
            ste.executeUpdate();
            System.out.println("Livraison supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return livraisons;
    }  
    
     public boolean checkLivraison(Livraison l) throws SQLException {
        int count = 0;
        String sql=("SELECT Count(*) from Livraison WHERE ref='"
                    + l.getRef() + "'");
        ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
        if (rs.next())
            count = rs.getInt(1);
        if (count == 0)
            return false;
        else
            return true;
}
}
