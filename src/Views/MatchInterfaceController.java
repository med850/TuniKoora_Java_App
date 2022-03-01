/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Models.Equipe;
import Models.Match;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import tools.MaConnexion;

/**
 * FXML Controller class
 *
 * @author mdhah
 */
public class MatchInterfaceController implements Initializable {

    @FXML
    private TextField idLoc;
    @FXML
    private TextField arbitreM;
    @FXML
    private TextField idM;
    @FXML
    private TextField search;
    @FXML
    private TextField tourMatch;
    @FXML
    private TableView<Match> matchTable;
    @FXML
    private TableColumn<Match, String> localisation;
    @FXML
    private TableColumn<Match, String> arbitre;
    @FXML
    private TableColumn<Match, String> tour;
    @FXML
    private TableColumn<Match, Integer> idMatch;

    
    
      Connection mc;
    PreparedStatement ste;
    
      ObservableList<Match>matchList;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
           mc=MaConnexion.getInstance().getCnx();

        matchList = FXCollections.observableArrayList();
        
        String sql="select * from matchtb ";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Match m = new Match();
                m.setIdM(rs.getInt("id"));
                m.setLocalisation(rs.getString("localisation"));
                m.setArbitrePrincipale(rs.getString("arbitrePrincipale"));
                m.setTour(rs.getString("tour"));
                matchList.add(m);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        

        
         idMatch.setCellValueFactory(new PropertyValueFactory<Match, Integer>("id"));
        localisation.setCellValueFactory(new PropertyValueFactory<Match, String>("localisation"));
        arbitre.setCellValueFactory(new PropertyValueFactory<Match, String>("arbitrePrincipale"));
        tour.setCellValueFactory(new PropertyValueFactory<Match, String>("tour"));





        
        matchTable.setItems(matchList);


        
        
        
        
        
        
        
        
        
        
        
    }    

    @FXML
    private void getSelected(MouseEvent event) {
    }

    @FXML
    private void refreshChamp(MouseEvent event) {
    }

    @FXML
    private void print(MouseEvent event) {
    }

    @FXML
    private void addMatch(MouseEvent event) {
    }

    @FXML
    private void updateMatch(MouseEvent event) {
    }

    @FXML
    private void deleteMatch(MouseEvent event) {
    }

    @FXML
    private void rechercheEq(MouseEvent event) {
    }
    
}
