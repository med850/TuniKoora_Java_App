
package Controllers;

import Models.Article;
import Models.Joueur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tools.MaConnexion;


public class ArticleController {
   
    Connection mc;
    PreparedStatement ste;
    
    
      public ArticleController() {
        mc=MaConnexion.getInstance().getCnx();
    }
    
    
    public void ajouterArticle(Article e){
        String sql ="insert into article(titre,description,user_id) Values(?,?,?)";
        try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, e.getTitreArticle());
            ste.setString(2, e.getDescriptionArticle());
            ste.setInt(3, e.getIdUser());
            ste.executeUpdate();
            System.out.println("Article Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    
       
    
    
//    public List<Article> afficherArticle(){
//        List<Article> article = new ArrayList<>();
//        String sql="select * from article where id=7 ";
//        try {
//            ste=mc.prepareStatement(sql);
//            ResultSet rs=ste.executeQuery();
//            while(rs.next()){
//                Article a = new Article();
//                a.setIdArticle(rs.getInt("id"));
//                a.setTitreArticle(rs.getString("titre"));
//                a.setDescriptionArticle(rs.getString("description"));
//                a.setIdUser(rs.getInt("user_id"));
//                article.add(a);
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        
//        return article;
//    }    
    
     public List<Article> updateArticle(Article e ){
        List<Article> article = new ArrayList<>();
        String sql="UPDATE article SET titre = ?, description = ?, user_id   = ? WHERE id = 6";
         try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, e.getTitreArticle());
            ste.setString(2, e.getDescriptionArticle());
            ste.setInt(3, e.getIdUser());
            ste.executeUpdate();
            System.out.println("Article Modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return article;
        
        
      }
        
        
     public List<Article> supprimerArticle(){
        List<Article> article = new ArrayList<>();
        String sql="DELETE FROM article WHERE id=6";
        try {
            ste=mc.prepareStatement(sql);
            ste.executeUpdate();
            System.out.println("Article supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return article;
    } 
}
        
        
    
    
    
    
    

