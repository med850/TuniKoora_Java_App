
package Controllers;

import Models.Equipe;
import Models.Joueur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tools.MaConnexion;

public class JoueurController {
 
    
    
      Connection mc;
    PreparedStatement ste;
    
    
      public JoueurController() {
        mc=MaConnexion.getInstance().getCnx();
    }
    
    //Ajouter in joueur
    public void ajouterJoueur(Joueur j){
        String sql ="insert into joueur(nom,prenom,image,poste,tel,equipe_id) Values(?,?,?,?,?,?)";
        try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, j.getNom());
            ste.setString(2, j.getPrenom());
            ste.setString(3, j.getImage());
            ste.setString(4, j.getPoste());
            ste.setInt(5, j.getTel());
            ste.setInt(6, j.getEquipe_id());
            ste.executeUpdate();
            System.out.println("Joueur Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    
    //Afficher liste des joueurs
    
        public List<Joueur> afficherJoueur(){
        List<Joueur> joueurs = new ArrayList<>();
        String sql="select * from joueur INNER JOIN equipe ON joueur.equipe_id = equipe.id";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Joueur j = new Joueur();
                j.setId(rs.getInt("id"));
                j.setNom(rs.getString("nom"));
                j.setPrenom(rs.getString("prenom"));
                j.setImage(rs.getString("image"));
                j.setPoste(rs.getString("poste"));
                j.setTel(rs.getInt("tel"));
                j.setEquipe_id(rs.getInt("equipe_id"));
                joueurs.add(j);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return joueurs;
    }    
    
    
    //Modifier un Joueur
    
     public List<Joueur> updateJoueur(Joueur j ){
        List<Joueur> joueurs = new ArrayList<>();
        String sql="UPDATE joueur SET nom = ?, prenom = ?, image = ?, poste = ?, tel = ?, equipe_id = ? WHERE id = 2";
         try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, j.getNom());
            ste.setString(2, j.getPrenom());
            ste.setString(3, j.getImage());
            ste.setString(4, j.getPoste());
            ste.setInt(5, j.getTel());
            ste.setInt(6, j.getEquipe_id());
            ste.executeUpdate();
            System.out.println("Joueur Modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return joueurs;
        
        
      }
        
        
        
        //Supprimer un joueur
     public List<Joueur> supprimerJoueur(){
        List<Joueur> joueurs = new ArrayList<>();
        String sql="DELETE FROM joueur WHERE id=1";
        try {
            ste=mc.prepareStatement(sql);
            ste.executeUpdate();
            System.out.println("Joueur Supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return joueurs;
    }  
        
    
    
    
    
}
