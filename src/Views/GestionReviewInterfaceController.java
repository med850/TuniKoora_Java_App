/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.ReviewController;
import Controllers.likeController;
import Models.Article;
import Models.Review;
import Models.dislike;
import Models.like;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.time.zone.ZoneRulesProvider.refresh;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import tools.MaConnexion;

/**
 * FXML Controller class
 *
 * @author wassim
 */
public class GestionReviewInterfaceController implements Initializable {

    Connection mc;
    PreparedStatement ste;
    int liked=0;
    int disliked=0;
    
    @FXML
    private TextArea userCommentaire;
    @FXML
    private Button ajouterCommentaire;
    @FXML
    private TableView<Review> commenatireTable;
     ObservableList<Review>reviewList;
     ObservableList<Article>articleList;
    @FXML
    private Button supprimererCommentaire;
    @FXML
    private Button modifierCommentaire;
    @FXML
    private TableColumn<Review, String> Cmntr;
    @FXML
    private TextArea idCmntr;
    @FXML
    private TableColumn<Review, Integer> id;
    @FXML
    private Button listArticle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      afficherReview();
      addButtonToTable();
      addButtonToTable2();
                   
    }  
void afficherReview(){
    mc=MaConnexion.getInstance().getCnx();

        reviewList = FXCollections.observableArrayList();        
        String sql="select * from review";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Review e = new Review();
                e.setIdReview(rs.getInt("id"));
                e.setcommentaire(rs.getString("commentaire"));
                reviewList.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        id.setCellValueFactory(new PropertyValueFactory<Review, Integer>("id"));       
        Cmntr.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getcommentaire()));
        commenatireTable.setItems(reviewList);
}
    @FXML
    private void addCommentaire(MouseEvent event) throws SQLException {
        String cmntr = userCommentaire.getText();
        

         if (cmntr.isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText("ERROR");
             alert.setContentText("Commenatire non disponible!!");
             alert.showAndWait();
               
             
         }
         else if (cmntr.length()>20){
              Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText("ERROR");
             alert.setContentText("La taille du commentaire doit etre < 20!");
             alert.showAndWait();
         }
         
         else {
             
             Review r=new Review(1,cmntr,5,5);
             ReviewController rc = new ReviewController();
             String sql = "select * from review where commentaire='"+cmntr+"'";
             ste=mc.prepareStatement(sql);
             ResultSet rs=ste.executeQuery();
             if(rs.next()==true){
              Alert alert = new Alert(Alert.AlertType.ERROR);
             //alert.setHeaderText("ERROR");
             alert.setContentText("Titre existe deja!");
             alert.showAndWait();
              userCommentaire.setText(null);
             }else{
             rc.ajouterReview(r);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setHeaderText("Succes");
             alert.setContentText("Commenatire Ajoutée!");
                alert.showAndWait();             
         }}
          refresh();
    }

    public void refresh() {
        reviewList.clear();
          mc=MaConnexion.getInstance().getCnx();

        reviewList = FXCollections.observableArrayList();
        
        String sql="select * from review ";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Review r = new Review();
                r.setIdReview(rs.getInt("id"));
                r.setcommentaire(rs.getString("commentaire"));
                reviewList.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         commenatireTable.setItems(reviewList);
        
    }

    @FXML
    private void selectRow(MouseEvent event) {
         Review clickReview = commenatireTable.getSelectionModel().getSelectedItem();
         idCmntr.setText(String.valueOf(clickReview.getIdReview()));
         userCommentaire.setText(String.valueOf(clickReview.getcommentaire().toString()));

    }

    @FXML
    private void updateCommentaire(MouseEvent event) {
              Review clickedEquipe = commenatireTable.getSelectionModel().getSelectedItem();
              Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");



        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){
              
        try{
             mc=MaConnexion.getInstance().getCnx();
             String value1 = idCmntr.getText();
             String value2 = userCommentaire.getText();
             
             String sql = "update review set id = '"+value1+"', commentaire = '"+value2+"'  where id ='"+value1+"' ";
             ste=mc.prepareStatement(sql);
             ste.execute();
            JOptionPane.showMessageDialog(null, "Review modifié");
        }catch(Exception e){
               JOptionPane.showMessageDialog(null,e);

        }
        refresh();
            }
        else{
              userCommentaire.setText(null);

        }
    }

    @FXML
    private void deleteCommentaire(MouseEvent event) throws SQLException {
         Review clickedEquipe = commenatireTable.getSelectionModel().getSelectedItem();
              Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");



        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){
        mc=MaConnexion.getInstance().getCnx();
         String sql = "delete from review where commentaire = ?";
            ste=mc.prepareStatement(sql);
            ste.setString(1, userCommentaire.getText());
            ste.execute();
            JOptionPane.showMessageDialog(null, "review supprimé" );
        
            refresh();
                   }
        else{
              userCommentaire.setText(null);

        }
    }

    @FXML
    private void navArticle(MouseEvent event) throws IOException {
        Stage stage = (Stage) listArticle.getScene().getWindow();
        stage.close();
        Stage primaryStage =new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("gestionArticleInterface.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Gestion des équipes");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

private void addButtonToTable()  {

        TableColumn<Review, Void> colBtn = new TableColumn("Action");
        Callback<TableColumn<Review, Void>, TableCell<Review, Void>> cellFactory = new Callback<TableColumn<Review, Void>, TableCell<Review, Void>>() {
            @Override
            public TableCell<Review, Void> call(final TableColumn<Review, Void> param) {
                final TableCell<Review, Void> cell = new TableCell<Review, Void>() {

                    private final Button btn = new Button("Like");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            if(liked == 0 && disliked == 0)
                                { String sql ="insert into likes(likes,article_id,user_id) Values(?,?,?)";
                            try {
                                
                                like l = new like(1,5,5);
                                ste=mc.prepareStatement(sql);
                                ste.setInt(1,l.getLike());
                                ste.setInt(2, l.getUser_id());
                                ste.setInt(3, l.getArticle_id());
                                ste.executeUpdate();
                                System.out.println("like Ajoutée");
                                btn.setStyle("-fx-background-color: #8EADF7 ");
                                liked =1;
                            } catch (SQLException ex) {
                                System.out.println(ex.getMessage());
                            }}
                                else{
                                btn.setStyle("");
                                liked =0;
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        commenatireTable.getColumns().add(colBtn);

    }
    
private void addButtonToTable2()  {
        TableColumn<Review, Void> colBtn = new TableColumn("Action");
        

        Callback<TableColumn<Review, Void>, TableCell<Review, Void>> cellFactory = new Callback<TableColumn<Review, Void>, TableCell<Review, Void>>() {
            @Override
            public TableCell<Review, Void> call(final TableColumn<Review, Void> param) {
                final TableCell<Review, Void> cell = new TableCell<Review, Void>() {

                    private final Button btn = new Button("Dislike");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            
                             String sql ="insert into dislike(dislike,article_id,user_id) Values(?,?,?)";
                            
                                if(disliked == 0 && liked == 0)
                                {
                                try {
                                    
                                dislike d = new dislike(1,5,5);
                                ste=mc.prepareStatement(sql);
                                ste.setInt(1,d.getDislike());
                                ste.setInt(2, d.getUser_id());
                                ste.setInt(3, d.getArticle_id());
                                ste.executeUpdate();
                                disliked =1;
                                System.out.println("dislike Ajoutée");
                                btn.setStyle("-fx-background-color: #8EADF7 ");

                            } catch (SQLException ex) {
                                System.out.println(ex.getMessage());
                            }}else{
                                    disliked=0;
                                    btn.setStyle(" ");
                                }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        commenatireTable.getColumns().add(colBtn);

    }
    

}
