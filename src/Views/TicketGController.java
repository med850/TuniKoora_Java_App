/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;


import Controllers.TicketController;
import Models.Ticket;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.net.URL;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.runtime.Debug.id;
import tools.MaConnexion;








/**
 * FXML Controller class
 *
 * @author CHAOUCH KHALIL
 */
public class TicketGController implements Initializable {
@FXML
    private TableColumn<Ticket, Integer> idticket;
@FXML   
    private TableColumn<Ticket, String> NomEquipeA;
@FXML
    private TableColumn<Ticket, String> NomEquipeB;
@FXML
    private TableColumn<Ticket, Integer> Gprix ;

@FXML
    private TableColumn<Ticket, Integer> GNmatch;
@FXML
    private TableColumn<Ticket, Integer> Gusr;

     Connection mc;
    PreparedStatement ste;
    int index = -1;
 
    @FXML
    private TableView<Ticket> tabticket;
    
       ObservableList<Ticket>ticketList;
       
       
        ObservableList<Ticket>listticketSearch;

@FXML
    private TextField idtk;
@FXML
    private TextField nomEqA;
@FXML
    private TextField nomEqB;
@FXML
    private TextField Tprix;
@FXML
    private TextField mch;
@FXML
    private TextField usr;

@FXML
private TextField Gsearch;//        int index = -1;
//        Connection cn = null;
//        ResultSet rs = null;
//        PreparedStatement ste = null;
    /**
     * Initializes the controller class.
     */

