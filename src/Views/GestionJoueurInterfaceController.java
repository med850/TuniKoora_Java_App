/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;


import Controllers.EquipeController;
import Controllers.JoueurController;
import Models.Equipe;
import Models.Joueur;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import tools.MaConnexion;

/**
 * FXML Controller class
 *
 * @author mdhah
 */
public class GestionJoueurInterfaceController implements Initializable {

    @FXML
    private TableColumn<Joueur, String> NomJoueur;
    @FXML
    private TableColumn<Joueur, String> prenomJoueur;
    @FXML
    private TableColumn<Joueur, String> posteJoueur;
    @FXML
    private TableColumn<Joueur, Integer> telJoueur;
    @FXML
    private TextField nomJ;

    @FXML
    private TextField idJ;
    @FXML
    private TextField search;
    @FXML
    private TextField prenomJ;
    @FXML
    private TextField posteJ;
    @FXML
    private TextField telJ;
    @FXML
    private TableView<Joueur> JoueurTable;

    
 EquipeController eq1 = new EquipeController();
   
    //   private static HttpURLConnection connection;
    
    
//    final ObservableList options = FXCollections.observableArrayList();
    
      Connection mc;
    PreparedStatement ste;

      ObservableList<Joueur>joueurList;
    @FXML
    private ComboBox<String> comboBox;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        idJ.setVisible(false);
          mc=MaConnexion.getInstance().getCnx();

        joueurList = FXCollections.observableArrayList();
        
      
         String sql="select * from joueur";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Joueur j = new Joueur();
                j.setId(rs.getInt("id"));
                j.setNom(rs.getString("nom"));
                j.setPrenom(rs.getString("prenom"));
                j.setPoste(rs.getString("poste"));
                j.setTel(rs.getInt("tel"));
                joueurList.add(j);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        

        
        // idJoueur.setCellValueFactory(new PropertyValueFactory<Joueur, Integer>("id"));
        NomJoueur.setCellValueFactory(new PropertyValueFactory<Joueur, String>("nom"));
          prenomJoueur.setCellValueFactory(new PropertyValueFactory<Joueur, String>("prenom"));
          posteJoueur.setCellValueFactory(new PropertyValueFactory<Joueur, String>("poste"));
        telJoueur.setCellValueFactory(new PropertyValueFactory<Joueur, Integer>("tel"));
         





        
        JoueurTable.setItems(joueurList);


        
        
