/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;


import Controllers.TicketController;
import Controllers.panier1Controller;
import Controllers.panierController;
import Models.Ticket;
import Models.panier;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.io.IOException;
import java.net.URL;
import java.awt.Desktop;
import static java.awt.SystemColor.window;
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
public class ClticketController implements Initializable {
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
private TextField Gsearch;//        int index = -1;
    @FXML
    private TextField usr;
@FXML
    private TextField idtkt;
//        Connection cn = null;
//        ResultSet rs = null;
//        PreparedStatement ste = null;
    /**
     * Initializes the controller class.
     */

    /**
     * Initializes the controller class.
     */
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

    @FXML
    private void getSelected(MouseEvent event) {
        
        Ticket clickedTickte = tabticket.getSelectionModel().getSelectedItem();
         idtkt.setText(String.valueOf(clickedTickte.getId()));
        usr.setText(String.valueOf(clickedTickte.getUser_id()));
      
    }

    @FXML
    private void addPanier(MouseEvent event) {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");



        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){
        
         int produit_id = Integer.parseInt(idtkt.getText());
           int user_id = Integer.parseInt(usr.getText());
        
        
           
        
        
             
             panier t=new panier( 1 ,user_id, produit_id );
             panierController ec = new panierController();
             ec.ajouterpanier(t);
           // Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setHeaderText("Succes");
             alert.setContentText("achat Ajoutée!");
                alert.showAndWait();             
        
        
         
         
         
            
        }
          
          
          
     
    
    }
    

    @FXML
    private void showPanier(MouseEvent event) {
   try{
   Parent root =FXMLLoader.load(getClass().getResource("/Views/panier.fxml"));
   Scene scene = new Scene(root);
   Stage stage = new Stage() ;
   stage.setScene(scene);
   stage.show();}catch(Exception e){
       e.printStackTrace();
   
   
   }
    
    
    }
   
    }
    


