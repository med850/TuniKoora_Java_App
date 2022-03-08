/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.dislike;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import tools.MaConnexion;

/**
 *
 * @author wassim
 */
public class dislikeController {
     Connection mc;
    PreparedStatement ste;
    
    
      public dislikeController() {
        mc=MaConnexion.getInstance().getCnx();
    }
    
    
    public void ajouterLike(dislike d){
        String sql ="insert into dislike(dislike,article_id,user_id) Values(?,?,?)";
        try {
            ste=mc.prepareStatement(sql);
            ste.setInt(1,d.getDislike());
            ste.setInt(2, d.getUser_id());
            ste.setInt(3, d.getArticle_id());
            ste.executeUpdate();
            System.out.println("dislike Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
   }
}
