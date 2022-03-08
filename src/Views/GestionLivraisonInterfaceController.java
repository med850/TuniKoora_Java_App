/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.LivraisonController;
import Models.Livraison;
import Models.Users;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.Event;
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

import javax.swing.JOptionPane;

import tools.MaConnexion;

/**
 * FXML Controller class
 *
 * @author jerby
 */
public class GestionLivraisonInterfaceController implements Initializable {

    @FXML
    private TableView<Livraison> LivraisonTable;
    
    ObservableList<Livraison>livraisonList;
    
    ObservableList<Livraison>listLivraisonForSearch;
    
    @FXML
    private TextField search;
    @FXML
    private TableColumn<Livraison, Integer> idLivraison;
    @FXML
    private TableColumn<Livraison, String> RefLivraison;
    @FXML
    private TableColumn<Livraison, String> Localisation;
    @FXML
    private TableColumn<Livraison, String> Etat;
    @FXML
    private TableColumn<Livraison, Integer> User_ID;
    @FXML
    private TextField refLivraison;
    @FXML
    private TextField localisationLivraison;
    @FXML
    private TextField User_IDLiv;
    @FXML
    private TextField etatLivraison;

    Connection mc;
    PreparedStatement ste;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        mc=MaConnexion.getInstance().getCnx();

        livraisonList = FXCollections.observableArrayList();
        
