
package Controllers;


import Models.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tools.MaConnexion;

public class ProduitController {
 
    
    
      Connection mc;
    PreparedStatement ste;
    
    
      public ProduitController() {
        mc=MaConnexion.getInstance().getCnx();
    }
    
    //Ajouter in produit
    public void ajouterProduit(Produit p){
        String sql ="insert into produit(nom,prix,qte,description,image,user_id) Values(?,?,?,?,?,?)";
        try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, p.getNom());
            ste.setInt(2, p.getPrix());
            ste.setInt(3, p.getQte());
            ste.setString(4, p.getDescription());
            ste.setString(5, p.getImage());
            ste.setInt(6, p.getUser_id());
            ste.executeUpdate();
            System.out.println("produit Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    
    //Afficher liste des produit
    
        public List<Produit> afficherProduit(){
        List<Produit> produit = new ArrayList<>();
        String sql="select * from produit";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Produit p = new Produit();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPrix(rs.getInt("prix"));
                p.setQte(rs.getInt("qte"));
                p.setDescription(rs.getString("description"));
                p.setImage(rs.getString("image"));
                p.setUser_id(rs.getInt("user_id"));
                produit.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return produit;
    }    
    
    
    //Modifier un Produit
     public List<Produit> updateProduit(Produit p ){
        List<Produit> produit = new ArrayList<>();
        String sql="UPDATE produit SET nom = ?, prix = ?, qte = ?, description = ?, image = ?, user_id = ? WHERE id = 2";
         try {
            ste=mc.prepareStatement(sql);
             ste=mc.prepareStatement(sql);
            ste.setString(1, p.getNom());
            ste.setInt(2, p.getPrix());
            ste.setInt(3, p.getQte());
            ste.setString(4, p.getDescription());
            ste.setString(5, p.getImage());
            ste.setInt(6, p.getUser_id());
            ste.executeUpdate();
            System.out.println("Produit Modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return produit;
        
        
      }
        
        
        
        //Supprimer un Produit
     public List<Produit> supprimerProduit(){
        List<Produit> produit = new ArrayList<>();
        String sql="DELETE FROM produit WHERE id=1";
        try {
            ste=mc.prepareStatement(sql);
            ste.executeUpdate();
            System.out.println("Produit Supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return produit;
    }  
        
    
    
    
    
}
