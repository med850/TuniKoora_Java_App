/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author aroua
 */
public class GestionutilisateurController implements Initializable {

    @FXML
    private TextField txt_cin;
    @FXML
    private TextField txt_nom;
    @FXML
    private TextField txt_prenom;
    @FXML
    private TextField txt_tel;
    @FXML
    private TextField txt_id;
    @FXML
    private TextField txt_email;
    @FXML
    private TextField txt_mdp;
    @FXML
    private TextField txt_mdpc;
    @FXML
    private TextField txt_type;
    @FXML
    private TableView<?> table_users;
    @FXML
    private TableColumn<?, ?> col_id;
    @FXML
    private TableColumn<?, ?> col_cin;
    @FXML
    private TableColumn<?, ?> col_nom;
    @FXML
    private TableColumn<?, ?> col_prenom;
    @FXML
    private TableColumn<?, ?> col_tel;
    @FXML
    private TableColumn<?, ?> col_email;
    @FXML
    private TableColumn<?, ?> col_mdp;
    @FXML
    private TableColumn<?, ?> col_mdpc;
    @FXML
    private TableColumn<?, ?> col_type;
    @FXML
    private TextField filterField;
    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Add_users(ActionEvent event) {
    }

    @FXML
    private void Edit(ActionEvent event) {
    }

    @FXML
    private void Delete(ActionEvent event) {
    }

    @FXML
    private void search_user(KeyEvent event) {
    }

    @FXML
    private void getSelected(MouseEvent event) {
    }
    
}