        String sql="select * from livraison ";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Livraison l = new Livraison();
                l.setId(rs.getInt("id"));
                l.setRef(rs.getString("ref"));
                l.setLocalisation(rs.getString("localisation"));
                l.setEtat(rs.getString("etat"));
                l.setUser_id(rs.getInt("user_id"));
                livraisonList.add(l);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        

        
        idLivraison.setCellValueFactory(new PropertyValueFactory<Livraison, Integer>("id"));
        RefLivraison.setCellValueFactory(new PropertyValueFactory<Livraison, String>("ref"));
        Localisation.setCellValueFactory(new PropertyValueFactory<Livraison, String>("localisation"));
        Etat.setCellValueFactory(new PropertyValueFactory<Livraison, String>("etat"));
        User_ID.setCellValueFactory(new PropertyValueFactory<Livraison, Integer>("user_id"));
        




        
        LivraisonTable.setItems(livraisonList);


        
        
        
        //rechercheLiv();
        
        
        
        
    }  
    
    
    @FXML
    private void addLivraison(MouseEvent event) throws IOException, SQLException {
        
        Livraison l= new Livraison();
        
        LivraisonController lc = new LivraisonController();
        String ref =  refLivraison.getText();
        String localisation =  localisationLivraison.getText();
        String etat =  etatLivraison.getText();
        int userid = Integer.parseInt(User_IDLiv.getText());
           
        
        
         if (ref.isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText("ERROR");
             alert.setContentText("Insérer toutes les informations avant de valider l'insertion");
             alert.showAndWait();
               
//         } else if (eq2.checkLivraison(e1)==true){
//              Alert alert = new Alert(Alert.AlertType.ERROR);
//             alert.setHeaderText("ERROR");
//             alert.setContentText("Element existant");
//             alert.showAndWait();


           
         }else{
             
             Livraison e=new Livraison(3,userid,ref,localisation,etat);
             LivraisonController ec = new LivraisonController();
             ec.ajouterLivraison(e);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setHeaderText("Succes");
             alert.setContentText("Livraison Ajoutée!");
                alert.showAndWait();             
        
        
         }
         
          refresh();
          
          
        
        
    }
    
    
    
    @FXML
    private void print(MouseEvent event) throws FileNotFoundException, DocumentException, SQLException, IOException {
        
        
        
           String sql = "SELECT * from livraison";
    ste=mc.prepareStatement(sql);
    ResultSet rs=ste.executeQuery();

    Document doc = new Document();
    PdfWriter.getInstance(doc, new FileOutputStream("./ListeDesLivraisons.pdf"));

    doc.open();
    //Image image = Image.getInstance("C:\\Users\\drwhoo\\Desktop\\Projet3eme\\JavaApplication\\src\\HolidaysHiatus\\images\\logo.png");

    //document.add(new Paragraph("test\n  test"));
   // doc.add(image);
    doc.add(new Paragraph("   "));
    doc.add(new Paragraph(" ***************************************** Liste Des Livraisons ***************************************** "));
    doc.add(new Paragraph("   "));

    PdfPTable table = new PdfPTable(2);
    table.setWidthPercentage(50);
    PdfPCell cell;

    cell = new PdfPCell(new Phrase("Nom des livraisons", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);

    table.addCell(cell);

//    cell = new PdfPCell(new Phrase("Classement", FontFactory.getFont("Comic Sans MS", 15)));
//    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//
//    table.addCell(cell);

    while (rs.next()) {

        Livraison l = new Livraison();
        //l.setId(rs.getInt("id"));
        l.setLocalisation(rs.getString("localisation"));
        l.setEtat(rs.getString("etat"));
        l.setUser_id(rs.getInt("user_id"));
       
      
        cell = new PdfPCell(new Phrase(l.getRef(), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase(l.getLocalisation()));
        cell = new PdfPCell(new Phrase(l.getEtat()));
        
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        table.addCell(cell);
        
        
        //Image image1 = Image.getInstance("C:\\Users\\drwhoo\\Desktop\\Projet3eme\\SymfonyApplication\\public\\uploads\\" + c.getLien_icon());
        //PdfPCell cell1 = new PdfPCell(image1, true);

      //  table.addCell(cell1);

    }

    doc.add(table);
    doc.close();
    Desktop.getDesktop().open(new File("./ListeDesLivraisons.pdf"));
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
//            mc=MaConnexion.getInstance().getCnx();
//        
//        
//         String sql="select * from equipe order by classement ASC";
//   
//            ste=mc.prepareStatement(sql);
//            ResultSet rs=ste.executeQuery();
//            rs.first();
//            
//            Document d = new Document();
//            PdfWriter.getInstance(d, new FileOutputStream("listeEquipe.pdf"));
//            
//            
//           // d.add(new Paragraph("Liste Equipe"));
//            d.close();
//        
        
        
        
        
//        
//        Equipe equipe = new Equipe();
//        
//        
//        
//        String path = "";
//        JFileChooser j  = new JFileChooser();
//        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//        int x = j.showSaveDialog(null);
//        
//          if(x ==JFileChooser.APPROVE_OPTION){
//              
//              path = j.getSelectedFile().getPath();
//              
//          }
//          
//          Document doc = new Document();
//
//
//           
//            doc.close();

//          
//          PdfWriter.getInstance(doc, new FileOutputStream(path+"listeEquipe.pdf"));
//          
//          doc.open();
//          
//          PdfPTable tbl = new PdfPTable(1);
//          
////          tbl.addCell("Id");
//          tbl.addCell("Nom Equipe");
////          tbl.addCell("Classement");
//          
//          
//          
//        
//          
//          for(int i = 0;i<equipeTable.getItems().size(); i++){
//              
//              equipe = equipeTable.getItems().get(i);
//             String Nom = equipe.getNom();
//          //   String Classement = equipe.getClassement().toString();
//           
//             tbl.addCell(Nom);
//           //  tbl.addCell(Classement);
//              
//              
//          }
//          
//          doc.add(tbl);
//          doc.close();
    }
    
    
    @FXML
    private void updateLivraison(MouseEvent event) {
        
        
        Livraison clickedLivraison = LivraisonTable.getSelectionModel().getSelectedItem();
        
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");
                 
        
        
        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){
                
        try{
             mc=MaConnexion.getInstance().getCnx();
              String value1 = refLivraison.getText();
             String value2 = localisationLivraison.getText();
             String value3 = etatLivraison.getText();
             String value4 = User_IDLiv.getText();
             
             String sql = "update livraison set ref = '"+value1+"', localisation = '"+value2+"', etat='"+value3+"', user_id='"+value4+"' where ref ='"+value1+"'";
             ste=mc.prepareStatement(sql);
             ste.execute();
            JOptionPane.showMessageDialog(null, "Livraison modifié");
        }catch(Exception e){
               JOptionPane.showMessageDialog(null,e);

        }
        }else{
            
        refLivraison.setText(null);
        localisationLivraison.setText(null);
        etatLivraison.setText(null);
        User_IDLiv.setText(null);
        
            
        }
        
        
        refresh();
    }
    
    
     @FXML
    private void deleteLivraison(MouseEvent event) throws SQLException {
        
        
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");
                 
        
        
        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){
        
        
        
        
        mc=MaConnexion.getInstance().getCnx();
         String sql = "delete from livraison where ref = ?";
            ste=mc.prepareStatement(sql);
            ste.setString(1, refLivraison.getText());
            ste.execute();
            JOptionPane.showMessageDialog(null, "Livraison supprimé" );
        
            refresh();
            
        }else{
            
            
        refLivraison.setText(null);
        localisationLivraison.setText(null);
        etatLivraison.setText(null);
        User_IDLiv.setText(null);
        
            
        }
        
    }
    
    @FXML
    private void getSelected(MouseEvent event) {
        
        Livraison clickedLivraison = LivraisonTable.getSelectionModel().getSelectedItem();
        refLivraison.setText(String.valueOf(clickedLivraison.getRef()));
        localisationLivraison.setText(String.valueOf(clickedLivraison.getLocalisation()));
        etatLivraison.setText(String.valueOf(clickedLivraison.getEtat()));
        User_IDLiv.setText(String.valueOf(clickedLivraison.getUser_id())) ;
     
    }
    
    public void refresh(){
        
         livraisonList.clear();
       
          
          mc=MaConnexion.getInstance().getCnx();

        livraisonList = FXCollections.observableArrayList();
        
        String sql="select * from livraison";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Livraison l = new Livraison();
                l.setRef(rs.getString("ref"));
                l.setLocalisation(rs.getString("localisation"));
                l.setEtat(rs.getString("etat"));
                l.setUser_id(rs.getInt("user_id"));
                livraisonList.add(l);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         LivraisonTable.setItems(livraisonList);
        
        
        
    }
    
     @FXML
    private void refreshChamp(MouseEvent event) {
        
        
        refLivraison.setText(null);
        localisationLivraison.setText(null);
        etatLivraison.setText(null);
        User_IDLiv.setText(null);
        
    }
    @FXML
    private void rechercheLiv() {
        
       
       //         idEquipe.setCellValueFactory(new PropertyValueFactory<Equipe, Integer>("id"));
//        NomEquipe.setCellValueFactory(new PropertyValueFactory<Equipe, String>("nom"));
//        ClassementEquipe.setCellValueFactory(new PropertyValueFactory<Equipe, String>("classement"));
//        
        FilteredList<Livraison>filteredData = new FilteredList<>(livraisonList, b->true);
        
     //   Equipe equipe = new Equipe();
        search.textProperty().addListener((observable, oldValue, newValue)->{
            
            filteredData.setPredicate(livraison->{
                
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if(livraison.getRef().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }else if(String.valueOf(livraison.getLocalisation()).indexOf(lowerCaseFilter) != -1){
                    return true;
                }else{
                return false;
                }
                
                
            });
            
            
            
            
        });
        
        SortedList<Livraison>sortedData = new SortedList<>(filteredData);
        
        sortedData.comparatorProperty().bind(LivraisonTable.comparatorProperty());
        
        LivraisonTable.setItems(sortedData);
        
        
    }

    @FXML
    private void SmsSender() throws SQLException {
        
       
      String ACCOUNT_SID = "AC3d517fdc7632fe0fb2b4a0e6b8cdcdaf";
      String AUTH_TOKEN = "1b1556a1021ce9ec2eab8e7e6fb86679";
      String value = User_IDLiv.getText() ;
      
      mc=MaConnexion.getInstance().getCnx() ;
        
        String sql="select tel from users where id = '"+value+"'" ;
      
      ste=mc.prepareStatement(sql);
      ResultSet rs=ste.executeQuery();  
      Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
      rs.next() ;
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+216"+rs.getString("tel")),
                new com.twilio.type.PhoneNumber("+19182768709"),
                "Where's golden?")
            .create();

        
    
    }


}
