/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tools.MaConnexion;


/**
 * FXML Controller class
 *
 * @author aroua
 */
public class AuthentificationUtilisateurController implements Initializable {

    @FXML
    private Label loginLabel;
    @FXML
    private Button closeButton ;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField ;
    
    Connection mc;
    PreparedStatement ste;
    
    @FXML
    public void openHome(){
        String nom = usernameTextField.getText();
        String pass = passwordTextField.getText();

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    public void closeButtonOnAction(ActionEvent e){
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
        
    }
    public void loginButtonOnAction(ActionEvent e) throws IOException{
        if (usernameTextField.getText().isEmpty() == false && passwordTextField.getText().isEmpty() == false){
                    //loginLabel.setText("Essayer de connecter");
     validateLogin();
        } else{
                    loginLabel.setText("SVP enter les données");

        }
        Stage primaryStage = new Stage();
         Parent parent = FXMLLoader.load(getClass().getResource("Home.fxml"));
         Scene scene = new Scene(parent);

        
//        
//       Parent parent = FXMLLoader.load(getClass().getResource("gestionEquipeInterface.fxml"));
//       Scene scene = new Scene(parent);

////
//        Parent parent = FXMLLoader.load(getClass().getResource("gestionJoueurInterface.fxml"));
//         Scene scene = new Scene(parent);



      // scene.getStylesheets().add(getClass().getResource("ControlPanel.css").toExternalForm());
      // primaryStage.initStyle(StageStyle.UTILITY);
//        primaryStage.setTitle("Gestion des équipes");

        primaryStage.setTitle("Dashbord");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        
    }
        public void validateLogin(){
            mc=MaConnexion.getInstance().getCnx();
                String verifyLogin ="SELECT count(1) FROM users WHERE id ='"+usernameTextField.getText()+"' AND cin='"+passwordTextField.getText()+"'";
                try{
                    Statement statement = mc.createStatement();
                    ResultSet queryResult = statement.executeQuery(verifyLogin);
                    while (queryResult.next()){
                        if (queryResult.getInt(1)== 1){
                           loginLabel.setText("bienvenue");

                        }else{
                            loginLabel.setText("Veuillez réessayer ");
                        }
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                    
                }
        }
        
    
}