    loadDataIntoChoiBox();
        
  
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
              
              
              comboBox.setItems(null);
              comboBox.setItems(list);
        
       } 
        

    @FXML
    private void getSelected(MouseEvent event) {
    
         Joueur clickedJoueur = JoueurTable.getSelectionModel().getSelectedItem();
         idJ.setText(String.valueOf(clickedJoueur.getId()));
        nomJ.setText(String.valueOf(clickedJoueur.getNom()));
        prenomJ.setText(String.valueOf(clickedJoueur.getPrenom()));
        posteJ.setText(String.valueOf(clickedJoueur.getPoste()));
        telJ.setText(String.valueOf(clickedJoueur.getTel()));
        prenomJ.setText(String.valueOf(clickedJoueur.getPrenom()));



     
    
    
    
    }

    @FXML
    private void refreshChamp(MouseEvent event) {
        
          idJ.setText(null);
          nomJ.setText(null);
          prenomJ.setText(null);
          posteJ.setText(null);
          telJ.setText(null);
        
        
    }


    @FXML
    private void addJ(MouseEvent event) throws SQLException {
               
 
          // int id = Integer.parseInt(idJ.getText());
           String nom = nomJ.getText();
           String prenom = prenomJ.getText();
           String poste = posteJ.getText();
           
           String tel1 = telJ.getText();
            int equipe_id = Integer.valueOf(eq1.getEquipeId(comboBox.getSelectionModel().getSelectedItem().toString()));
            
          //  System.out.println(equipe_id);

          
          String sql="select * from joueur where tel ='"+tel1+"'";
        ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            if(rs.next() == true){
                
                  Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText("ERROR");
             alert.setContentText("Joueur deja existe");
             alert.showAndWait();
             
               nomJ.setText(null);
          prenomJ.setText(null);
        posteJ.setText(null);
        telJ.setText(null);
                
            }else{
          
          
          
          
            if(tel1.length()!=8){
            
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText("ERROR");
             alert.setContentText("La taile de votre numéro doit égale 8");
             alert.showAndWait();}
        
         else if(!tel1.matches("^[0-9]*$")){
            
              Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText("ERROR");
             alert.setContentText("Tel doit contenir que des nombres");
             alert.showAndWait();
            
            
        }
          

         else if (nom.isEmpty() || prenom.isEmpty() || poste.isEmpty() ||tel1.isEmpty()){
              
               Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText("ERROR");
             alert.setContentText("Insérer toutes les informations avant de valider l'insertion");
             alert.showAndWait();
              
              
          }
          else{
              int tel = Integer.parseInt(telJ.getText());
               Joueur j=new Joueur(2,nom,prenom,poste,tel,equipe_id);
             JoueurController ec = new JoueurController();
             ec.ajouterJoueur(j);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setHeaderText("Succes");
             alert.setContentText("Joueur Ajoutée!");
                alert.showAndWait();   
              
          }
        
          
             refresh();
          
          idJ.setText(null);
          nomJ.setText(null);
          prenomJ.setText(null);
          posteJ.setText(null);
          telJ.setText(null);
          
          
          
            } 
         
        
    }

    @FXML
    private void updateJoueur(MouseEvent event) throws SQLException {
        
      // int equipe_id = Integer.valueOf(eq1.getEquipeId(comboBox.getSelectionModel().getSelectedItem().toString()));

      
      
      
      
          Joueur clickedJoueur = JoueurTable.getSelectionModel().getSelectedItem();
        
        
          
            mc=MaConnexion.getInstance().getCnx();
         String sql = "delete from joueur where id = ?";
            ste=mc.prepareStatement(sql);
            ste.setString(1, idJ.getText());
            ste.execute();
           // JOptionPane.showMessageDialog(null, "Joueur supprimé" );
        
          
            
            String nom = nomJ.getText();
           String prenom = prenomJ.getText();
           String poste = posteJ.getText();
            String tel1 = telJ.getText();

         
            int equipe_id = Integer.valueOf(eq1.getEquipeId(comboBox.getSelectionModel().getSelectedItem().toString()));
            
          //  System.out.println(equipe_id);

          
          
           if(tel1.length()!=8){
            
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText("ERROR");
             alert.setContentText("La taile de votre numéro doit égale 8");
             alert.showAndWait();}
        
         else if(!tel1.matches("^[0-9]*$")){
            
              Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText("ERROR");
             alert.setContentText("Tel doit contenir que des nombres");
             alert.showAndWait();
            
            
        }
          

         else if (nom.isEmpty() || prenom.isEmpty() || poste.isEmpty() ||tel1.isEmpty()){
              
               Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText("ERROR");
             alert.setContentText("Il existe un champs vide");
             alert.showAndWait();
              
              
          }


          else {
                int tel = Integer.parseInt(telJ.getText());
               Joueur j=new Joueur(2,nom,prenom,poste,tel,equipe_id);
             JoueurController ec = new JoueurController();
             ec.ajouterJoueur(j);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setHeaderText("Succes");
             alert.setContentText("Joueur Modifié!");
                alert.showAndWait();   
              
          }
        
          
            
            
            
            
//        try{
//             mc=MaConnexion.getInstance().getCnx();
//              String value1 = idJ.getText();
//             String value2 = nomJ.getText();
//             String value3 = prenomJ.getText();
//             String value4 = posteJ.getText();
//             String value5 = telJ.getText();
//           //  String value6 = eq1.getEquipeId(comboBox.getSelectionModel().getSelectedItem().toString());
//             
//          //   String sql = "update joueur set id = '"+value1+"', nom = '"+value2+"', prenom='"+value3+"' , poste='"+value4+"', tel='"+value5+"', equipe_id='"+value6+"'  where id ='"+value1+"'";
//           //  ste=mc.prepareStatement(sql);
//             ste.execute();
//            JOptionPane.showMessageDialog(null, "Equipe modifié");
//        }catch(Exception e){
//               JOptionPane.showMessageDialog(null,e);
//
//        }
//        
        
        refresh();
        
        
    }

    @FXML
    private void deleteJoueur(MouseEvent event) throws SQLException {
        
        
        
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");
                 
        
        
        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){
        
        
        
        
           mc=MaConnexion.getInstance().getCnx();
         String sql = "delete from joueur where id = ?";
            ste=mc.prepareStatement(sql);
            ste.setString(1, idJ.getText());
            ste.execute();
            JOptionPane.showMessageDialog(null, "Joueur supprimé" );
        
            refresh();
        
        
        }
        else{
            
              idJ.setText(null);
          nomJ.setText(null);
          prenomJ.setText(null);
          posteJ.setText(null);
          telJ.setText(null);
          
        }
        
        
    }

    
    
      public void refresh(){
        
         joueurList.clear();
       
          
          mc=MaConnexion.getInstance().getCnx();

        joueurList = FXCollections.observableArrayList();
        
        String sql="select * from joueur";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Joueur j = new Joueur();
                j.setId(rs.getInt("id"));
                j.setNom(rs.getString("nom"));
                j.setPrenom(rs.getString("prenom"));
                j.setPoste(rs.getString("poste"));
                j.setTel(rs.getInt("tel"));
                joueurList.add(j);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         JoueurTable.setItems(joueurList);
        
       // loadDataIntoChoiBox();
        
    }

    @FXML
    private void rechercheJr(MouseEvent event) {
        
          FilteredList<Joueur>filteredData = new FilteredList<>(joueurList, b->true);
        
     //   Equipe equipe = new Equipe();
        search.textProperty().addListener((observable, oldValue, newValue)->{
            
            filteredData.setPredicate(joueur->{
                
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                
                String lowerCaseFilter = newValue.toLowerCase();
                
                if(joueur.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                    
                    }else if(String.valueOf(joueur.getTel()).indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                    
                    
                    
                    
                    else{
                return false;
                }
                
                
            }); 
            
            
            
            
        });
        
        SortedList<Joueur>sortedData = new SortedList<>(filteredData);
        
        sortedData.comparatorProperty().bind(JoueurTable.comparatorProperty());
        
        JoueurTable.setItems(sortedData);
        
      
        
        
        
        
    }
    
    
}
