/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;


import Controllers.ProduitController;
import Controllers.panier1Controller;
import Models.Produit;
import Models.panier1;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.embed.swing.SwingFXUtils;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import tools.MaConnexion;

/**
 * FXML Controller class
 *
 * @author CHAOUCH KHALIL
 */
public class ClprodController implements Initializable {
@FXML
    private TableColumn<Produit, Integer> idproduit;
@FXML   
    private TableColumn<Produit, String> NomProduit;
@FXML
    private TableColumn<Produit, Integer> Pprix;
@FXML
    private TableColumn<Produit, Integer> Gqte ;
@FXML
    private TableColumn<Produit, String> Gimage ;
@FXML
    private TableColumn<Produit, String> Gdescp ;
@FXML
    private TableColumn<Produit, Integer> Pusr;


     Connection mc;
    PreparedStatement ste;
    int index = -1;
 
    @FXML
    private TableView<Produit> tabprod;
    
       ObservableList<Produit>prodList;
       
       
        ObservableList<Produit>listprodSearch;



@FXML
private TextField search;//        int index = -1;
    @FXML
    private TextField usr;
    @FXML
    private TextField idpp;
    @FXML
    private TextField idppp;
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

        prodList = FXCollections.observableArrayList();
        
        String sql="select * from produit ";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Produit t = new Produit();
                t.setId(rs.getInt("id"));
                t.setNom(rs.getString("nom"));
                t.setPrix(rs.getInt("prix"));
                t.setQte(rs.getInt("qte"));
                t.setDescription(rs.getString("description"));
               t.setImage(rs.getString("image"));
                t.setUser_id(rs.getInt("user_id"));
                prodList.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        

        
         idproduit.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("id"));
         NomProduit.setCellValueFactory(new PropertyValueFactory<Produit,String>("nom"));
         Pprix.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("prix"));
         Gqte.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("qte"));
         Gimage.setCellValueFactory(new PropertyValueFactory<Produit,String>("image"));
        Gdescp.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescription()));
        //NomEquipeB.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getequipeB()));
       // Gdescp.setCellValueFactory(new PropertyValueFactory<Produit,String>("description"));
         Pusr.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("user_id"));
        tabprod.setItems(prodList);


        
        
    }    

 
@FXML
    private void rechercheEq(MouseEvent event) {
        
//        
//          col_id.setCellValueFactory(new PropertyValueFactory<users,Integer>("id"));
       
        
        idproduit.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("id"));
         NomProduit.setCellValueFactory(new PropertyValueFactory<Produit,String>("nom"));
         Pprix.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("prix"));
         Gqte.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("qte"));
         Gimage.setCellValueFactory(new PropertyValueFactory<Produit,String>("image"));
        //NomEquipeA.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getequipeA()));
        //NomEquipeB.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getequipeB()));
        Gdescp.setCellValueFactory(new PropertyValueFactory<Produit,String>("description"));
         Pusr.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("user_id"));
     
        
        
        
           //prodList = MaConnexion.getDatausers();
        tabprod.setItems(prodList);
        FilteredList<Produit> filteredData = new FilteredList<>(prodList, b -> true);  
 search.textProperty().addListener((observable, oldValue, newValue) -> {
 filteredData.setPredicate(person -> {
    if (newValue == null || newValue.isEmpty()) {
     return true;
    }    
    String lowerCaseFilter = newValue.toLowerCase();
    
    if (person.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
     return true; // Filter matches username
    } else if (person.getImage().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }else if (person.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
    }
    else if (String.valueOf(person.getUser_id()).indexOf(lowerCaseFilter)!=-1)
         return true;// Filter matches email
                                
         else  
          return false; // Does not match.
   });
  });  
  SortedList<Produit> sortedData = new SortedList<>(filteredData);  
  sortedData.comparatorProperty().bind(tabprod.comparatorProperty());  
  tabprod.setItems(sortedData);     
        
    }

    @FXML
    private void getSelected(MouseEvent event) {
          Produit clickedprod = tabprod.getSelectionModel().getSelectedItem();
         idpp.setText(String.valueOf(clickedprod.getId()));
        usr.setText(String.valueOf(clickedprod.getUser_id()));
    }

    @FXML
    private void print(MouseEvent event) throws FileNotFoundException, DocumentException, SQLException, IOException {
        
        
        
           String sql = "SELECT * from produit";
    ste=mc.prepareStatement(sql);
    ResultSet rs=ste.executeQuery();

    Document doc = new Document();
    PdfWriter.getInstance(doc, new FileOutputStream("./ListeDesprod.pdf"));

    doc.open();
   
    doc.add(new Paragraph("   "));
    doc.add(new Paragraph(" ***************************************** Liste Des Produits ***************************************** "));
    doc.add(new Paragraph("   "));

    PdfPTable table = new PdfPTable(2);
    table.setWidthPercentage(50);
    PdfPCell cell;

    cell = new PdfPCell(new Phrase("Nom du prod", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    
    table.addCell(cell);
   
    cell = new PdfPCell(new Phrase("description", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    
    table.addCell(cell);

    


    while (rs.next()) {

        Produit e = new Produit();
      //  e.setId(rs.getInt("id"));
        e.setNom(rs.getString("nom"));
        e.setDescription(rs.getString("description"));
       // e.setClassement(rs.getInt("classement"));
       
      
        cell = new PdfPCell(new Phrase(e.getNom(), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase(e.getDescription(), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        table.addCell(cell);
        
        

    }

    doc.add(table);
    doc.close();
    Desktop.getDesktop().open(new File("./ListeDesprod.pdf"));
;
    }

    @FXML
    private void showPanier(MouseEvent event) {
                try{
   Parent root =FXMLLoader.load(getClass().getResource("/Views/panier1.fxml"));
   Scene scene = new Scene(root);
   Stage stage = new Stage() ;
   stage.setScene(scene);
   stage.show();}catch(Exception e){
       e.printStackTrace();
   
   
   }  
          
     
    
    }
    

    @FXML
    private void addPanier(MouseEvent event) {
  Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");



        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){
        // int idPanier = Integer.parseInt(idppp.getText());
         int produit_id = Integer.parseInt(idpp.getText());
           int user_id = Integer.parseInt(usr.getText());
   
             panier1 t=new panier1(1 ,user_id, produit_id );
             panier1Controller ec = new panier1Controller();
             ec.ajouterpanier(t);
           // Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setHeaderText("Succes");
             alert.setContentText("achat Ajoutée!");
                alert.showAndWait();             
        
        
         
         
         
            
        }
    }
    
}
