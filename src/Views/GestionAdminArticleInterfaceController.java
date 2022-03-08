/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.ArticleController;
import Models.APIConnector;
import Models.Article;
import Models.Review;
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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import tools.MaConnexion;

/**
 * FXML Controller class
 *
 * @author wassim
 */
public class GestionAdminArticleInterfaceController implements Initializable {

    @FXML
    private TextArea DescArticle;
    @FXML
    private TextArea titreArticle;
    @FXML
    private Button ajouterArticle;
    @FXML
    private TableColumn<Article, Integer> id;
    @FXML
    private TableColumn<Article, String> titre;
    @FXML
    private TableColumn<Article, String> desc;
    @FXML
    private TableColumn<Article, Integer> userId;
    @FXML
    private Button supprimerArticle;
    @FXML
    private Button modifierArticle;
    @FXML
    private TableView<Article> tableArticle;
      ObservableList<Article>articleList;
    
     Connection mc;
    PreparedStatement ste;
    @FXML
    private TextArea idtxt;
    @FXML
    private TextArea recherche;
    @FXML
    private Button pdf;
    @FXML
    private TextArea weatherTxt;
    @FXML
    private Text showWeather;
    @FXML
    private Button weatherBtn;

    private final String cityAPI = "https://www.metaweather.com/api/location/search/?query=";

