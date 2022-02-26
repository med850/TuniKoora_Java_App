/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.EquipeController;
import Models.Equipe;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
    int index = -1;
 
    @FXML
    private TableView<Equipe> equipeTable;
    
       ObservableList<Equipe>equipeList;
       
       
        ObservableList<Equipe>listEquipeForSearch;

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


        
        
    }    

    @FXML
    private void addEquipe(MouseEvent event) throws IOException {
        
         String nom = nomEq.getText();
        int classement = Integer.parseInt(classementEq.getText());
           
        
        
         if (nom.isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText("ERROR");
             alert.setContentText("Insérer toutes les informations avant de valider l'insertion");
             alert.showAndWait();
               
             
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
        
    }
    



    @FXML
    private void print(MouseEvent event) {
    }

    @FXML
    private void updateEquipe(MouseEvent event) {
        
        Equipe clickedEquipe = equipeTable.getSelectionModel().getSelectedItem();
        
        
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
        
        
        refresh();
    }

    @FXML
    private void deleteEquipe(MouseEvent event) throws SQLException {
         mc=MaConnexion.getInstance().getCnx();
         String sql = "delete from equipe where id = ?";
            ste=mc.prepareStatement(sql);
            ste.setString(1, idEq.getText());
            ste.execute();
            JOptionPane.showMessageDialog(null, "Equipe supprimé" );
        
            refresh();
        
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

    @FXML
    private void rechercheEq(MouseEvent event) {
        
//        
//         idEquipe.setCellValueFactory(new PropertyValueFactory<Equipe, Integer>("id"));
//        NomEquipe.setCellValueFactory(new PropertyValueFactory<Equipe, String>("nom"));
//        ClassementEquipe.setCellValueFactory(new PropertyValueFactory<Equipe, String>("classement"));
//        
        
        
        
    }
    
    
    
}
