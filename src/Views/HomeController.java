/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Views.AuthentificationUtilisateurController;
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
public class HomeController implements Initializable {

    @FXML
    private Button utilisateur;
    @FXML
    private Button equipe;
    @FXML
    private Button Joueur;
    @FXML
    private Button livraison;
    @FXML
    private Button article;
    @FXML
    private Button produit;
    @FXML
    private Label UserName;
    @FXML
    private Label Role;
    @FXML
    private Button ticket;
    @FXML
    private Button match;
    @FXML
    private ImageView logout;
    @FXML
    private Button close;
    @FXML
    private Button livreur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
//         UserName.setText(Userconnected.getPrenom()+" "+Userconnected.getNom());
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
               Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
           }
       }

    @FXML
    private void GotoGestionUtilisateur(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
                           Parent parent = FXMLLoader.load(getClass().getResource("Home.fxml"));
                           Scene scene = new Scene(parent);
                               //    primaryStage.initStyle(StageStyle.UNDECORATED);

                           //primaryStage.setTitle("Dashbord");
                           primaryStage.setScene(scene);
                           primaryStage.show();
        GotoFXML("gestionutilisateur", "TuniKoora",event);
    }

    @FXML
    private void GotoEventEquipe(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
                           Parent parent = FXMLLoader.load(getClass().getResource("Home.fxml"));
                           Scene scene = new Scene(parent);
                               //    primaryStage.initStyle(StageStyle.UNDECORATED);

                           //primaryStage.setTitle("Dashbord");
                           primaryStage.setScene(scene);
                           primaryStage.show();
         GotoFXML("gestionEquipeInterface","TuniKoora",event);
//         Parent parent = FXMLLoader.load(getClass().getResource("gestionEquipeInterface.fxml"));
//         Scene scene = new Scene(parent);
//         Stage primaryStage = new Stage();
//          primaryStage.setTitle("Tunikoora");
//     
//        primaryStage.setScene(scene);
//          primaryStage.initStyle(StageStyle.UNDECORATED);
//        primaryStage.show();

    }

    @FXML
    private void GotoGestJoueur(ActionEvent event) throws IOException {
         Stage primaryStage = new Stage();
                           Parent parent = FXMLLoader.load(getClass().getResource("Home.fxml"));
                           Scene scene = new Scene(parent);
                               //    primaryStage.initStyle(StageStyle.UNDECORATED);

                           //primaryStage.setTitle("Dashbord");
                           primaryStage.setScene(scene);
                           primaryStage.show();
         GotoFXML("gestionJoueurInterface","TuniKoora",event);
    }

    @FXML
    private void GotoGesLivraison(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
                           Parent parent = FXMLLoader.load(getClass().getResource("Home.fxml"));
                           Scene scene = new Scene(parent);
                               //    primaryStage.initStyle(StageStyle.UNDECORATED);

                           //primaryStage.setTitle("Dashbord");
                           primaryStage.setScene(scene);
                           primaryStage.show();
         GotoFXML("GestionLivraisonInterface", "TuniKoora",event);
    }

    @FXML
    private void GotoGestArticle(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
                           Parent parent = FXMLLoader.load(getClass().getResource("Home.fxml"));
                           Scene scene = new Scene(parent);
                               //    primaryStage.initStyle(StageStyle.UNDECORATED);

                           //primaryStage.setTitle("Dashbord");
                           primaryStage.setScene(scene);
                           primaryStage.show();
         GotoFXML("GestionAdminArticleInterface", "TuniKoora",event);
    }

   
    @FXML
    private void gototicket(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
                           Parent parent = FXMLLoader.load(getClass().getResource("Home.fxml"));
                           Scene scene = new Scene(parent);
                               //    primaryStage.initStyle(StageStyle.UNDECORATED);

                           //primaryStage.setTitle("Dashbord");
                           primaryStage.setScene(scene);
                           primaryStage.show();
        GotoFXML("ticketG", "TuniKoora",event);
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
    private void gotoumatch(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
                           Parent parent = FXMLLoader.load(getClass().getResource("Home.fxml"));
                           Scene scene = new Scene(parent);
                               //    primaryStage.initStyle(StageStyle.UNDECORATED);

                           //primaryStage.setTitle("Dashbord");
                           primaryStage.setScene(scene);
                           primaryStage.show();
        GotoFXML("matchInterface", "TuniKoora",event);
    }

    @FXML
    private void GotoGesProduit(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
                           Parent parent = FXMLLoader.load(getClass().getResource("Home.fxml"));
                           Scene scene = new Scene(parent);
                               //    primaryStage.initStyle(StageStyle.UNDECORATED);

                           //primaryStage.setTitle("Dashbord");
                           primaryStage.setScene(scene);
                           primaryStage.show();
         GotoFXML("produitG", "TuniKoora",event);
    }

    @FXML
    private void closeButton(ActionEvent event) throws IOException {
        Stage stage = (Stage) close.getScene().getWindow();
                           stage.close();

//                           Stage primaryStage = new Stage();
//                           Parent parent = FXMLLoader.load(getClass().getResource("AuthentificationUtilisateur.fxml"));
//                           Scene scene = new Scene(parent);
//                                   primaryStage.initStyle(StageStyle.UNDECORATED);
//
//                           //primaryStage.setTitle("Dashbord");
//                           primaryStage.setScene(scene);
//                           primaryStage.show();
    }

    @FXML
    private void GotoGesLivreur(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
                           Parent parent = FXMLLoader.load(getClass().getResource("Home.fxml"));
                           Scene scene = new Scene(parent);
                               //    primaryStage.initStyle(StageStyle.UNDECORATED);

                           //primaryStage.setTitle("Dashbord");
                           primaryStage.setScene(scene);
                           primaryStage.show();
         GotoFXML("GestionLivreurInterface", "TuniKoora",event);
    }
    
     
    
}
