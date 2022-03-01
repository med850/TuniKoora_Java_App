/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.EquipeController;
import Models.Equipe;
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
 * @author mdhah
 */
public class GestionEquipeInterfaceController implements Initializable {

    @FXML
    private TableColumn<Equipe, Integer> idEquipe;
    @FXML
    private TableColumn<Equipe, String> NomEquipe;
    @FXML
    private TableColumn<Equipe, String> ClassementEquipe;

     Connection mc;
    PreparedStatement ste;

 
    @FXML
    private TableView<Equipe> equipeTable;
    
       ObservableList<Equipe>equipeList;
       
       
      //  ObservableList<Equipe>listEquipeForSearch;

    @FXML
    private TextField nomEq;
    @FXML
    private TextField classementEq;
    @FXML
    private TextField idEq;
    @FXML
    private TextField search;
//        int index = -1;
//        Connection cn = null;
//        ResultSet rs = null;
//        PreparedStatement ste = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
           mc=MaConnexion.getInstance().getCnx();

        equipeList = FXCollections.observableArrayList();
        
        String sql="select * from equipe order by classement ASC";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Equipe e = new Equipe();
                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("nom"));
                e.setClassement(rs.getInt("classement"));
                equipeList.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        

        
         idEquipe.setCellValueFactory(new PropertyValueFactory<Equipe, Integer>("id"));
        NomEquipe.setCellValueFactory(new PropertyValueFactory<Equipe, String>("nom"));
        ClassementEquipe.setCellValueFactory(new PropertyValueFactory<Equipe, String>("classement"));




        
        equipeTable.setItems(equipeList);


        
        
        
        rechercheEq();
        
        
        
        
    }    

    @FXML
    private void addEquipe(MouseEvent event) throws IOException, SQLException {
        
        Equipe e1= new Equipe();
        
        EquipeController eq2 = new EquipeController();
         String nom = nomEq.getText();
        int classement = Integer.parseInt(classementEq.getText());
           
        
        
         if (nom.isEmpty()){
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
             
             Equipe e=new Equipe(1,nom,classement);
             EquipeController ec = new EquipeController();
             ec.ajouterEquipe(e);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setHeaderText("Succes");
             alert.setContentText("Equipe Ajoutée!");
                alert.showAndWait();             
        
        
         }
         
          refresh();
          
          
             idEq.setText(null);
          nomEq.setText(null);
        classementEq.setText(null);
        
    }
    



    @FXML
    private void print(MouseEvent event) throws FileNotFoundException, DocumentException, SQLException, IOException {
        
        
        
           String sql = "SELECT * from equipe";
    ste=mc.prepareStatement(sql);
    ResultSet rs=ste.executeQuery();

    Document doc = new Document();
    PdfWriter.getInstance(doc, new FileOutputStream("./ListeDesEquipes.pdf"));

    doc.open();
    //Image image = Image.getInstance("C:\\Users\\drwhoo\\Desktop\\Projet3eme\\JavaApplication\\src\\HolidaysHiatus\\images\\logo.png");

    //document.add(new Paragraph("test\n  test"));
   // doc.add(image);
    doc.add(new Paragraph("   "));
    doc.add(new Paragraph(" ***************************************** Liste Des Equipes ***************************************** "));
    doc.add(new Paragraph("   "));

    PdfPTable table = new PdfPTable(1);
    table.setWidthPercentage(50);
    PdfPCell cell;

    cell = new PdfPCell(new Phrase("Nom des équipes", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);

    table.addCell(cell);

//    cell = new PdfPCell(new Phrase("Classement", FontFactory.getFont("Comic Sans MS", 15)));
//    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//
//    table.addCell(cell);

    while (rs.next()) {

        Equipe e = new Equipe();
      //  e.setId(rs.getInt("id"));
        e.setNom(rs.getString("nom"));
       // e.setClassement(rs.getInt("classement"));
       
      
        cell = new PdfPCell(new Phrase(e.getNom(), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        table.addCell(cell);
        
        
//        cell = new PdfPCell(new Phrase(e.getClassement()));
//        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//
//        table.addCell(cell);
        
        
        //Image image1 = Image.getInstance("C:\\Users\\drwhoo\\Desktop\\Projet3eme\\SymfonyApplication\\public\\uploads\\" + c.getLien_icon());
        //PdfPCell cell1 = new PdfPCell(image1, true);

      //  table.addCell(cell1);

    }

    doc.add(table);
    doc.close();
    Desktop.getDesktop().open(new File("./ListeDesEquipes.pdf"));
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
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
    private void updateEquipe(MouseEvent event) {
        
        
        Equipe clickedEquipe = equipeTable.getSelectionModel().getSelectedItem();
        
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");
                 
        
        
        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){
                
        try{
             mc=MaConnexion.getInstance().getCnx();
              String value1 = idEq.getText();
             String value2 = nomEq.getText();
             String value3 = classementEq.getText();
             
             String sql = "update equipe set id = '"+value1+"', nom = '"+value2+"', classement='"+value3+"' where id ='"+value1+"'";
             ste=mc.prepareStatement(sql);
             ste.execute();
            JOptionPane.showMessageDialog(null, "Equipe modifié");
        }catch(Exception e){
               JOptionPane.showMessageDialog(null,e);

        }
        }else{
            
             idEq.setText(null);
          nomEq.setText(null);
        classementEq.setText(null);
        
            
        }
        
        
        refresh();
    }

    @FXML
    private void deleteEquipe(MouseEvent event) throws SQLException {
        
        
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");
                 
        
        
        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){
        
        
        
        
        mc=MaConnexion.getInstance().getCnx();
         String sql = "delete from equipe where id = ?";
            ste=mc.prepareStatement(sql);
            ste.setString(1, idEq.getText());
            ste.execute();
            JOptionPane.showMessageDialog(null, "Equipe supprimé" );
        
            refresh();
            
        }else{
            
            
             idEq.setText(null);
          nomEq.setText(null);
        classementEq.setText(null);
        
            
        }
        
    }

    @FXML
    private void getSelected(MouseEvent event) {
        
        Equipe clickedEquipe = equipeTable.getSelectionModel().getSelectedItem();
         idEq.setText(String.valueOf(clickedEquipe.getId()));
        nomEq.setText(String.valueOf(clickedEquipe.getNom()));
        classementEq.setText(String.valueOf(clickedEquipe.getClassement()));
     
    }
    
    
    public void refresh(){
        
         equipeList.clear();
       
          
          mc=MaConnexion.getInstance().getCnx();

        equipeList = FXCollections.observableArrayList();
        
        String sql="select * from equipe order by classement ASC";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Equipe e = new Equipe();
                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("nom"));
                e.setClassement(rs.getInt("classement"));
                equipeList.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         equipeTable.setItems(equipeList);
        
        
        
    }

    @FXML
    private void refreshChamp(MouseEvent event) {
        
        
          idEq.setText(null);
          nomEq.setText(null);
        classementEq.setText(null);
        
    }

    
    
    
        private void rechercheEq() {
        
//        
//         idEquipe.setCellValueFactory(new PropertyValueFactory<Equipe, Integer>("id"));
//        NomEquipe.setCellValueFactory(new PropertyValueFactory<Equipe, String>("nom"));
//        ClassementEquipe.setCellValueFactory(new PropertyValueFactory<Equipe, String>("classement"));
//        
        FilteredList<Equipe>filteredData = new FilteredList<>(equipeList, b->true);
        
     //   Equipe equipe = new Equipe();
        search.textProperty().addListener((observable, oldValue, newValue)->{
            
            filteredData.setPredicate(equipe->{
                
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if(equipe.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }else if(String.valueOf(equipe.getClassement()).indexOf(lowerCaseFilter) != -1){
                    return true;
                }else{
                return false;
                }
                
                
            });
            
            
            
            
        });
        
        SortedList<Equipe>sortedData = new SortedList<>(filteredData);
        
        sortedData.comparatorProperty().bind(equipeTable.comparatorProperty());
        
        equipeTable.setItems(sortedData);
        
        
    }

   
    
    
        
        
   
        
        
        
    
}
