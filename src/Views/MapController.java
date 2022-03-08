/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;
/**
 * FXML Controller class
 *
 * @author arfao
 */
public class MapController implements Initializable {

    private WebEngine engine;
    private WebHistory history;
    private String homePage;
    private double webZoom;
    @FXML
    private WebView webView;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try{
       engine = webView.getEngine();
		homePage = "www.google.com";
              
		//textField.setText(homePage);
		webZoom = 1;
                engine.load("https://www.google.tn/maps/@34.6113892,8.7590835,6z?hl=fr");
		loadPage();
                System.out.println("https://www.google.tn/maps/@34.6113892,8.7590835,6z?hl=fr");
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }    

    @FXML
    private void loadPage() {
      //   primaryStage.setTitle("JavaFX WebView Example");

         webView = new WebView();

        //webView.getEngine().load("http://google.com");
        engine.load("https://www.google.tn/maps/@34.6113892,8.7590835,6z?hl=fr");
        VBox vBox = new VBox(webView);
        Scene scene = new Scene(vBox, 960, 600);

   //     primaryStage.setScene(scene);
     //   primaryStage.show(); 
     //   engine.load("https://www.google.tn/maps/@34.6113892,8.7590835,6z?hl=fr");
//        engine.load("http://"+textField.getText());
    }
    
}