    /**
     * Initializes the controller class.
     */
      @Override
    public void initialize(URL url, ResourceBundle rb) {
      //  showTicket();
        
           mc=MaConnexion.getInstance().getCnx();
// TicketController tk = new TicketController() ;
        ticketList = FXCollections.observableArrayList();
        
        String sql="SELECT * FROM ticket" 
;
//"LEFT JOIN matchtb ON   ticket.match_id =  matchtb.id ";
        
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                
//                ticketList.add(new Ticket(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getString(5),rs.getString(6)));
                Ticket t = new Ticket();
                t.setId(rs.getInt("id"));
                t.setEquipeA(rs.getString("equipeA"));
                t.setEquipeB(rs.getString("equipeB"));
                t.setPrix(rs.getInt("prix"));
                t.setMatch_id(rs.getInt("match_id"));
                t.setUser_id(rs.getInt("user_id"));
                ticketList.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        

        
         idticket.setCellValueFactory(new PropertyValueFactory<Ticket,Integer>("id"));
        NomEquipeA.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEquipeA()));
        NomEquipeB.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEquipeB()));
        Gprix.setCellValueFactory(new PropertyValueFactory<Ticket,Integer>("prix"));
        GNmatch.setCellValueFactory(new PropertyValueFactory<Ticket,Integer>("match_id"));
        Gusr.setCellValueFactory(new PropertyValueFactory<Ticket,Integer>("user_id"));
        tabticket.setItems(ticketList);

        
        
    }    

    @FXML
    private void addTicket(MouseEvent event) throws IOException, SQLException {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");



        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){
        
        
         String equipeA = nomEqA.getText();
         String equipeB = nomEqB.getText();
        int prix = Integer.parseInt(Tprix.getText());
        int match_id = Integer.parseInt(mch.getText());
        int user_id = Integer.parseInt(usr.getText());
           
        
        
         String sql="select * from equipe where match_id='"+match_id+"'";
        ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            if(rs.next() == true){
                
                  Alert alert1 = new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText("ERROR");
             alert.setContentText("ticket deja existe");
             alert.showAndWait();
             
               idtk.setText(null);
          nomEqA.setText(null);
          nomEqB.setText(null);
        Tprix.setText(null);
        mch.setText(null);
        usr.setText(null);
            }else{
        
         if (equipeA.isEmpty()){
             //Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText("ERROR");
             alert.setContentText("Insérer toutes les informations avant de valider l'insertion");
             alert.showAndWait();
               
             
          }else{
             
             Ticket t=new Ticket( 1 ,prix,match_id,user_id,equipeA , equipeB );
             TicketController ec = new TicketController();
             ec.ajouterTicket(t);
           // Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setHeaderText("Succes");
             alert.setContentText("ticket Ajoutée!");
                alert.showAndWait();             
        
        
         }
         
          refresh();
            }}else{
             idtk.setText(null);
          nomEqA.setText(null);
          nomEqB.setText(null);
        Tprix.setText(null);
        mch.setText(null);
        usr.setText(null);
            
        }
          
          
          
        
    }
    



    @FXML
 private void print(MouseEvent event) throws FileNotFoundException, DocumentException, SQLException, IOException {
        
        
        
           String sql = "SELECT * from ticket";
    ste=mc.prepareStatement(sql);
    ResultSet rs=ste.executeQuery();

    Document doc = new Document();
    PdfWriter.getInstance(doc, new FileOutputStream("./ListeDesEquipes.pdf"));

    doc.open();
   
    doc.add(new Paragraph("   "));
    doc.add(new Paragraph(" ***************************************** Liste Des Equipes ***************************************** "));
    doc.add(new Paragraph("   "));

    PdfPTable table = new PdfPTable(2);
    table.setWidthPercentage(50);
    PdfPCell cell;

    cell = new PdfPCell(new Phrase("Nom des equipeA", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    
    table.addCell(cell);
   
    cell = new PdfPCell(new Phrase("Nom des equipeB", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    
    table.addCell(cell);

    


    while (rs.next()) {

        Ticket e = new Ticket();
      //  e.setId(rs.getInt("id"));
        e.setEquipeA(rs.getString("equipeA"));
        e.setEquipeB(rs.getString("equipeB"));
       // e.setClassement(rs.getInt("classement"));
       
      
        cell = new PdfPCell(new Phrase(e.getEquipeA(), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase(e.getEquipeB(), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        table.addCell(cell);
        
        

    }

    doc.add(table);
    doc.close();
    Desktop.getDesktop().open(new File("./ListeDesEquipes.pdf"));
;
    }

    @FXML
    private void updateTicket(MouseEvent event) {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");



        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){
        
        Ticket clickedTicket = tabticket.getSelectionModel().getSelectedItem();
        
        
        try{
             mc=MaConnexion.getInstance().getCnx();
              String value1 = idtk.getText();
             String value2 = nomEqA.getText();
             String value3 = nomEqB.getText();
             String value4 = Tprix.getText();
             String value5 = mch.getText();
             String value6 = usr.getText();
             
             String sql = "update ticket set id = '"+value1+"', equipeA = '"+value2+"', equipeB = '"+value3+"', prix='"+value4+"' , match_id = '"+value5+"' , user_id = '"+value6+"' where id ='"+value1+"'";
             ste=mc.prepareStatement(sql);
             ste.execute();
            JOptionPane.showMessageDialog(null, "ticket modifié");
        }catch(Exception e){
               JOptionPane.showMessageDialog(null,e);

        }
        
        
        refresh();}else{
              idtk.setText(null);
          nomEqA.setText(null);
          nomEqB.setText(null);
        Tprix.setText(null);
        mch.setText(null);
        usr.setText(null);
            
        }
    }

    @FXML
    private void deleteTicket(MouseEvent event) throws SQLException {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");



        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){ 
        
        
        mc=MaConnexion.getInstance().getCnx();
         String sql = "delete from ticket where id = ?";
            ste=mc.prepareStatement(sql);
            ste.setString(1, idtk.getText());
            ste.execute();
            JOptionPane.showMessageDialog(null, "ticket supprimé" );
        
            refresh();}else{
             idtk.setText(null);
          nomEqA.setText(null);
          nomEqB.setText(null);
        Tprix.setText(null);
        mch.setText(null);
        usr.setText(null);
        }
        
    }

    @FXML
    private void getSelected(MouseEvent event) {
        
        Ticket clickedTickte = tabticket.getSelectionModel().getSelectedItem();
         idtk.setText(String.valueOf(clickedTickte.getId()));
        nomEqA.setText(String.valueOf(clickedTickte.getEquipeA()));
        nomEqB.setText(String.valueOf(clickedTickte.getEquipeB()));
        Tprix.setText(String.valueOf(clickedTickte.getPrix()));
        mch.setText(String.valueOf(clickedTickte.getMatch_id()));
        usr.setText(String.valueOf(clickedTickte.getUser_id()));
     
    }
    
    
    public void refresh(){
        
         ticketList.clear();
       
          
          mc=MaConnexion.getInstance().getCnx();

        ticketList = FXCollections.observableArrayList();
        
        String sql="select * from ticket ";
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
                ticketList.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         tabticket.setItems(ticketList);
        
        
        
    }

    @FXML
    private void refreshChamp(MouseEvent event) {
        
        
          idtk.setText(null);
          nomEqA.setText(null);
          nomEqB.setText(null);
        Tprix.setText(null);
        mch.setText(null);
        usr.setText(null);
        
    }

    @FXML
    private void rechercheEq(MouseEvent event) {
        
//     @FXML
  
//        
//          col_id.setCellValueFactory(new PropertyValueFactory<users,Integer>("id"));
       
        
       idticket.setCellValueFactory(new PropertyValueFactory<Ticket,Integer>("id"));
        NomEquipeA.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEquipeA()));
        NomEquipeB.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEquipeB()));
        Gprix.setCellValueFactory(new PropertyValueFactory<Ticket,Integer>("prix"));
     
        
        
        
           //prodList = MaConnexion.getDatausers();
        tabticket.setItems(ticketList);
        FilteredList<Ticket> filteredData = new FilteredList<>(ticketList, b -> true);  
 Gsearch.textProperty().addListener((observable, oldValue, newValue) -> {
 filteredData.setPredicate(person -> {
    if (newValue == null || newValue.isEmpty()) {
     return true;
    }    
    String lowerCaseFilter = newValue.toLowerCase();
    
    if (person.getEquipeA().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
     return true; // Filter matches username
    } else if (person.getEquipeB().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    
    }
    else if (String.valueOf(person.getUser_id()).indexOf(lowerCaseFilter)!=-1)
         return true;// Filter matches email
                                
         else  
          return false; // Does not match.
   });
  });  
  SortedList<Ticket> sortedData = new SortedList<>(filteredData);  
  sortedData.comparatorProperty().bind(tabticket.comparatorProperty());  
  tabticket.setItems(sortedData);     
        
    }
        
        
    }
    


