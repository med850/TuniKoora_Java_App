/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;


import Controllers.LivreurController;
import Models.Livreur;
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
 *
 * @author jerby
 */
public class GestionLivreurInterfaceController {

    @FXML
    private TableView<Livreur> LivreurTable;
    
    ObservableList<Livreur>livreurList;
    
    ObservableList<Livreur>listLivreurForSearch;
    
    @FXML
    private TableColumn<Livreur, Integer> idLivreur;
    @FXML
    private TableColumn<Livreur, String> nomLivreur;
    @FXML
    private TableColumn<Livreur, String> prenomLivreur;
    @FXML
    private TableColumn<Livreur, Integer> telLivreur;
    @FXML
    private TableColumn<Livreur, Integer> Livraison_ID;
    @FXML
    private TextField nomLiv;
    @FXML
    private TextField prenomLiv;
    @FXML
    private TextField telLiv;
    @FXML
    private TextField Livraison_id;
    @FXML
    private TextField search;
    
    Connection mc;
    PreparedStatement ste;
    
    
    /**
     * Initializes the controller class.
     */
    
    
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        mc=MaConnexion.getInstance().getCnx();

        livreurList = FXCollections.observableArrayList();
        
        String sql="select * from livreur ";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Livreur l = new Livreur();
                l.setId(rs.getInt("id"));
                l.setNom(rs.getString("nom"));
                l.setPrenom(rs.getString("prenom"));
                l.setTel(rs.getInt("tel"));
                l.setLivraisonID(rs.getInt("livraison_id"));
                livreurList.add(l);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        

        
        idLivreur.setCellValueFactory(new PropertyValueFactory<Livreur, Integer>("id"));
        nomLivreur.setCellValueFactory(new PropertyValueFactory<Livreur, String>("nom"));
        prenomLivreur.setCellValueFactory(new PropertyValueFactory<Livreur, String>("prenom"));
        telLivreur.setCellValueFactory(new PropertyValueFactory<Livreur, Integer>("tel"));
        Livraison_ID.setCellValueFactory(new PropertyValueFactory<Livreur, Integer>("livraison_id"));




        
        LivreurTable.setItems(livreurList);


        
        
        
       // rechercheLiv();
        
        
        
        
        
        
        
        
    }

    @FXML
    private void getSelected(MouseEvent event) {
        
        Livreur clickedLivreur = LivreurTable.getSelectionModel().getSelectedItem();
        nomLiv.setText(String.valueOf(clickedLivreur.getNom()));
        prenomLiv.setText(String.valueOf(clickedLivreur.getPrenom()));
        telLiv.setText(String.valueOf(clickedLivreur.getTel()));
        Livraison_id.setText(String.valueOf(clickedLivreur.getLivraisonID())) ;
    }

    @FXML
    private void refreshChamp(MouseEvent event) {
        
        nomLiv.setText(null);
        prenomLiv.setText(null);
        telLiv.setText(null);
        Livraison_id.setText(null);
    
    }

    @FXML
    private void print(MouseEvent event) {
    }

    @FXML
    private void addLivreur(MouseEvent event) {
        
        Livreur li= new Livreur();
        
        LivreurController lic = new LivreurController();
        String nom = nomLiv.getText();
        String prenom = prenomLiv.getText();
        String tel = telLiv.getText();
        int livraisonid = Integer.parseInt(Livraison_id.getText());
           
        
        
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
             
             
             lic.ajouterLivreur(li);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setHeaderText("Succes");
             alert.setContentText("Livreur Ajoutée!");
                alert.showAndWait();             
        
        
         }
         
          refresh();
          
          
        nomLiv.setText(null);
        prenomLiv.setText(null);
        telLiv.setText(null);
        Livraison_id.setText(null);
        
    }

    @FXML
    private void updateLivreur(MouseEvent event) {
        
        Livreur clickedLivreur = LivreurTable.getSelectionModel().getSelectedItem();
        
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");
                 
        
        
        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){
                
        try{
             mc=MaConnexion.getInstance().getCnx();
              String value1 = nomLiv.getText();
             String value2 = prenomLiv.getText();
             String value3 = telLiv.getText();
             String value4 = Livraison_id.getText();
             
             String sql = "update livreur set nom = '"+value1+"', prenom = '"+value2+"', tel='"+value3+"', livraison_id='"+value4+"' where nom ='"+value1+"'";
             ste=mc.prepareStatement(sql);
             ste.execute();
            JOptionPane.showMessageDialog(null, "Livreur modifié");
        }catch(Exception e){
               JOptionPane.showMessageDialog(null,e);

        }
        }else{
            
        nomLiv.setText(null);
        prenomLiv.setText(null);
        telLiv.setText(null);
        Livraison_id.setText(null);
        
            
        }
        
        
        refresh();
        
    }

    @FXML
    private void deleteLivreur(MouseEvent event) throws SQLException {
        
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");
                 
        
        
        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){
        
        
        
        
        mc=MaConnexion.getInstance().getCnx();
         String sql = "delete from livreur where nom = ?";
            ste=mc.prepareStatement(sql);
            ste.setString(1, nomLiv.getText());
            ste.execute();
            JOptionPane.showMessageDialog(null, "Livreur supprimé" );
        
            refresh();
            
        }else{
            
            
            nomLiv.setText(null);
            prenomLiv.setText(null);
            telLiv.setText(null);
            Livraison_id.setText(null);
        
            
        }
        
       
    }
    
    
    public void refresh(){
        
         livreurList.clear();
       
          
          mc=MaConnexion.getInstance().getCnx();

        livreurList = FXCollections.observableArrayList();
        
        String sql="select * from livreur";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Livreur l = new Livreur();
                l.setNom(rs.getString("nom"));
                l.setPrenom(rs.getString("prenom"));
                l.setTel(rs.getInt("tel"));
                l.setLivraisonID(rs.getInt("livraison_id"));
                livreurList.add(l);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         LivreurTable.setItems(livreurList);
        
        
        
    }
    

    @FXML
    private void rechercheLiv(MouseEvent event) {
        
          //         idEquipe.setCellValueFactory(new PropertyValueFactory<Equipe, Integer>("id"));
//        NomEquipe.setCellValueFactory(new PropertyValueFactory<Equipe, String>("nom"));
//        ClassementEquipe.setCellValueFactory(new PropertyValueFactory<Equipe, String>("classement"));
//        
        FilteredList<Livreur>filteredData = new FilteredList<>(livreurList, b->true);
        
     //   Equipe equipe = new Equipe();
        search.textProperty().addListener((observable, oldValue, newValue)->{
            
            filteredData.setPredicate(livraison->{
                
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if(livraison.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }else if(String.valueOf(livraison.getPrenom()).indexOf(lowerCaseFilter) != -1){
                    return true;
                }else{
                return false;
                }
                
                
            });
            
            
            
            
        });
        
        SortedList<Livreur>sortedData = new SortedList<>(filteredData);
        
        sortedData.comparatorProperty().bind(LivreurTable.comparatorProperty());
        
        LivreurTable.setItems(sortedData);
        
    }
    
}
