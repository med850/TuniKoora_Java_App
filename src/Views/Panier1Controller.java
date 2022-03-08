/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Models.Produit;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;
import tools.MaConnexion;

/**
 * FXML Controller class
 *
 * @author CHAOUCH KHALIL
 */
public class Panier1Controller implements Initializable {

    @FXML
    private TableView<Produit> tabpanier;
    @FXML
    private TableColumn<?, ?> idproduit;
    @FXML
    private TableColumn<Produit, String> name;
    @FXML
    private TableColumn<Produit, Integer> qqt;
    @FXML
    private TableColumn<Produit, Integer> Pprix;
    @FXML
    private TableColumn<Produit, String> desc;
    @FXML
    private TextField search;
    @FXML
    private TextField idpan;

    ObservableList<Produit>panierList;
    @FXML
    private Text sum;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection mc = MaConnexion.getInstance().getCnx();

        panierList = FXCollections.observableArrayList();
        
        String sql="SELECT p.produit_id , t.* FROM produit AS t INNER JOIN panierp AS p WHERE p.produit_id = t.id";
        try {
            PreparedStatement ste = mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
               
                Produit t = new Produit();
 t.setId(rs.getInt("id"));
                t.setNom(rs.getString("nom"));
                t.setDescription(rs.getString("description"));
                t.setPrix(rs.getInt("prix"));
                t.setQte(rs.getInt("qte"));
                panierList.add(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        

        
         
        name.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNom()));
        desc.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescription()));
        Pprix.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("prix"));
        qqt.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("qte"));
//         Gimage.setCellValueFactory(new PropertyValueFactory<Produit,String>("image"));
//        Gdescp.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescription()));
//        //NomEquipeB.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getequipeB()));
//       // Gdescp.setCellValueFactory(new PropertyValueFactory<Produit,String>("description"));
//         Pusr.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("user_id"));
        tabpanier.setItems(panierList);

        try {
            getSum();
        } catch (SQLException ex) {
            Logger.getLogger(Panier1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    

    @FXML
    private void getSelected(MouseEvent event) {
         Produit clickedprod = tabpanier.getSelectionModel().getSelectedItem();
         idpan.setText(String.valueOf(clickedprod.getId()));
    }

    @FXML
    private void rechercheEq(MouseEvent event) {
    }

    @FXML
    private void deleteAchat(MouseEvent event) throws SQLException {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");



        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){ 
        
        
                Connection mc = MaConnexion.getInstance().getCnx();
         String sql = "delete from panierp where produit_id = ?";
                PreparedStatement ste = mc.prepareStatement(sql);
            ste.setString(1, idpan.getText());
            ste.execute();
            JOptionPane.showMessageDialog(null, "prod supprimé" );
        
        }
    }

        public String getSum() throws SQLException
    {
        String somme="";
      Connection mc = MaConnexion.getInstance().getCnx();

      //  panierList = FXCollections.observableArrayList();
        
        String sql="SELECT p.produit_id, SUM(prix) FROM produit AS t INNER JOIN panierp AS p WHERE p.produit_id = t.id";
      
            PreparedStatement ste = mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                somme = rs.getString("SUM(prix)");
              // sum.setText(somme);

    }
       
          
        return somme;   
    }
    
    @FXML
    private void valider(MouseEvent event) throws Exception {
         mail.sendMail("mohamedkhalil.chaouch@gmail.com");
    }
    
}
