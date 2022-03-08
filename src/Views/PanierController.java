/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;


import Controllers.panier1Controller;
import Models.Ticket;
import Models.panier;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;
import tools.MaConnexion;


/**
 * FXML Controller class
 *
 * @author CHAOUCH KHALIL
 */
public class PanierController implements Initializable {

    @FXML
    private TableView<Ticket> tabpanier;
    @FXML
    private TableColumn<?, ?> idproduit;
    @FXML
    private TableColumn<Ticket, Integer> Pprix;
    @FXML
    private TableColumn<?, ?> Pusr;
    @FXML
    private TextField search;
    @FXML
    private TableColumn<Ticket, String> eqA;
    @FXML
    private TableColumn<Ticket,String> eqb;
 Connection mc;
    PreparedStatement ste;
    int index = -1;
    
     ObservableList<Ticket>panierList;
       
       
        ObservableList<panier>listpanierSearch;
    @FXML
    private TextField idpan;
    @FXML
    private Text summ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            mc=MaConnexion.getInstance().getCnx();
            
            panierList = FXCollections.observableArrayList();
            
            String sql="SELECT p.produit_id , t.* FROM ticket AS t INNER JOIN panier AS p WHERE p.produit_id = t.id";
            try {
                ste=mc.prepareStatement(sql);
                ResultSet rs=ste.executeQuery();
                while(rs.next()){
                    
                    Ticket t = new Ticket();
                    t.setId(rs.getInt("id"));
                    t.setEquipeA(rs.getString("equipeA"));
                    t.setEquipeB(rs.getString("equipeB"));
                    t.setPrix(rs.getInt("prix"));
                    panierList.add(t);
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
            
            
            
            
            eqA.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEquipeA()));
            eqb.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEquipeB()));
            Pprix.setCellValueFactory(new PropertyValueFactory<Ticket,Integer>("prix"));
//         Gimage.setCellValueFactory(new PropertyValueFactory<Produit,String>("image"));
//        Gdescp.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescription()));
//        //NomEquipeB.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getequipeB()));
//       // Gdescp.setCellValueFactory(new PropertyValueFactory<Produit,String>("description"));
//         Pusr.setCellValueFactory(new PropertyValueFactory<Produit,Integer>("user_id"));
tabpanier.setItems(panierList);
getSum();
        } catch (SQLException ex) {
            Logger.getLogger(PanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void getSelected(MouseEvent event) {
        
         Ticket clickedTickte = tabpanier.getSelectionModel().getSelectedItem();
         idpan.setText(String.valueOf(clickedTickte.getId()));
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
        
        
        mc=MaConnexion.getInstance().getCnx();
         String sql = "delete from panier where produit_id = ?";
            ste=mc.prepareStatement(sql);
            ste.setString(1, idpan.getText());
            ste.execute();
            JOptionPane.showMessageDialog(null, "ticket supprimé" );
        
        }
        
    
    }

    
    
    
    
     public String getSum() throws SQLException
    {
        String somme="";
      Connection mc = MaConnexion.getInstance().getCnx();

      //  panierList = FXCollections.observableArrayList();
        
        String sql="SELECT p.produit_id, SUM(prix) FROM ticket AS t INNER JOIN panier AS p WHERE p.produit_id = t.id";
      
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
        
        
          
            mail1.sendMail1("mohamedkhalil.chaouch@gmail.com");
          
        
    }
 
//    public int prixtot(){
//        int prtot=0;
//    mc=MaConnexion.getInstance().getCnx();
//        
//        panierList = FXCollections.observableArrayList();
//        
//        String sql="SELECT p.produit_id, SUM(prix) FROM ticket AS t INNER JOIN panier AS p WHERE p.produit_id = t.id";
//        try {
//            ste=mc.prepareStatement(sql);
//            ResultSet rs=ste.executeQuery();
//            while(rs.next()){
//               
//                //Ticket t = new Ticket();
////                prtot = rs.getInt("prix");
//                //panierList.add(t);
//                System.out.println(rs);
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//     
//   return prtot ;
//}
}