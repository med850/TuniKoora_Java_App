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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    @FXML
    private TableColumn<Equipe, String> LogoEquipel;

    
 
    @FXML
    private TableView<Equipe> equipeTable;
    
       ObservableList<Equipe>equipeList;
//        int index = -1;
//        Connection cn = null;
//        ResultSet rs = null;
//        PreparedStatement ste = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        equipeList = FXCollections.observableArrayList();
        
         idEquipe.setCellValueFactory(new PropertyValueFactory<Equipe, Integer>("id"));
        NomEquipe.setCellValueFactory(new PropertyValueFactory<Equipe, String>("nom"));
        ClassementEquipe.setCellValueFactory(new PropertyValueFactory<Equipe, String>("classement"));
        LogoEquipel.setCellValueFactory(new PropertyValueFactory<Equipe, String>("logo"));
        
        
        EquipeController ec = new EquipeController();
        ec.afficherEquipe();
        
        equipeTable.setItems(equipeList);


        
        
    }    

    @FXML
    private void addEquipe(MouseEvent event) throws IOException {
        
    
            Parent parent = FXMLLoader.load(getClass().getResource("addEquipe.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);

           // stage.initStyle(StageStyle.UTILITY);
            stage.show();
       
        
    }
    

    @FXML
    private void refreshTable(MouseEvent event) {
    }

    @FXML
    private void print(MouseEvent event) {
    }
    
}