    private final String weatherAPI = "https://www.metaweather.com/api/location/";
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         afficherArticle();
     
    }    
    
    void afficherArticle(){
            mc=MaConnexion.getInstance().getCnx();
        articleList = FXCollections.observableArrayList();
       
        String sql="select * from article";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Article e = new Article();
                e.setIdArticle(rs.getInt("id"));
                e.setTitreArticle(rs.getString("titre"));
                e.setDescriptionArticle(rs.getString("description"));
                e.setIdUser(rs.getInt("user_id"));
                articleList.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        id.setCellValueFactory(new PropertyValueFactory<Article,Integer>("idArticle"));
        titre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTitreArticle()));
        desc.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getDescriptionArticle()));
        userId.setCellValueFactory(new PropertyValueFactory<Article,Integer>("idUser"));


        tableArticle.setItems(articleList);
        
        search();
        
    }

    @FXML
    private void addArticle(MouseEvent event) throws SQLException {
        String titre = titreArticle.getText();
        String desc = DescArticle.getText();
     
         if (desc.isEmpty() || titre.isEmpty()){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             //alert.setHeaderText("ERROR");
             alert.setContentText("Donnees non disponible!!");
             alert.showAndWait();          
         }
         else{     
             Article a=new Article(1,titre,desc,5);
             ArticleController ac = new ArticleController();
             String sql = "select * from article where titre='"+titre+"'";
             ste=mc.prepareStatement(sql);
             ResultSet rs=ste.executeQuery();
             if(rs.next()==true){
              Alert alert = new Alert(Alert.AlertType.ERROR);
             //alert.setHeaderText("ERROR");
             alert.setContentText("Titre existe deja!");
             alert.showAndWait();
              titreArticle.setText(null);
             DescArticle.setText(null);            
             }else{
             ac.ajouterArticle(a);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
             //alert.setHeaderText("Succes");
             alert.setContentText("Article Ajoutée avec succes!");
                alert.showAndWait();             
         }}
         refresh();
    }
    
    @FXML
    private void deleteArticle(MouseEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");

        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){
               mc=MaConnexion.getInstance().getCnx();
            String sql = "delete from article where id = ?";
            ste=mc.prepareStatement(sql);
            ste.setString(1, idtxt.getText());
            ste.execute();
            JOptionPane.showMessageDialog(null, "Article supprimé" );
        
            refresh();
             }
        else{

              DescArticle.setText(null);
          titreArticle.setText(null);

        }
    }

    @FXML
    private void UpdateArticle(MouseEvent event) {
                Article clickedEquipe = tableArticle.getSelectionModel().getSelectedItem();
              Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Warning");
            alert.setContentText("Confirmation..!");
        Optional<ButtonType>result =  alert.showAndWait(); 
        if(result.get() == ButtonType.OK){ 
        try{
             mc=MaConnexion.getInstance().getCnx();
             String value1 = idtxt.getText();
             String value2 = titreArticle.getText();
             String value3 = DescArticle.getText();
             
             String sql = "update article set id = '"+value1+"', titre = '"+value2+"', description = '"+value3+"'  where id ='"+value1+"' ";
             ste=mc.prepareStatement(sql);
             ste.execute();
            JOptionPane.showMessageDialog(null, "Article modifié");
        }catch(Exception e){
               JOptionPane.showMessageDialog(null,e);

        }
        refresh();
               }
        else{

              DescArticle.setText(null);
            titreArticle.setText(null);

        }
        refresh();
     
    }

    @FXML
    private void getSelected(MouseEvent event) {
         Article clickedArticle = tableArticle.getSelectionModel().getSelectedItem();
         idtxt.setText(String.valueOf(clickedArticle.getIdArticle()));
        titreArticle.setText(String.valueOf(clickedArticle.getTitreArticle()));
        DescArticle.setText(String.valueOf(clickedArticle.getDescriptionArticle()));
    }
    
    public void refresh(){
        
         articleList.clear();
       
          
          mc=MaConnexion.getInstance().getCnx();

        articleList = FXCollections.observableArrayList();
        
        String sql="select * from article";
        try {
            ste=mc.prepareStatement(sql);
            ResultSet rs=ste.executeQuery();
            while(rs.next()){
                Article e = new Article();
                e.setIdArticle(rs.getInt("id"));
                e.setTitreArticle(rs.getString("titre"));
                e.setDescriptionArticle(rs.getString("description"));
                e.setIdUser(rs.getInt("user_id"));
                articleList.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         tableArticle.setItems(articleList);   
    }
        
    
    private void search() {      
        
        FilteredList<Article>filteredData = new FilteredList<>(articleList, b->true);
        recherche.textProperty().addListener((observable, oldValue, newValue)->{
           
            filteredData.setPredicate(article->{
               
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
               
                String lowerCaseFilter = newValue.toLowerCase();
                 
                  if(String.valueOf(article.getIdArticle()).indexOf(lowerCaseFilter) != -1){
                    return true;
                }
                  else if(String.valueOf(article.getTitreArticle()).indexOf(lowerCaseFilter) != -1){
                    return true;
                }else{
                return false;
                }
            });          
        });
        SortedList<Article>sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableArticle.comparatorProperty());
        tableArticle.setItems(sortedData);
    }

    
    @FXML
    private void createpdf(MouseEvent event) throws SQLException, FileNotFoundException, DocumentException, IOException {
        
    String sql = "SELECT * from article";
    ste=mc.prepareStatement(sql);
    ResultSet rs=ste.executeQuery();

    Document doc = new Document();
    PdfWriter.getInstance(doc, new FileOutputStream("./ListeDesArticles.pdf"));

    doc.open();
   
    doc.add(new Paragraph("   "));
    doc.add(new Paragraph(" *************************************** Liste Des Article *************************************** "));
    doc.add(new Paragraph("   "));

    PdfPTable table = new PdfPTable(2);
    table.setWidthPercentage(50);
    PdfPCell cell;

    cell = new PdfPCell(new Phrase("Titre des articles", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    
    table.addCell(cell);
   
    cell = new PdfPCell(new Phrase("Description des articles", FontFactory.getFont("Comic Sans MS", 14)));
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    
    table.addCell(cell);

    while (rs.next()) {

        Article e = new Article();
      //  e.setId(rs.getInt("id"));
        e.setTitreArticle(rs.getString("titre"));
        e.setDescriptionArticle(rs.getString("description"));
       // e.setClassement(rs.getInt("classement"));
       
      
        cell = new PdfPCell(new Phrase(e.getTitreArticle(), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
        
        cell = new PdfPCell(new Phrase(e.getDescriptionArticle(), FontFactory.getFont("Comic Sans MS", 12)));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }

    doc.add(table);
    doc.close();
    Desktop.getDesktop().open(new File("./ListeDesArticles.pdf"));

    }

    @FXML
    private void addWeather(ActionEvent event) throws MalformedURLException {
         JSONObject todaysWeather = GetTodaysWeatherInformation(getWoeid());

        System.out.println(todaysWeather);

        showWeather.setText(
            "Min temperature: " + todaysWeather.get("min_temp") +
            "\nCurrent temperature: " + todaysWeather.get("the_temp") +
            "\nMax temperature: " + todaysWeather.get("max_temp")
        );
    }
    public String getWoeid() throws MalformedURLException {
        APIConnector apiConnectorCity = new APIConnector(cityAPI);

        JSONObject jsonData = (JSONObject) (apiConnectorCity.getJSONArray(weatherTxt.getText())).get(0);

        return jsonData.get("woeid").toString();
    }

    public JSONObject GetTodaysWeatherInformation(String woeid) throws MalformedURLException {
        APIConnector apiConnectorWeather = new APIConnector(weatherAPI);

        JSONObject weatherJSONObject = apiConnectorWeather.getJSONObject(woeid + "/");

        JSONArray weatherArray = (JSONArray) weatherJSONObject.get("consolidated_weather");

        return  (JSONObject) weatherArray.get(0);
    }
  
    
    }
    

