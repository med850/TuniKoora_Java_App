/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;


import Controllers.ProduitController;
import Models.Produit;
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
public class ProduitGController implements Initializable {
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
    private TextField idP;
@FXML
    private TextField Pnom;
@FXML
    private TextField TPprix;
@FXML
    private TextField TPqte;
@FXML
    private TextField Timg;
@FXML
    private TextField TPdescrp;
@FXML
    private TextField TPusr;

@FXML
private TextField search;//        int index = -1;
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
        idP.setVisible(false);
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
    private void addProduit(MouseEvent event) throws IOException, SQLException {
        
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");



        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){
        
        
        
         String nom = Pnom.getText();
        int prix = Integer.parseInt(TPprix.getText());
        int qte = Integer.parseInt(TPqte.getText());
        String image = Timg.getText();
        String description = TPdescrp.getText();
        int user_id = Integer.parseInt(TPusr.getText());
           
        
        
        
         String sql="select * from produit where nom='"+nom+"'";
        ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            if(rs.next() == true){
                
                  Alert alert1 = new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText("ERROR");
             alert.setContentText("Equipe deja existe");
             alert.showAndWait();
             
               idP.setText(null);
          Pnom.setText(null);
          TPprix.setText(null);
        TPqte.setText(null);
        Timg.setText(null);
        TPdescrp.setText(null);
          TPusr.setText(null);

                
            }else{
        
        
        
        
        
        
         if (nom.isEmpty()){
            // Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText("ERROR");
             alert.setContentText("Insérer toutes les informations avant de valider l'insertion");
             alert.showAndWait();
               
             
         }else{
             
             Produit t=new Produit( 1 , prix, qte, user_id,  nom,  image,  description );
             ProduitController ec = new ProduitController();
             ec.ajouterProduit(t);
           // Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setHeaderText("Succes");
             alert.setContentText("Prod Ajoutée!");
                alert.showAndWait();             
        
         idP.setText(null);
          Pnom.setText(null);
          TPprix.setText(null);
        TPqte.setText(null);
        Timg.setText(null);
        TPdescrp.setText(null);
          TPusr.setText(null);
         }
         
          refresh();
        }}else{
             idP.setText(null);
          Pnom.setText(null);
          TPprix.setText(null);
        TPqte.setText(null);
        Timg.setText(null);
        TPdescrp.setText(null);
        TPusr.setText(null);
        }
          
        
    }
    




    @FXML
    private void updateTicket(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");



        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){
        
        
        
        
        Produit clickedTicket = tabprod.getSelectionModel().getSelectedItem();
        
        
        try{
             mc=MaConnexion.getInstance().getCnx();
              String value1 = idP.getText();
             String value2 = Pnom.getText();
             String value3 = TPprix.getText();
             String value4 = TPqte.getText();
             String value5 = Timg.getText();
             String value6 = TPdescrp.getText();
             String value7 = TPusr.getText();
             
             String sql = "update produit set id = '"+value1+"', nom = '"+value2+"', prix = '"+value3+"', qte='"+value4+"' , description = '"+value5+"' , image = '"+value6+"'  , user_id = '"+value7+"'  where id ='"+value1+"'";
             ste=mc.prepareStatement(sql);
             ste.execute();
            JOptionPane.showMessageDialog(null, "prood modifié");
            
             idP.setText(null);
          Pnom.setText(null);
          TPprix.setText(null);
        TPqte.setText(null);
        Timg.setText(null);
        TPdescrp.setText(null);
          TPusr.setText(null);
            
        }catch(Exception e){
               JOptionPane.showMessageDialog(null,e);

        }
        
        
        refresh();
        }else{
             idP.setText(null);
          Pnom.setText(null);
          TPprix.setText(null);
        TPqte.setText(null);
        Timg.setText(null);
        TPdescrp.setText(null);
        TPusr.setText(null);
       
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
         String sql = "delete from produit where id = ?";
            ste=mc.prepareStatement(sql);
            ste.setString(1, idP.getText());
            ste.execute();
            JOptionPane.showMessageDialog(null, "prod supprimé" );
            
             idP.setText(null);
          Pnom.setText(null);
          TPprix.setText(null);
        TPqte.setText(null);
        Timg.setText(null);
        TPdescrp.setText(null);
          TPusr.setText(null);
        
            refresh();}else{
              idP.setText(null);
          Pnom.setText(null);
          TPprix.setText(null);
        TPqte.setText(null);
        Timg.setText(null);
        TPdescrp.setText(null);
        TPusr.setText(null);
       
        };
        
    }

    @FXML
    private void getSelected(MouseEvent event) {
        
        Produit clickedProd = tabprod.getSelectionModel().getSelectedItem();
         idP.setText(String.valueOf(clickedProd.getId()));
        Pnom.setText(String.valueOf(clickedProd.getNom()));
        TPprix.setText(String.valueOf(clickedProd.getPrix()));
        TPqte.setText(String.valueOf(clickedProd.getQte()));
        Timg.setText(String.valueOf(clickedProd.getImage()));
        TPdescrp.setText(String.valueOf(clickedProd.getDescription()));
        TPusr.setText(String.valueOf(clickedProd.getUser_id()));
     
    }
    
    
    public void refresh(){
        
         prodList.clear();
       
          
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
         tabprod.setItems(prodList);
        
        
        
    }

    private void refreshChamp(MouseEvent event) {
        
        
          idP.setText(null);
          Pnom.setText(null);
          TPprix.setText(null);
        TPqte.setText(null);
        Timg.setText(null);
        TPdescrp.setText(null);
        TPusr.setText(null);
       
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

    
    
}
