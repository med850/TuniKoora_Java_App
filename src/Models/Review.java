/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Date;

/**
 *
 * @author wassim
 */
public class Review {
    int idReview;
    Date dateReview;
    String descriptionReview;
    int idArticle;
    int idUser;

    public Review(){};
    
    public Review(int idReview, Date dateReview, String descriptionReview, int idArticle, int idUser) {
        this.idReview = idReview;
        this.dateReview = dateReview;
        this.descriptionReview = descriptionReview;
        this.idArticle = idArticle;
        this.idUser = idUser;
    }

    public int getIdReview() {
        return idReview;
    }

    public Date getDateReview() {
        return dateReview;
    }

    public String getDescriptionReview() {
        return descriptionReview;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdReview(int idReview) {
        this.idReview = idReview;
    }

    public void setDateReview(Date dateReview) {
        this.dateReview = dateReview;
    }

    public void setDescriptionReview(String descriptionReview) {
        this.descriptionReview = descriptionReview;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
    
}
