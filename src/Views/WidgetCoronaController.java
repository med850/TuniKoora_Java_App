/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author mdhah
 */
public class WidgetCoronaController implements Initializable {

   

    
     public static HttpURLConnection connection;
    BufferedReader reader;
    String line;
    StringBuffer responseContent = new StringBuffer();
    @FXML
    private Text cases;
    @FXML
    private Text deaths;
    @FXML
    private Text recovered;
    @FXML
    private Text tests;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            getDataTn();
        } catch (IOException ex) {
            Logger.getLogger(WidgetCoronaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    
    
     
    
    
    public void getDataTn() throws MalformedURLException, IOException{
    URL url = new URL("https://coronavirus-19-api.herokuapp.com/countries/Tunisia");
    
    connection = (HttpURLConnection) url.openConnection();
    
    connection.setRequestMethod("GET");
    connection.setConnectTimeout(5000);
    connection.setReadTimeout(5000);
    
    int status = connection.getResponseCode();
 //   System.out.println(status);
    
    
    if(status>299){
        
        reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        while((line = reader.readLine())!=null){
            responseContent.append(line);
        }
        reader.close();
    }else{
       reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
         while((line = reader.readLine())!=null){
            responseContent.append(line);
        }
        reader.close();
    }
    
     JSONObject myResponse = new JSONObject(responseContent.toString());
    System.out.println( myResponse);
       // JSONObject rest = new JSONObject(myResponse.getJSONObject("recovered").toString());
//
   cases.setText(""+myResponse.getInt("cases"));
    deaths.setText(""+myResponse.getInt("deaths"));
     recovered.setText(""+myResponse.getInt("recovered"));
      tests.setText(""+myResponse.getInt("totalTests"));

  // System.out.println("countryName- "+myResponse.getString("country"));
    
//    System.out.println(responseContent.toString());


       // parse(responseContent.toString());

    
    }
    
    
}
