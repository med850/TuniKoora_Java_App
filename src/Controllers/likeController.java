/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Models.like;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import tools.MaConnexion;

/**
 *
 * @author wassim
 */
public class likeController {
     Connection mc;
    PreparedStatement ste;
    
    
      public likeController() {
        mc=MaConnexion.getInstance().getCnx();
    }
    
    
    public void ajouterLike(like l){
        String sql ="insert into likes(likes,article_id,user_id) Values(?,?,?)";
        try {
            ste=mc.prepareStatement(sql);
            ste.setInt(1,l.getLike());
            ste.setInt(2, l.getUser_id());
            ste.setInt(3, l.getArticle_id());
            ste.executeUpdate();
            System.out.println("like Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
   }
    
    
    
}
