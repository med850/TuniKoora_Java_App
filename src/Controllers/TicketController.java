


package Controllers;

import Models.Ticket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tools.MaConnexion;

public class TicketController {
 
    
    
      Connection mc;
    PreparedStatement ste;
    
    
      public TicketController() {
        mc=MaConnexion.getInstance().getCnx();
    }
    
    //Ajouter in Ticket
    public void ajouterTicket(Ticket t){
        String sql ="insert into ticket(equipeA,equipeB,prix,match_id,user_id) Values(?,?,?,?,?)";
        try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, t.getEquipeA());
            ste.setString(2, t.getEquipeB());
            ste.setInt(3, t.getPrix());
            ste.setInt(4, t.getMatch_id());
            ste.setInt(5, t.getUser_id());
            ste.executeUpdate();
            System.out.println("ticket Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    
    //Afficher liste des Ticket
    
        public List<Ticket> afficherTicket(){
        List<Ticket> ticket = new ArrayList<>();
        String sql="select * from ticket";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Ticket t = new Ticket();
                t.setId(rs.getInt("id"));
                t.setEquipeA(rs.getString("equipeA"));
                t.setEquipeB(rs.getString("equipeB"));
                t.setPrix(rs.getInt("prix"));
                t.setMatch_id(rs.getInt("match_id"));
                t.setUser_id(rs.getInt("user_id"));
                ticket.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return ticket;
    }    
    
    
    //Modifier un Ticket
     public List<Ticket> updateTicket(Ticket t ){
        List<Ticket> ticket = new ArrayList<>();
        String sql="UPDATE Ticket SET equipeA = ?,   equipeB = ?, prix = ?, match_id = ?, user_id = ? WHERE id = 3";
         try {
            ste=mc.prepareStatement(sql);
             ste=mc.prepareStatement(sql);
            ste.setString(1, t.getEquipeA());
            ste.setString(2, t.getEquipeB());
            ste.setInt(3, t.getPrix());
            ste.setInt(4, t.getMatch_id());
            ste.setInt(5, t.getUser_id());
            ste.executeUpdate();
            System.out.println("Ticket Modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return ticket;
        
        
      }
        
        
        
        //Supprimer un Ticket
     public List<Ticket> supprimerTicket(){
        List<Ticket> ticket = new ArrayList<>();
        String sql="DELETE FROM ticket WHERE id=3";
        try {
            ste=mc.prepareStatement(sql);
            ste.executeUpdate();
            System.out.println("Ticket Supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return ticket;
    }  
    
     
     
      public int getTicketId(String tour){
           
            int ticketId = 0;
           
               mc=MaConnexion.getInstance().getCnx();

     
       
        String sql="select * from ticket where tour = ?";
        try {
            ste=mc.prepareStatement(sql);
            ste.setString(1, tour);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
               
               ticketId = rs.getInt("id");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
           
             
       
             
             
             
              return ticketId;
             
             
        }      

     
     
     
}
