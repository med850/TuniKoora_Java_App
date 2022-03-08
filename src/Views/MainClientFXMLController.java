/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Nayrouz
 */
public class MainClientFXMLController implements Initializable {
    @FXML
    private ImageView logout;
    @FXML
    private Label UserName;
    @FXML
    private Label Role;
    @FXML
    private Button review;
    @FXML
    private Button article;
    @FXML
    private Button ticket;
    @FXML
    private Button produit;
    @FXML
    private Button closebtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    

    private void GotoFXML(String vue, String title,Event aEvent) {
           try {
               Event event;
               FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(vue + ".fxml"));
               Parent root1 = (Parent) fxmlLoader.load();
               Stage stage =(Stage)((Node) aEvent.getSource()).getScene().getWindow() ;
               stage.setTitle(title);
               stage.setScene(new Scene(root1));
               stage.show();
           } catch (IOException ex) {
               Logger.getLogger(MainClientFXMLController.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
   

    @FXML
    private void Logout(MouseEvent event) throws IOException, Exception {
         Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Deconnexion");
        alert.setContentText("Voulez-vous vraiment Déconnecter?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
//        Userconnected.setId(0);
//        Userconnected.setPrenom("");
//        Userconnected.setNom("");
//        Userconnected.setEmail("");
//        Userconnected.setPassword("");
//        Userconnected.setAdresse("");
//        Userconnected.setImage("");
        FXMLLoader LOADER = new FXMLLoader(getClass().getResource("AuthentificationUtilisateur.fxml"));
        try {
            Parent root = LOADER.load();
            Scene sc = new Scene(root);
            AuthentificationUtilisateurController cntr = LOADER.getController();
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(sc);
            window.show();
        } catch (IOException ex) {
    }}}

    @FXML
    private void GotoGestionreview(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
                           Parent parent = FXMLLoader.load(getClass().getResource("MainClientFXML.fxml"));
                           Scene scene = new Scene(parent);
                                   //  primaryStage.initStyle(StageStyle.UNDECORATED);

                           //primaryStage.setTitle("Dashbord");
                           primaryStage.setScene(scene);
                           primaryStage.show();
                GotoFXML("gestionReviewInterface", "TuniKoora",event);

    }

    @FXML
    private void GotoEventarticle(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
                           Parent parent = FXMLLoader.load(getClass().getResource("MainClientFXML.fxml"));
                           Scene scene = new Scene(parent);
                                   //  primaryStage.initStyle(StageStyle.UNDECORATED);

                           //primaryStage.setTitle("Dashbord");
                           primaryStage.setScene(scene);
                           primaryStage.show();
                GotoFXML("gestionArticleInterface", "TuniKoora",event);

    }

    @FXML
    private void GotoGestticket(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
                           Parent parent = FXMLLoader.load(getClass().getResource("MainClientFXML.fxml"));
                           Scene scene = new Scene(parent);
                                   //  primaryStage.initStyle(StageStyle.UNDECORATED);

                           //primaryStage.setTitle("Dashbord");
                           primaryStage.setScene(scene);
                           primaryStage.show();
                GotoFXML("Clticket", "TuniKoora",event);

    }

    @FXML
    private void GotoGesProduit(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
                           Parent parent = FXMLLoader.load(getClass().getResource("MainClientFXML.fxml"));
                           Scene scene = new Scene(parent);
                                   //  primaryStage.initStyle(StageStyle.UNDECORATED);

                           //primaryStage.setTitle("Dashbord");
                           primaryStage.setScene(scene);
                           primaryStage.show();
                        GotoFXML("Clprod", "TuniKoora",event);

    }

    @FXML
    private void closeButton(ActionEvent event) throws IOException {
        Stage stage = (Stage) closebtn.getScene().getWindow();
                           stage.close();

                           Stage primaryStage = new Stage();
                           Parent parent = FXMLLoader.load(getClass().getResource("AuthentificationUtilisateur.fxml"));
                           Scene scene = new Scene(parent);
                                   primaryStage.initStyle(StageStyle.UNDECORATED);

                           //primaryStage.setTitle("Dashbord");
                           primaryStage.setScene(scene);
                           primaryStage.show();
    }
    
}
