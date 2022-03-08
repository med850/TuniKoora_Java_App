/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.EquipeController;
import Controllers.MatchController;
import Controllers.ParticipationController;
import Models.Equipe;
import Models.Match;
import Models.Participation;
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
import javax.swing.JOptionPane;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import tools.MaConnexion;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import Models.CsvWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author arfao
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
    private TableView<PMatch> participationTable;
    @FXML
    private TableColumn<Match, String> localisation;
    @FXML
    private TableColumn<Match, String> arbitre;
    @FXML
    private TableColumn<Match, String> tour;
   
    @FXML
    private TableColumn<PMatch, Integer> id;
    @FXML
    private TableColumn<PMatch, String> equipe1;
    @FXML
    private TableColumn<PMatch, String> equipe2;

    ObservableList<PMatch>participationList;
    
      Connection mc;
    PreparedStatement ste;
    
      ObservableList<Match>matchList;
    @FXML
    private ComboBox<String> p1;
    @FXML
    private ComboBox<String> p2;
    @FXML
    private AnchorPane cs;
    @FXML
    private Button map;

    @FXML
    private void telecharger(ActionEvent event) {
    }

    @FXML
    private void Gerermap(MouseEvent event) {
    }

//    @FXML
//    private void Gerermap(MouseEvent event) {
//    }

     protected class PMatch{

        public PMatch(Integer id, String equipe1, String equipe2) {
            this.id = id;
            this.equipe1 = equipe1;
            this.equipe2 = equipe2;
        }
        public PMatch() {}
         Integer id;
         String equipe1;
         String equipe2;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getEquipe1() {
            return equipe1;
        }

        public void setEquipe1(String equipe1) {
            this.equipe1 = equipe1;
        }

        public String getEquipe2() {
            return equipe2;
        }

        public void setEquipe2(String equipe2) {
            this.equipe2 = equipe2;
        }
     }
     List<String[]> array = null;
//         public String convertToCSV(String[] data) {
////    return Stream.of(data)
////      .map(this::escapeSpecialCharacters)
////      .collect(Collectors.joining(","));
//}
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
        

        
        //idMatch.setCellValueFactory(new PropertyValueFactory<Match, Integer>("id"));
        localisation.setCellValueFactory(new PropertyValueFactory<Match, String>("localisation"));
        arbitre.setCellValueFactory(new PropertyValueFactory<Match, String>("arbitrePrincipale"));
        tour.setCellValueFactory(new PropertyValueFactory<Match, String>("tour"));

           mc=MaConnexion.getInstance().getCnx();

        participationList = FXCollections.observableArrayList();
        array=new ArrayList<String[]>();
        sql = "select p1.id, e1.nom equipe1, e2.nom equipe2, p1.equipe_id, p2.equipe_id from participation p1 left join participation p2 on p1.match_id = p2.match_id left join equipe e1 on p1.equipe_id = e1.id left join equipe e2 on p2.equipe_id = e2.id where p1.match_id = p2.match_id and p1.equipe_id <> p2.equipe_id";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                PMatch p = new PMatch();
                p.setId(rs.getInt("id"));
                p.setEquipe1(rs.getString("equipe1"));
                p.setEquipe2(rs.getString("equipe2"));
                String id = p.getId().toString();
                String eq1 = p.getEquipe1();
                String eq2 = p.getEquipe2();
                String [] ch = {id,eq1,eq2};
                array.add(ch);
                participationList.add(p);
                
                System.out.println(p.getId()+" "+p.getEquipe1());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
        id.setCellValueFactory(new PropertyValueFactory<PMatch, Integer>("id"));
        equipe1.setCellValueFactory(new PropertyValueFactory<PMatch, String>("equipe1"));
        equipe2.setCellValueFactory(new PropertyValueFactory<PMatch, String>("equipe2"));
                participationTable.setItems(participationList);

        matchTable.setItems(matchList);
           loadDataIntoChoiBox();
           loadDataIntoChoiBox2();
        
        
        
        
        
    }    

    @FXML
    private void getSelected(MouseEvent event) {
        
        
         Match clickedMatch = matchTable.getSelectionModel().getSelectedItem();
         idM.setText(String.valueOf(clickedMatch.getIdM()));
        idLoc.setText(String.valueOf(clickedMatch.getLocalisation()));
        arbitreM.setText(String.valueOf(clickedMatch.getArbitrePrincipale()));
        tourMatch.setText(String.valueOf(clickedMatch.getTour()));
        
    }

    @FXML
    private void refreshChamp(MouseEvent event) {
    }

    @FXML
    private void print(MouseEvent event) {
    }

    @FXML
    private void addMatch(MouseEvent event) {
       //  String id = idM.getText();
         String localisation = idLoc.getText();
         String arbitre = arbitreM.getText();
         String tour = tourMatch.getText();
       // int classement = Integer.parseInt(idLoc.getText());
           
        
        
         if (localisation.isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText("ERROR");
             alert.setContentText("Insérer toutes les informations avant de valider l'insertion");
             alert.showAndWait();
               
             
         }else{
             
             Match m=new Match(1,localisation,arbitre,tour);
             MatchController mc = new MatchController();
             mc.ajouterMatch(m);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setHeaderText("Succes");
             alert.setContentText("Match Ajoutée!");
                alert.showAndWait();             
        
        
         }
         
         refresh();
          idLoc.setText(null);
        arbitreM.setText(null);
        tourMatch.setText(null);
    }
