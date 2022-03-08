/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Livreur;
import java.sql.Connection;
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
public class LivreurController {
    
    Connection mc;
    PreparedStatement ste;
    
    
      public LivreurController() {
        mc=MaConnexion.getInstance().getCnx();
    }
    
    
    public void ajouterLivreur(Livreur l){
        String sql ="insert into livreur(nom,prenom,tel,livraison_id) Values(?,?,?,?)";
        try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, l.getNom());
            ste.setString(2, l.getPrenom());
            ste.setInt(3, l.getTel());
            ste.setInt(4, l.getLivraisonID());
            ste.executeUpdate();
            System.out.println("Livreur Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    
        public ObservableList<Livreur> afficherLivreur(){
        ObservableList<Livreur> livreurs = FXCollections.observableArrayList();
        String sql="select * from livreur";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Livreur l = new Livreur();
                l.setId(rs.getInt("id"));
                l.setNom(rs.getString("nom"));
                l.setPrenom(rs.getString("prenom"));
                l.setTel(rs.getInt("tel"));
                l.setLivraisonID(rs.getInt("livraison_id"));
                livreurs.add(l);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return livreurs;
    }    
    
    
    
    
     public List<Livreur> updateLivreur(Livreur l ){
        List<Livreur> livreurs = new ArrayList<>();
        String sql="UPDATE livreur SET nom = ?, prenom = ?, tel = ?, livraison_id = ? WHERE id = 15";
         try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, l.getNom());
            ste.setString(2, l.getPrenom());
            ste.setInt(3, l.getTel());
            ste.setInt(4, l.getLivraisonID());
            
            ste.executeUpdate();
            System.out.println("Livreur Modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return livreurs;
     } 
        
    public List<Livreur> supprimerLivreur(){
        List<Livreur> livreurs = new ArrayList<>();
        String sql="DELETE FROM livreur WHERE id=15";
        try {
            ste=mc.prepareStatement(sql);
            ste.executeUpdate();
            System.out.println("Livreur supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return livreurs;
    }  
    
    
    
    
}
