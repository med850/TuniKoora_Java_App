/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Models.panier1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tools.MaConnexion;



/**
 *
 * @author CHAOUCH KHALIL
 */
public class panier1Controller {
         Connection mc;
    PreparedStatement ste;
    
    
      public panier1Controller() {
        mc=MaConnexion.getInstance().getCnx();
}
public void ajouterpanier(panier1 p){
        String sql ="insert into panierp(user_id,produit_id) Values(?,?)";
        try {
            ste=mc.prepareStatement(sql);
          //  ste.setInt(1, p.getIdPanier());
            ste.setInt(1, p.getUser_id());
            ste.setInt(2, p.getProduit_id());
            

            ste.executeUpdate();
            System.out.println("produit Ajoutée au panier");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }   
}
}
