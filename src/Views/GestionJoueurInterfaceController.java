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
import javafx.scene.control.Alert;
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
    private TableColumn<Joueur, Integer> idJoueur;
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
        

        
         idJoueur.setCellValueFactory(new PropertyValueFactory<Joueur, Integer>("id"));
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
    }

    @FXML
    private void print(MouseEvent event) {
    }

    @FXML
    private void addJ(MouseEvent event) {
               
 
          // int id = Integer.parseInt(idJ.getText());
           String nom = nomJ.getText();
           String prenom = prenomJ.getText();
           String poste = posteJ.getText();
           int tel = Integer.parseInt(telJ.getText());
            int equipe_id = Integer.valueOf(eq1.getEquipeId(comboBox.getSelectionModel().getSelectedItem().toString()));
            
          //  System.out.println(equipe_id);



          if (nom.isEmpty() || prenom.isEmpty() || poste.isEmpty()){
              
               Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText("ERROR");
             alert.setContentText("Insérer toutes les informations avant de valider l'insertion");
             alert.showAndWait();
              
              
          }
          else{
              
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

    @FXML
    private void updateJoueur(MouseEvent event) {
        
      // int equipe_id = Integer.valueOf(eq1.getEquipeId(comboBox.getSelectionModel().getSelectedItem().toString()));

          Joueur clickedJoueur = JoueurTable.getSelectionModel().getSelectedItem();
        
        
        try{
             mc=MaConnexion.getInstance().getCnx();
              String value1 = idJ.getText();
             String value2 = nomJ.getText();
             String value3 = prenomJ.getText();
             String value4 = posteJ.getText();
             String value5 = telJ.getText();
           //  String value6 = eq1.getEquipeId(comboBox.getSelectionModel().getSelectedItem().toString());
             
          //   String sql = "update joueur set id = '"+value1+"', nom = '"+value2+"', prenom='"+value3+"' , poste='"+value4+"', tel='"+value5+"', equipe_id='"+value6+"'  where id ='"+value1+"'";
           //  ste=mc.prepareStatement(sql);
             ste.execute();
            JOptionPane.showMessageDialog(null, "Equipe modifié");
        }catch(Exception e){
               JOptionPane.showMessageDialog(null,e);

        }
        
        
        refresh();
        
        
    }

    @FXML
    private void deleteJoueur(MouseEvent event) throws SQLException {
        
           mc=MaConnexion.getInstance().getCnx();
         String sql = "delete from joueur where id = ?";
            ste=mc.prepareStatement(sql);
            ste.setString(1, idJ.getText());
            ste.execute();
            JOptionPane.showMessageDialog(null, "Joueur supprimé" );
        
            refresh();
        
        
        
        
        
    }

    @FXML
    private void rechercheEq(MouseEvent event) {
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
        
        
        
    }
    
    
}
