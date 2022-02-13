package Controllers;

import Models.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tools.MaConnexion;


public class UserController {
   
    Connection mc;
    PreparedStatement ste;
    
    
      public UserController() {
        mc=MaConnexion.getInstance().getCnx();
    }
    
    
    public void ajouterUser(Users u){
        String sql ="insert into users(cin,nom,prenom,tel,email,password,repeatpassword,typeuser) Values(?,?,?,?,?,?,?,?)";
        try {
            ste=mc.prepareStatement(sql);
            ste.setInt(1, u.getCin());
            ste.setString(2, u.getNom());
            ste.setString(3, u.getPrenom());
            ste.setInt(4, u.getTel());
            ste.setString(5, u.getEmail());
            ste.setString(6, u.getPassword());
            ste.setString(7, u.getRepeatpassword());
            ste.setString(8, u.getTypeuser());
            ste.executeUpdate();
            System.out.println("Utilisateur Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    
        public List<Users> afficherUser(){
        List<Users> utilisateur = new ArrayList<>();
        String sql="select * from users";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Users u = new Users();
                 u.setId(rs.getInt("id"));
                 u.setCin(rs.getInt("cin"));
            u.setNom(rs.getString("nom"));
            u.setPrenom(rs.getString("prenom"));
            u.setTel(rs.getInt("tel"));
            u.setEmail(rs.getString("email"));
            u.setPassword(rs.getString("password"));
            u.setRepeatpassword(rs.getString("repeatpassword"));
            u.setTypeuser(rs.getString("typeuser"));
                
            utilisateur.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return utilisateur;
    }    
    
    
    
    
     public List<Users> updateUser(Users u ){
        List<Users> utilisateur = new ArrayList<>();
        String sql="UPDATE `users` SET `id`='[value-1]',`cin`='[value-2]',`nom`='[value-3]',`prenom`='[value-4]',`tel`='[value-5]',`email`='[value-6]',`password`='[value-7]',`repeatPassword`='[value-8]',`typeUser`='[value-9]' WHERE 1";
         try {
            ste=mc.prepareStatement(sql);
            ste.setInt(1, u.getCin());
            ste.setString(2, u.getNom());
            ste.setString(3, u.getPrenom());
            ste.setInt(4, u.getTel());
            ste.setString(5, u.getEmail());
            ste.setString(6, u.getPassword());
            ste.setString(7, u.getRepeatpassword());
            ste.setString(8, u.getTypeuser());
            ste.executeUpdate();
            System.out.println("Utilisateur Modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return utilisateur;
        
        
      }
        public List<Users> supprimerUser(){
        List<Users> utilisateur = new ArrayList<>();
        String sql="DELETE FROM `users` WHERE 0";
        try {
            ste=mc.prepareStatement(sql);
            ste.executeUpdate();
            System.out.println("Utilisateur Supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return utilisateur;
}
}