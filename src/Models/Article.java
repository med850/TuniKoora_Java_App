/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;


/**
 *
 * @author wassim
 */
public class Article {
    int idArticle;
    String titreArticle;
    String descriptionArticle;
    int idUser;

    public Article(){};
    
    public Article(int idArticle, String titreArticle, String descriptionArticle, int idUser) {
        this.idArticle = idArticle;
        this.titreArticle = titreArticle;
        this.descriptionArticle = descriptionArticle;
        this.idUser = idUser;
        
    }

   
    public int getIdArticle() {
        return idArticle;
    }

    public String getTitreArticle() {
        return titreArticle;
    }

    public String getDescriptionArticle() {
        return descriptionArticle;
    }
    

    public int getIdUser() {
        return idUser;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public void setTitreArticle(String titreArticle) {
        this.titreArticle = titreArticle;
    }

    public void setDescriptionArticle(String descriptionArticle) {
        this.descriptionArticle = descriptionArticle;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Article{" + "idArticle=" + idArticle + ", titreArticle=" + titreArticle + ", descriptionArticle=" + descriptionArticle + ", idUser=" + idUser + '}';
    }
    
    
    
    
}