//
   
    @FXML
    private void updateMatch(MouseEvent event) {
         Match clickedMatch = matchTable.getSelectionModel().getSelectedItem();
        
        
        try{
             mc=MaConnexion.getInstance().getCnx();
             String value1 = idM.getText() ;
             String value2 = idLoc.getText();
             String value3 = arbitreM.getText();
             String value4 = tourMatch.getText();

             
             String sql =  "update matchtb set id = '"+value1+"', localisation='"+value2+"', arbitrePrincipale='"+value3+"', tour='"+value4+ "'where id ='"+value1+"'" ;
             ste=mc.prepareStatement(sql);
             ste.execute();
            JOptionPane.showMessageDialog(null, "match modifié");
            
            refresh();
            
            
            
          idLoc.setText(null);
        arbitreM.setText(null);
        tourMatch.setText(null);
            
        }catch(Exception e){
               JOptionPane.showMessageDialog(null,e);

        }
        
        
        refresh();
    }

    
    
    @FXML
    private void deleteMatch(MouseEvent event) throws SQLException {
      mc=MaConnexion.getInstance().getCnx();
         String sql = "delete from matchtb where id = ?";
            ste=mc.prepareStatement(sql);
            ste.setString(1, idM.getText());
            ste.execute();
            JOptionPane.showMessageDialog(null, "match supprimé" );
            
             refresh();
          idLoc.setText(null);
        arbitreM.setText(null);
        tourMatch.setText(null);
                
    }

        
    public void refresh(){
        
        
          matchList.clear();
       
          
          mc=MaConnexion.getInstance().getCnx();

        matchList = FXCollections.observableArrayList();
        
        String sql="select * from matchtb";
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
         matchTable.setItems(matchList);
        
        
        loadDataIntoChoiBox2();
        
        
        
    }
  
    public void loadDataIntoChoiBox(){

           ObservableList<String> list = FXCollections.observableArrayList();
//         ComboBox comboBox = new ComboBox(options);
         //  comboBox.setMaxHeight(30);
            mc=MaConnexion.getInstance().getCnx();

           String sql = "select * from equipe";

              try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
               //String nom = rs.getString("nom").toString();
             list.add(rs.getString("nom"));


            }
//            ste.close();
//            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


              p2.setItems(null);
              p2.setItems(list);

       }
    
     public void loadDataIntoChoiBox2(){

           ObservableList<String> list = FXCollections.observableArrayList();
//         ComboBox comboBox = new ComboBox(options);
         //  comboBox.setMaxHeight(30);
            mc=MaConnexion.getInstance().getCnx();

           String sql = "select * from matchtb";

              try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
               //String nom = rs.getString("nom").toString();
             list.add(rs.getString("tour"));


            }
//            ste.close();
//            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


              p1.setItems(null);
              p1.setItems(list);

       }

    @FXML
    private void ajouterParticipation(MouseEvent event) {
         MatchController cc = new MatchController(); 
         EquipeController ee = new EquipeController();
        int matchId = Integer.valueOf(ee.getEquipeId(p1.getSelectionModel().getSelectedItem().toString()));
        int equipe_id = Integer.valueOf(ee.getEquipeId(p2.getSelectionModel().getSelectedItem().toString()));
        Participation p=new Participation(1,matchId,equipe_id);
             ParticipationController ec = new ParticipationController();
             ec.ajouterParticipation(p);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setHeaderText("Succes");
             alert.setContentText("participation Ajoutée!");
                alert.showAndWait();
    }
     @FXML
    private void downloadCsv(){
                String[] record = {"id", "equipe1", "equipe2"};
        String expected = "\"1\",\"apple\",\"10\",\"9.99\"";
                    CsvWriter writer = new CsvWriter();
        try {
            File f = new File("C:\\Users\\arfao\\Desktop\\test.csv");
            if( f.createNewFile())
            writer.writeToCsvFile(writer.createCsvDataSpecial(array), f);
            else             writer.writeToCsvFile(writer.createCsvDataSpecial(array), f);

//        String result = writer.convertToCsvFormat(record);
//        assertEquals(expected, result);
        } catch (IOException ex) {
            Logger.getLogger(MatchInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
        void Gerermap(ActionEvent event) throws IOException {
          Stage stage = (Stage) map.getScene().getWindow();
                            stage.close();
                            Stage primaryStage =new Stage();
                            Parent root;

                            root = FXMLLoader.load(getClass().getResource("map.fxml"));
                            Scene scene = new Scene(root);
                            primaryStage.setTitle("map");
                            primaryStage.setScene(scene);
                            primaryStage.show();

        }
    }

