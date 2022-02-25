/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableView;

import Controllers.EquipeController;
import Models.Equipe;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author mdhah
 */
public class AddStudentController implements Initializable {

    @FXML
    private TextField nom_equipe;
    @FXML
    private TextField logo_equipe;
    @FXML
    private TextField classement_equipe;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void save(MouseEvent event) {
    }}
//         
//         String nom = nom_equipe.getText();
//        String classement = classement_equipe.getText();
//        String logo = logo_equipe.getText();
//      
//        
//        
//        
//        if (nom.isEmpty() || classement.isEmpty() || logo.isEmpty()) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setHeaderText(null);
//            alert.setContentText("Please Fill All DATA");
//            alert.showAndWait();
//        
//        
//    }else{
//            Equipe e=new Equipe(1,"hfbffhg",1, "logo2.png");
//            EquipeController ec = new EquipeController();
//            ec.ajouterEquipe(e);
//        }
//    }
//}

  


   
