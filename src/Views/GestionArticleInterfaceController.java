/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import Models.Article;
import Controllers.ArticleController;
import Controllers.ReviewController;
import Models.Equipe;
import Models.Review;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import tools.MaConnexion;


/**
 * FXML Controller class
 *
 * @author wassim
 */
public class GestionArticleInterfaceController implements Initializable {
    Article article;
    @FXML
    private TableColumn<Article, String> titreArticle;
     @FXML
    private TableColumn<Article, String> desc;
    @FXML
    private TableView<Article> articleTable;
    
       ObservableList<Article>articleList;

     Connection mc;
    PreparedStatement ste;
    @FXML
    private TextArea recherchetxt;
    private Button recherche;

    @FXML
    private TableColumn<Article, Integer> id;
    private TextArea idA;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
           mc=MaConnexion.getInstance().getCnx();

        articleList = FXCollections.observableArrayList();
        
        String sql="select * from article";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Article a = new Article();
                a.setIdArticle(rs.getInt("id"));
                a.setTitreArticle(rs.getString("titre"));
                a.setDescriptionArticle(rs.getString("description"));
                articleList.add(a);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        id.setCellValueFactory(new PropertyValueFactory<Article, Integer>("idArticle"));
        titreArticle.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTitreArticle()));
        desc.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescriptionArticle()));
        addButtonToTable();
        articleTable.setItems(articleList);  
        search();

        
    } 
    
    private void search() {      
        
        FilteredList<Article>filteredData = new FilteredList<>(articleList, b->true);
        recherchetxt.textProperty().addListener((observable, oldValue, newValue)->{
           
            filteredData.setPredicate(article->{
               
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
               
                String lowerCaseFilter = newValue.toLowerCase();
               
                 if(String.valueOf(article.getTitreArticle()).indexOf(lowerCaseFilter) != -1){
                    return true;
                }else{
                return false;
                }
            });          
        });
        
        SortedList<Article>sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(articleTable.comparatorProperty());
        articleTable.setItems(sortedData);
        
    }
    
   private void addButtonToTable()  {
        TableColumn<Article, Void> colBtn = new TableColumn("Voir l'article");

        Callback<TableColumn<Article, Void>, TableCell<Article, Void>> cellFactory = new Callback<TableColumn<Article, Void>, TableCell<Article, Void>>() {
            @Override
            public TableCell<Article, Void> call(final TableColumn<Article, Void> param) {
                final TableCell<Article, Void> cell = new TableCell<Article, Void>() {

                    private final Button btn = new Button("Action");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Stage stage = (Stage) btn.getScene().getWindow();
                            stage.close();
                            Stage primaryStage =new Stage();
                            Parent root;
                            try {
                            root = FXMLLoader.load(getClass().getResource("gestionReviewInterface.fxml"));
                            Scene scene = new Scene(root);
                            primaryStage.setTitle("Gestion des review");
                            primaryStage.setScene(scene);
                            primaryStage.show();
                            Article ar = getTableView().getItems().get(getIndex());

                            } catch (IOException ex) {
                                Logger.getLogger(GestionArticleInterfaceController.class.getName()).log(Level.SEVERE, null, ex);
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
        articleTable.getColumns().add(colBtn);

    }

    @FXML
    public void getSelected(MouseEvent event) {
           
         Article clickArticle = articleTable.getSelectionModel().getSelectedItem();
         
    }

}

