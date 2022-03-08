
package Controllers;

import Models.Review;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tools.MaConnexion;


public class ReviewController {
   
    Connection mc;
    PreparedStatement ste;
    
    
      public ReviewController() {
        mc=MaConnexion.getInstance().getCnx();
    }
    
    
    public void ajouterReview(Review r){
        String sql ="insert into review(commentaire,article_id,user_id) Values(?,?,?)";
        try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, r.getCommentaire());
            ste.setInt(2, r.getIdArticle());
            ste.setInt(3, r.getIdUser());
            ste.executeUpdate();
            System.out.println("Review Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    public List<Review> afficherReview(){
        List<Review> review = new ArrayList<>();
        String sql="SELECT * FROM review ";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            
            while(rs.next()){
                Review r = new Review();
                r.setIdReview(rs.getInt("id"));
                r.setCommentaire(rs.getString("description"));
                r.setIdArticle(rs.getInt("article_id"));
                r.setIdUser(rs.getInt("user_id"));
                review.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return review;
    }    
    

     public List<Review> updateReview(Review r ){
        List<Review> review = new ArrayList<>();
        String sql="UPDATE review SET commentaire = ?, article_id = ?, user_id = ? WHERE id = 1";
         try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, r.getCommentaire());
            ste.setInt(2, r.getIdArticle());
            ste.setInt(3, r.getIdUser());
            ste.executeUpdate();
            System.out.println("Review Modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return review;
        
        
      }
    
     public List<Review> supprimerReview(){
        List<Review> review = new ArrayList<>();
        String sql="DELETE FROM review WHERE id=2";
        try {
            ste=mc.prepareStatement(sql);
            ste.executeUpdate();
            System.out.println("Review supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return review;
    }  
        
        
    
    
    
    
    
}
